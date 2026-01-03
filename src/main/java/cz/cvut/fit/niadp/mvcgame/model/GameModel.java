package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.flyweight.ParticleFlyweightFactory;
import cz.cvut.fit.niadp.mvcgame.flyweight.ParticleType;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class GameModel implements IGameModel {

    private final AbsCannon cannon;
    private GameInfo gameInfo;
    private final List<AbsMissile> missiles;
    private List<AbsEnemy> enemies;
    private final Set<IObserver> observers;
    private final List<Particle> particles;
    private final IGameObjectsFactory gameObjectsFactory;
    private IMovingStrategy movingStrategy;
    Random rand = new Random();
    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    public GameModel() {
        gameObjectsFactory = new GameObjectsFactoryA(this);
        cannon = gameObjectsFactory.createCannon();
        gameInfo = new GameInfo(new Position(MvcGameConfig.GAMEINF_POS_X, MvcGameConfig.GAMEINF_POS_Y), cannon);
        observers = new HashSet<>();
        missiles = new ArrayList<>();
        enemies = new ArrayList<>();
        particles = new ArrayList<>();

        movingStrategy = new SimpleMovingStrategy();

        unexecutedCommands = new LinkedBlockingQueue<>();
        executedCommands = new Stack<>();
    }

    public void update() {
        moveMissiles();
        moveParticles();
        checkCollisions();
        executeCommands();
    }

    private void executeCommands() {
        while(!unexecutedCommands.isEmpty()) {
            executedCommands.push(unexecutedCommands.poll().doExecute());
        }
    }

    private void moveMissiles() {
        missiles.forEach(AbsMissile::move);
        destroyMissiles();
        notifyObservers();
    }

    private void checkCollisions() {
        Set<AbsMissile> missilesToRemove = new HashSet<>();
        Set<AbsEnemy> enemiesToRemove = new HashSet<>();

        for (AbsMissile missile : missiles) {
            for (AbsEnemy enemy : enemies) {
                if(missile.getCollider().collided(enemy.getCollider())) {
                    gameInfo.addScore(enemy.getScoreValue());
                    bomboclat(enemy.getPosition());
                    missilesToRemove.add(missile);
                    enemiesToRemove.add(enemy);
                }
            }
        }

        if(!missilesToRemove.isEmpty() || !enemiesToRemove.isEmpty()) {
            missiles.removeAll(missilesToRemove);
            enemies.removeAll(enemiesToRemove);
        }
    }

    private void moveParticles() {
        particles.forEach(Particle::move);
        particles.removeIf(p -> p.getAge() > 1000);
    }

    private void bomboclat(Position pos) {
        ParticleType fireType = ParticleFlyweightFactory.getParticleType(
                "fire", "images/fire.png", 5, 10
        );

        for (int i = 0; i < MvcGameConfig.PARTICLE_COUNT; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;

            int velocity = 10 + rand.nextInt(10);

            particles.add(new Particle(
                    pos,
                    fireType,
                    angle,
                    velocity
            ));
        }
    }

    private void destroyMissiles() {
        missiles.removeAll(
            missiles.stream().filter(missile ->
                missile.getPosition().getX() > MvcGameConfig.MAX_X ||  missile.getPosition().getX() < MvcGameConfig.MIN_X
            ).toList()
        );
    }

    public Position getCannonPosition() {
        return cannon.getPosition();
    }

    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }

    public void aimCannonUp() {
        cannon.aimUp();
        notifyObservers();
    }

    public void aimCannonDown() {
        cannon.aimDown();
        notifyObservers();
    }

    public void cannonPowerUp() {
        cannon.powerUp();
        notifyObservers();
    }

    public void cannonPowerDown() {
        cannon.powerDown();
        notifyObservers();
    }

    public void cannonShoot() {
        missiles.addAll(cannon.shoot());
        gameInfo.incMissilesShot();
        notifyObservers();
    }

    public List<GameObject> getGameObjects() {
        return Stream.concat(Stream.of(cannon), missiles.stream()).toList();
    }

    @Override
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @Override
    public AbsCannon getCanon() {
        return cannon;
    }

    @Override
    public List<AbsMissile> getMissiles() {
        return missiles;
    }


    @Override
    public List<AbsEnemy> getEnemies() {
        return enemies;
    }

    @Override
    public List<Particle> getParticles() {
        return particles;
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(IObserver::update);
    }

    public IMovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public void toggleMovingStrategy() {
        if (movingStrategy instanceof SimpleMovingStrategy) {
            movingStrategy = new RealisticMovingStrategy();
        } else if (movingStrategy instanceof RealisticMovingStrategy) {
            movingStrategy = new RandomMovingStrategy();
        } else if (movingStrategy instanceof RandomMovingStrategy) {
            movingStrategy = new SimpleMovingStrategy();
        } else {

        }
    }

    public void toggleShootingMode() {
        cannon.toggleShootingMode();
    }

    private static class Memento {
        private int cannonPositionX;
        private int cannonPositionY;

        private List<AbsEnemy> enemies;

        private GameInfo gameInfo;

        private double cannonAngle;
        private int cannonPower;
        private IShootingMode shootingMode;

        // game snapshot (gameobjects, score, strategy, cannon state)
    }

    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = cannon.getPosition().getX();
        gameModelSnapshot.cannonPositionY = cannon.getPosition().getY();

        gameModelSnapshot.enemies = new ArrayList<>();
        for(AbsEnemy enemy: enemies) {
            gameModelSnapshot.enemies.add(enemy.clone());
        }

        gameModelSnapshot.gameInfo = gameInfo.clone();

        gameModelSnapshot.cannonAngle = cannon.getAngle();
        gameModelSnapshot.cannonPower = cannon.getPower();
        gameModelSnapshot.shootingMode = cannon.getShootingMode();

        return gameModelSnapshot;
    }

    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        cannon.getPosition().setX((gameModelSnapshot).cannonPositionX);
        cannon.getPosition().setY((gameModelSnapshot).cannonPositionY);
        cannon.setShootingMode(gameModelSnapshot.shootingMode);
        cannon.setAngle(gameModelSnapshot.cannonAngle);
        cannon.setPower(gameModelSnapshot.cannonPower);

        enemies = gameModelSnapshot.enemies;

        gameInfo = gameModelSnapshot.gameInfo;
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        unexecutedCommands.add(command);
    }

    @Override
    public void undoLastCommand() {
        if (!executedCommands.isEmpty()) {
            executedCommands.pop().unExecute();
            notifyObservers();
        }
    }

}

package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class GameModel implements IGameModel {

    private final AbsCannon cannon;
    private final GameInfo gameInfo;
    private final List<AbsMissile> missiles;
    private List<AbsEnemy> enemies;
    private final Set<IObserver> observers;
    private final IGameObjectsFactory gameObjectsFactory;
    private IMovingStrategy movingStrategy;

    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    public GameModel() {
        gameObjectsFactory = new GameObjectsFactoryA(this);
        cannon = gameObjectsFactory.createCannon();
        gameInfo = new GameInfo(new Position(MvcGameConfig.GAMEINF_POS_X, MvcGameConfig.GAMEINF_POS_Y));
        observers = new HashSet<>();
        missiles = new ArrayList<>();
        enemies = new ArrayList<>();
        movingStrategy = new SimpleMovingStrategy();

        unexecutedCommands = new LinkedBlockingQueue<>();
        executedCommands = new Stack<>();
    }

    public void update() {
        moveMissiles();
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

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @Override
    public List<AbsEnemy> getEnemies() {
        return enemies;
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

        //game info
        private int shotCount;
        private int score;


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

        gameModelSnapshot.shotCount = gameInfo.getMissilesShot();
        gameModelSnapshot.score = gameInfo.getScore();
        return gameModelSnapshot;
    }

    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        cannon.getPosition().setX((gameModelSnapshot).cannonPositionX);
        cannon.getPosition().setY((gameModelSnapshot).cannonPositionY);

        enemies = gameModelSnapshot.enemies;

        gameInfo.setMissilesShot(gameModelSnapshot.shotCount);
        gameInfo.setScore(gameModelSnapshot.score);
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

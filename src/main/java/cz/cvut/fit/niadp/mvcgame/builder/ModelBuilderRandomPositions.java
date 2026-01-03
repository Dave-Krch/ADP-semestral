package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.EnemyLargeA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.EnemyStandardA;

import java.util.Random;

public class ModelBuilderRandomPositions implements IModelBuilder {

    private GameModel model;

    private final Random random;

    public ModelBuilderRandomPositions() {
        this.reset();
        this.random = new Random();
    }

    public ModelBuilderRandomPositions(Random random) {
        this.reset();
        this.random = random;
    }

    @Override
    public void reset() {
        model = new GameModel();
    }

    @Override
    public void createNormalEnemies(int count) {
        for (int i = 0; i < count; i++) {
            // Create a temporary enemy to check its size/collision
            // We start with a dummy position (0,0) just to get the object dimensions
            EnemyStandardA newEnemy = new EnemyStandardA(new Position(0, 0));

            Position validPos = findFreePosition(newEnemy);
            if (validPos != null) {
                // Update position to the valid one found
                newEnemy.getPosition().setX(validPos.getX());
                newEnemy.getPosition().setY(validPos.getY());
                model.getEnemies().add(newEnemy);
            }
        }
    }

    @Override
    public void createLargeEnemies(int count) {
        for (int i = 0; i < count; i++) {
            EnemyLargeA newEnemy = new EnemyLargeA(new Position(0, 0));

            Position validPos = findFreePosition(newEnemy);
            if (validPos != null) {
                newEnemy.getPosition().setX(validPos.getX());
                newEnemy.getPosition().setY(validPos.getY());
                model.getEnemies().add(newEnemy);
            }
        }
    }

    /**
     * Tries to find a random position where the enemy won't collide with others.
     * Returns null if no valid position is found after max attempts.
     */
    private Position findFreePosition(AbsEnemy enemyToCheck) {
        int maxAttempts = 100; // Safety break to avoid infinite loops

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            // 1. Generate random X and Y within spawn bounds
            int x = random.nextInt(MvcGameConfig.ENEMY_SPAWN_MAX_X - MvcGameConfig.ENEMY_SPAWN_MIN_X)
                    + MvcGameConfig.ENEMY_SPAWN_MIN_X;

            int y = random.nextInt(MvcGameConfig.ENEMY_SPAWN_MAX_Y - MvcGameConfig.ENEMY_SPAWN_MIN_Y)
                    + MvcGameConfig.ENEMY_SPAWN_MIN_Y;

            // 2. Temporarily set this position to the enemy
            enemyToCheck.getPosition().setX(x);
            enemyToCheck.getPosition().setY(y);

            // 3. Check collision with ALL existing enemies in the model
            boolean collisionDetected = false;
            for (AbsEnemy existingEnemy : model.getEnemies()) {
                if (enemyToCheck.getCollider().collided(existingEnemy.getCollider())) {
                    collisionDetected = true;
                    break;
                }
            }

            // 4. If no collision, we found a good spot!
            if (!collisionDetected) {
                return new Position(x, y);
            }
        }

        // If we tried 100 times and failed (screen is full), return null
        return null;
    }

    @Override
    public GameModel getProduct() {
        return model;
    }
}

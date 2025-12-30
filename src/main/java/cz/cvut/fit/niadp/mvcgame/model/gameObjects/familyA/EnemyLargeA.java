package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class EnemyLargeA extends AbsEnemy {
    public EnemyLargeA(Position position) {
        this.position = position;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitLargeEnemy(this);
    }

    @Override
    public int getScoreValue() {
        return MvcGameConfig.ENEMY_LARGE_VALUE;
    }

    @Override
    public AbsEnemy clone() {
        return new EnemyLargeA(this.position.clone());
    }

    @Override
    public int getWidth() {
        return MvcGameConfig.ENEMY_LARGE_COLLIDER_WIDTH;
    }

    @Override
    public int getHeight() {
        return MvcGameConfig.ENEMY_LARGE_COLLIDER_HEIGHT;
    }
}

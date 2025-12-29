package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class EnemyStandardA extends AbsEnemy {

    public EnemyStandardA(Position position) {
        this.position = position;
        this.collider = new Collider(this.position, MvcGameConfig.ENEMY_NORMAL_COLLIDER_WIDTH, MvcGameConfig.ENEMY_NORMAL_COLLIDER_HEIGHT);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitNormalEnemy(this);
    }

    @Override
    public int getScoreValue() {
        return MvcGameConfig.ENEMY_STANDARD_VALUE;
    }

    @Override
    public AbsEnemy clone() {
        return new EnemyStandardA(this.position);
    }
}

package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class EnemyLargeA extends AbsEnemy {
    public EnemyLargeA(Position position) {
        this.position = position;
        this.collider = new Collider(this.position, MvcGameConfig.ENEMY_LARGE_COLLIDER_WIDTH, MvcGameConfig.ENEMY_LARGE_COLLIDER_HEIGHT);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitLargeEnemy(this);
    }


}

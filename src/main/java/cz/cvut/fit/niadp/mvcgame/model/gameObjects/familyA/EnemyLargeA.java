package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class EnemyLargeA extends AbsEnemy {
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitLargeEnemy(this);
    }
}

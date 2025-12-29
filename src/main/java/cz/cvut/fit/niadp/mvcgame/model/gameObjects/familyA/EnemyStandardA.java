package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class EnemyStandardA extends AbsEnemy {

    public EnemyStandardA(Position position) {
        this.position = position;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitNormalEnemy(this);
    }
}

package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject implements ICollidable{
    protected Collider collider;

    public abstract int getScoreValue();

    @Override
    public Collider getCollider() {
        return collider;
    }

}

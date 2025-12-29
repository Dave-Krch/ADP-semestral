package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;
import javafx.geometry.Pos;

public abstract class AbsEnemy extends GameObject implements ICollidable{
    protected Collider collider;

    public abstract int getScoreValue();

    public abstract AbsEnemy clone();

    @Override
    public Collider getCollider() {
        return collider;
    }

}

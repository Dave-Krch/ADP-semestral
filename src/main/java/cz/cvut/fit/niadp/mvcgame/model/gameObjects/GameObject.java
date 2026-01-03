package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitable;

import java.io.Serializable;

public abstract class GameObject implements IVisitable, Serializable {

    protected Position position;

    public void move(Vector vector) {
        position.add(vector);
    }

    public Position getPosition() {
        return position;
    }
}

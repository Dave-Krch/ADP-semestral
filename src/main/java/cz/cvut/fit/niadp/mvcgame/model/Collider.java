package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.visitor.IVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

import java.io.Serializable;

public class Collider implements Serializable {
    private final Position topLeft;
    private final Position bottomRight;

    public Collider(Position topLeft, Position bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Collider(int minX, int minY, int maxX, int maxY) {
        this.topLeft = new Position(minX, minY);
        this.bottomRight = new Position(maxX, maxY);
    }

    public Collider(Position topLeft, int collWidth, int collHeight) {
        this.topLeft = topLeft;
        this.bottomRight = new Position(topLeft.getX() + collWidth, topLeft.getY() + collHeight);
    }

    public Collider clone() {
        return new Collider(new Position(topLeft.getX(), topLeft.getY()), new Position(bottomRight.getX(), bottomRight.getY()));
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public Position getBottomRight() {
        return bottomRight;
    }

    public boolean collided(Collider other) {
        return this.topLeft.getX() <= other.bottomRight.getX() &&
                this.bottomRight.getX() >= other.topLeft.getX() &&
                this.topLeft.getY() <= other.bottomRight.getY() &&
                this.bottomRight.getY() >= other.topLeft.getY();
    }


}

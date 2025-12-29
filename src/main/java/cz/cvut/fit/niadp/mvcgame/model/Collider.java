package cz.cvut.fit.niadp.mvcgame.model;

public class Collider {
    private final Position topLeft;
    private final Position bottomRight;

    public Collider(Position topLeft, Position bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Collider(Position topLeft, int collWidth, int collHeight) {
        this.topLeft = topLeft;
        this.bottomRight = new Position(topLeft.getX() + collWidth, topLeft.getY() + collHeight);
    }

    public boolean collided(Collider other) {
        return this.topLeft.getX() <= other.bottomRight.getX() &&
                this.bottomRight.getX() >= other.topLeft.getX() &&
                this.topLeft.getY() <= other.bottomRight.getY() &&
                this.bottomRight.getY() >= other.topLeft.getY();
    }
}

package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.flyweight.ParticleType;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class Particle extends LifetimeLimitedGameObject {

    private final ParticleType type;

    // We need the origin to calculate displacement from start
    private final Position origin;
    private final double angle;
    private final int velocity;

    public Particle(Position startPosition, ParticleType type, double angle, int velocity) {
        super(new Position(startPosition.getX(), startPosition.getY()));

        this.origin = new Position(startPosition.getX(), startPosition.getY());

        this.type = type;
        this.angle = angle;
        this.velocity = velocity;
    }

    public void move() {
        long age = this.getAge();

        long time = age / 20;

        int dX = (int) (this.velocity * time * Math.cos(this.angle));
        int dY = (int) (this.velocity * time * Math.sin(this.angle));

        this.getPosition().setX(origin.getX() + dX);
        this.getPosition().setY(origin.getY() + dY);
    }

    public String getImageResource() {
        return type.getImageResource();
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitParticle(this);
    }
}
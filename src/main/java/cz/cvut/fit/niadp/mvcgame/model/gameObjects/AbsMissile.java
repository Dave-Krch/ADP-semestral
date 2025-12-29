package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsMissile extends LifetimeLimitedGameObject {

    private final double initAngle;
    private final int initVelocity;

    protected Collider collider;

    protected AbsMissile(Position initPosition, double initAngle, int initVelocity) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.collider = new Collider(this.position, MvcGameConfig.MISSILE_COLLIDER_WIDTH, MvcGameConfig.MISSILE_COLLIDER_HEIGHT);
    }

    public abstract void move();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }

    public double getInitAngle() {
        return initAngle;
    }

    public int getInitVelocity() {
        return initVelocity;
    }
}

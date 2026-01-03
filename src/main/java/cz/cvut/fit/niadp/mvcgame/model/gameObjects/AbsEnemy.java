package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;
import javafx.geometry.Pos;

import java.io.Serializable;

public abstract class AbsEnemy extends GameObject implements ICollidable, Serializable {

    public abstract int getScoreValue();

    public abstract AbsEnemy clone();

    public abstract int getWidth();
    public abstract int getHeight();

    // 3. GENERATE a fresh collider every time based on current position
    @Override
    public Collider getCollider() {
        return new Collider(
                this.position,
                this.getWidth(),
                this.getHeight()
        );
    }

}

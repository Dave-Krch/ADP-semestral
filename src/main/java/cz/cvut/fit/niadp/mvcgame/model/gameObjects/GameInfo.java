package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class GameInfo extends GameObject{

    public GameInfo(Position position) {
        this.position = position;
    }

    private int missilesShot = 0;

    public void inc() { missilesShot++; }
    public void dec() { missilesShot--; }

    public int getMissilesShot() { return missilesShot; }

    @Override
    public void acceptVisitor(IVisitor visitor) { visitor.visitGameInfo(this); }
}

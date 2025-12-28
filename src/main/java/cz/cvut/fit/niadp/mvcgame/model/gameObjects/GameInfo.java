package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class GameInfo extends GameObject{
    @Override
    public void acceptVisitor(IVisitor visitor) { visitor.visitGameInfo(this); }
}

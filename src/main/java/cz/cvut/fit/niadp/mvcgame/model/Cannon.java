package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;

public class Cannon extends GameObject{
    //int power
    //int angle

    public Cannon(Position pos) {
        this.position = pos;
    }

    public void moveUp() {
        move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
    }

    public void moveDown() {
        move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }
}

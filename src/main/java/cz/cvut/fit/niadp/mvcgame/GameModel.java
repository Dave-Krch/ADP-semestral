package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;

public class GameModel {

    public Position getLogoPos() {
        return logoPos;
    }

    private Position logoPos;

    public GameModel() {
        logoPos = new Position( ((MvcGameConfig.MAX_X/2)-128), ((MvcGameConfig.MAX_Y/2)-128) );
    }

    public void update() {
    }

    public void moveLogoUp() {
        logoPos.setY(logoPos.getY() - MvcGameConfig.MOVE_STEP);
    }

    public void moveLogoDown() {
        logoPos.setY(logoPos.getY() + MvcGameConfig.MOVE_STEP);
    }

    public void moveLogoLeft() {
        logoPos.setX(logoPos.getX() - MvcGameConfig.MOVE_STEP);
    }

    public void moveLogoRight() {
        logoPos.setX(logoPos.getX() + MvcGameConfig.MOVE_STEP);
    }
}

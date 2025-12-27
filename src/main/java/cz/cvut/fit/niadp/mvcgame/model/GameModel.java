package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;

public class GameModel {

    private Cannon canon;

    public GameModel() {
        canon = new Cannon(new Position (MvcGameConfig.CANON_POS_X, MvcGameConfig.CANON_POS_Y ));
    }

    public Position getCanonPosition() {
        return canon.getPosition();
    }

    public void update() {
    }

    public void moveLogoUp() {
        canon.moveUp();
    }

    public void moveLogoDown() {
        canon.moveDown();
    }

}

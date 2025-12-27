package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;

import java.util.List;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameKeys.*;

public class GameController {

    private final GameModel model;
    public GameController(GameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case KEY_UP:
                    model.moveLogoUp();
                    break;
                case KEY_DOWN:
                    model.moveLogoDown();
                    break;
                case KEY_LEFT:
                    //model.moveLogoLeft();
                    break;
                case KEY_RIGHT:
                    //model.moveLogoRight();
                    break;
                case KEY_EXIT:
                    System.exit(0);
                    break;
                default:
                    //nothing
            }
        }
    }
}

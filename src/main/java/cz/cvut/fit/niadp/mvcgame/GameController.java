package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
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
            Position logoPos = model.getLogoPos();
            switch(code) {
                case KEY_UP:
                    model.moveLogoUp();
                    break;
                case KEY_DOWN:
                    model.moveLogoDown();
                    break;
                case KEY_LEFT:
                    model.moveLogoLeft();
                    break;
                case KEY_RIGHT:
                    model.moveLogoRight();
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

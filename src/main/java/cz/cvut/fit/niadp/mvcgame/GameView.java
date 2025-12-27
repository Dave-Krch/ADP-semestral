package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.LOGO_RESOURCE;

public class GameView {

    private final GameModel model;
    private final GameController controller;
    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(model);
    }

    public GameController getController() {
        return controller;
    }

    public void render(GraphicsContext gr) {
        Position pos = model.getLogoPos();
        gr.drawImage(new Image(LOGO_RESOURCE), pos.getX(), pos.getY());
    }
}

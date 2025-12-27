package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.CANON_RESOURCE;

public class GameView implements IObserver {

    private final GameModel model;
    private final GameController controller;
    private GraphicsContext gc;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        model.registerObserver(this);
    }

    public GameController getController() {
        return controller;
    }

    public void render() {
        if(gc == null) return;
        gc.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        drawCannon();
    }

    public void drawCannon() {
        Position pos = model.getCanonPosition();
        gc.drawImage(new Image(CANON_RESOURCE), pos.getX(), pos.getY());
    }

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
        render();
    }

    @Override
    public void update() {
        render();
    }
}

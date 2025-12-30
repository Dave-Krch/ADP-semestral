package cz.cvut.fit.niadp.mvcgame;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.builder.IModelBuilder;
import cz.cvut.fit.niadp.mvcgame.builder.ModelBuilderRandomPositions;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.proxy.GameModelProxy;
import cz.cvut.fit.niadp.mvcgame.view.GameView;

public class MvcGame {
    private IGameModel model;
    private GameController controller;
    private GameView view;

    public void init() {
        //Using builder to build model <- passing only counts of enemies, builder handles other logic
        IModelBuilder modelBuilder = new ModelBuilderRandomPositions();
        modelBuilder.createNormalEnemies(MvcGameConfig.STARTING_NORMAL_ENEMIES);
        modelBuilder.createLargeEnemies(MvcGameConfig.STARTING_LARGE_ENEMIES);
        model = new GameModelProxy(modelBuilder.getProduct());

        view = new GameView(model);
        controller = view.getController();
        CareTaker.getInstance().setModel(model);
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        controller.processPressedKeys(pressedKeysCodes);
    }

    public String getWindowTitle() {
        return "The NI-ADP MvcGame";
    }

    public int getWindowWidth() {
        return MvcGameConfig.MAX_X;
    }

    public int getWindowHeight() {
        return  MvcGameConfig.MAX_Y;
    }

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        view.setGraphicsContext(gameGraphics);
    }
}
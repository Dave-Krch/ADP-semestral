package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameDrawer;
import cz.cvut.fit.niadp.mvcgame.visitor.GameDrawerDebug;

public class GameView implements IObserver {

    private final IGameModel model;
    private final GameController controller;
    private IGameGraphics gameGraphics;
    private GameDrawer currentGameDrawer;
    private final GameDrawer gameDrawer;
    private final GameDrawer gameDrawerDebug;
    private boolean debugModeSet = false;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(model, this);
        model.registerObserver(this);
        gameDrawer = new GameDrawer();
        gameDrawerDebug = new GameDrawerDebug();
        currentGameDrawer = gameDrawer;
    }

    public GameController getController() {
        return controller;
    }

    public void switchDrawerModes() {
        if(debugModeSet) {
            currentGameDrawer = gameDrawer;
            debugModeSet = false;
        }
        else {
            currentGameDrawer = gameDrawerDebug;
            debugModeSet = true;
        }
    }

    public void render() {
        if (gameGraphics != null) {
            gameGraphics.clear();
            model.getCanon().acceptVisitor(currentGameDrawer);
            model.getMissiles().forEach(absMissile -> {
                absMissile.acceptVisitor(currentGameDrawer);
            });
            model.getEnemies().forEach(absEnemy -> {
                absEnemy.acceptVisitor(currentGameDrawer);
            });
            model.getParticles().forEach(particle -> {
                particle.acceptVisitor(currentGameDrawer);
            });
            model.getGameInfo().acceptVisitor(currentGameDrawer);

        }
    }

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
        gameDrawer.setGraphicsContext(gameGraphics);
        gameDrawerDebug.setGraphicsContext(gameGraphics);
        this.render();
    }

    @Override
    public void update() {
        render();
    }
}

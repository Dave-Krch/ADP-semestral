package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.CANNON_RESOURCE;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.MISSILE_RESOURCE;

public class GameDrawer implements IVisitor {

    private IGameGraphics gameGraphics;

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        drawGameObject(cannon, CANNON_RESOURCE);
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        drawGameObject(missile, MISSILE_RESOURCE);
    }

    private void drawGameObject(GameObject gameObject, String resource) {
        gameGraphics.drawImage(resource, gameObject.getPosition());
    }
}

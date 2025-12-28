package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.*;

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

    @Override
    public void visitEnemy(AbsEnemy enemy) { drawGameObject(enemy, ENEMY_1_RESOURCE); }

    @Override
    public void visitGameInfo(GameInfo gameInfo) { drawGameInfo(gameInfo); }

    private void drawGameObject(GameObject gameObject, String resource) {
        gameGraphics.drawImage(resource, gameObject.getPosition());
    }

    //TODO: Vypsat game info
    private void drawGameInfo(GameInfo gameInfo) {
        gameGraphics.drawText("" + gameInfo.getMissilesShot(), gameInfo.getPosition());
    }
}

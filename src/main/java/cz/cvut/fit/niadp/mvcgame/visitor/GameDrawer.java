package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import javafx.geometry.Pos;

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
    public void visitNormalEnemy(AbsEnemy enemy) { drawGameObject(enemy, ENEMY_NORMAL_RESOURCE); }

    @Override
    public void visitLargeEnemy(AbsEnemy enemy) { drawGameObject(enemy, ENEMY_LARGE_RESOURCE); }

    @Override
    public void visitGameInfo(GameInfo gameInfo) { drawGameInfo(gameInfo); }

    private void drawGameObject(GameObject gameObject, String resource) {
        gameGraphics.drawImage(resource, gameObject.getPosition());
    }

    private void drawGameInfo(GameInfo gameInfo) {
        gameGraphics.drawText(    "Score:         " + gameInfo.getScore() +
                                     "\nShot count: " + gameInfo.getShotCount(), gameInfo.getPosition());
    }
}

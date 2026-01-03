package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameInfo;

public class GameDrawerDebug extends GameDrawer {

    @Override
    public void visitMissile(AbsMissile missile) {
        super.visitMissile(missile);
        drawCollider(missile.getCollider());
    }

    @Override
    public void visitGameInfo(GameInfo gameInfo) {
        gameGraphics.drawText(
                "Score:        " + gameInfo.getScore() + "\n" +
                     "Shot count:   " + gameInfo.getShotCount() + "\n" +
                     "Cannon angle: " + gameInfo.getCannonAngle() + "\n" +
                     "Cannon power: " + gameInfo.getCannonPower() + "\n" +
                gameInfo.legend, gameInfo.getPosition());
        drawCollider(gameInfo.getCollider());
    }

    @Override
    public void visitNormalEnemy(AbsEnemy enemy) {
        super.visitNormalEnemy(enemy);
        drawCollider(enemy.getCollider());
    }

    @Override
    public void visitLargeEnemy(AbsEnemy enemy) {
        super.visitLargeEnemy(enemy);
        drawCollider(enemy.getCollider());
    }

    public void drawCollider(Collider collider) {
        gameGraphics.drawRectangle(collider.getTopLeft(), collider.getBottomRight());
    }
}

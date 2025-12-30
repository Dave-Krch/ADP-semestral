package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameInfo;

public interface IVisitor {
    void visitCannon(AbsCannon cannon);
    void visitMissile(AbsMissile missile);
    void visitNormalEnemy(AbsEnemy enemy);
    void visitLargeEnemy(AbsEnemy enemy);
    void visitGameInfo(GameInfo gameInfo);
}

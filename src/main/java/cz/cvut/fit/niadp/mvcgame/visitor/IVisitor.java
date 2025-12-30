package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

public interface IVisitor {
    void visitCannon(AbsCannon cannon);
    void visitMissile(AbsMissile missile);
    void visitNormalEnemy(AbsEnemy enemy);
    void visitLargeEnemy(AbsEnemy enemy);
    void visitGameInfo(GameInfo gameInfo);
    void visitParticle(Particle particle);
}

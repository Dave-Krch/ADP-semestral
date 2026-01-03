package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameKeys;
import cz.cvut.fit.niadp.mvcgame.model.Collider;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class GameInfo extends GameObject  {
    private int shotCount = 0;
    private int score = 0;

    private Collider collider = new Collider(   MvcGameConfig.ENEMY_SPAWN_MIN_X,
                                                MvcGameConfig.ENEMY_SPAWN_MIN_Y,
                                                MvcGameConfig.ENEMY_SPAWN_MAX_X,
                                                MvcGameConfig.ENEMY_SPAWN_MAX_Y );

    public final String legend = MvcGameKeys.LEGEND;

    private AbsCannon cannon;

    public GameInfo(Position position, AbsCannon canon) {
        this.position = position;
        this.cannon = canon;
    }

    public GameInfo(Position position, int score, int shotCount, AbsCannon cannon) {
        this.position = position;
        this.score = score;
        this.shotCount = shotCount;
        this.cannon = cannon;
    }

    public GameInfo clone() {
        return new GameInfo(this.position, this.score, this.shotCount, this.cannon);
    }

    public Collider getCollider() {
        return collider;
    }

    public void incMissilesShot() { shotCount++; }

    public int getShotCount() {
        return shotCount;
    }

    public void setShotCount(int shotCount) {
        this.shotCount = shotCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int count) {
        this.score += count;
    }

    public double getCannonAngle() {
        return cannon.getAngle();
    }

    public int getCannonPower() {
        return cannon.getPower();
    }

    @Override
    public void acceptVisitor(IVisitor visitor) { visitor.visitGameInfo(this); }


}

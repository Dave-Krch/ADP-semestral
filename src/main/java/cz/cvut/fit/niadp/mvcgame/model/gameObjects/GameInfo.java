package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class GameInfo extends GameObject  {
    private int shotCount = 0;
    private int score = 0;

    public GameInfo(Position position) {
        this.position = position;
    }

    public GameInfo(Position position, int score, int shotCount) {
        this.position = position;
        this.score = score;
        this.shotCount = shotCount;
    }

    public GameInfo clone() {
        return new GameInfo(this.position, this.score, this.shotCount);
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

    @Override
    public void acceptVisitor(IVisitor visitor) { visitor.visitGameInfo(this); }


}

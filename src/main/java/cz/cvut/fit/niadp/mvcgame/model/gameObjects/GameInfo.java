package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class GameInfo extends GameObject{

    public Position scorePos;
    public Position shotCountPos;

    public GameInfo(Position position) {
        this.position = position;


    }

    private int missilesShot = 0;

    private int score = 0;

    public void incMissilesShot() { missilesShot++; }

    public int getMissilesShot() {
        return missilesShot;
    }

    public void setMissilesShot(int missilesShot) {
        this.missilesShot = missilesShot;
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

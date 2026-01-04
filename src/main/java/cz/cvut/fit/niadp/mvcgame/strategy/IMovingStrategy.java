package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.io.Serializable;

public interface IMovingStrategy extends Serializable {
    void updatePosition(AbsMissile missile);
}

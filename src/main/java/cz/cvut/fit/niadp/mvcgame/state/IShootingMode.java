package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;

import java.io.Serializable;

public interface IShootingMode extends Serializable {
    String getName();
    void shoot(AbsCannon cannon);
}

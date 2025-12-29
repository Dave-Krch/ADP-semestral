package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public interface IModelBuilder {
    public void reset();
    public void createNormalEnemies(int count);

    public void createLargeEnemies(int count);

    public GameModel getProduct();
}

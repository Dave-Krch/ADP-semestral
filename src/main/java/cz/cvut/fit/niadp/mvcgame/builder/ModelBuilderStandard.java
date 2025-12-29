package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ModelBuilderStandard implements IModelBuilder{

    private GameModel model;

    public ModelBuilderStandard() {
        this.reset();
    }

    @Override
    public void reset() {
        this.model = new GameModel();
    }

    @Override
    public void createNormalEnemies(int count) {
        //TODO
    }

    @Override
    public void createLargeEnemies(int count) {
        //TODO
    }

    @Override
    public GameModel getProduct() {
        return model;
    }
}

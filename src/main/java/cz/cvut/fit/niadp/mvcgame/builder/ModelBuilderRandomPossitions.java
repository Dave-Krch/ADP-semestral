package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ModelBuilderRandomPossitions implements IModelBuilder {

    private GameModel model;

    public ModelBuilderRandomPossitions() {
        this.reset();
    }

    @Override
    public void reset() {
        model = new GameModel();
    }

    @Override
    public void createNormalEnemies(int count) {
        //todo:
    }

    @Override
    public void createLargeEnemies(int count) {
        //todo:
    }

    @Override
    public GameModel getProduct() {
        return model;
    }
}

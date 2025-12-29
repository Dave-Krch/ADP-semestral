package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.EnemyStandardA;

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
        model.getEnemies().add(new EnemyStandardA(new Position(500, 500)));
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

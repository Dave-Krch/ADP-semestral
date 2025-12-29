package cz.cvut.fit.niadp.mvcgame.command.gameCommands;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class StoreGameSnapshotCommand extends AbstractGameCommand {

    public StoreGameSnapshotCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        CareTaker.getInstance().createMemento();
    }
}

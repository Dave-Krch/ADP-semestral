package cz.cvut.fit.niadp.mvcgame.command.gameCommands;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class LoadGameSnapshotFromFileCommand extends AbstractGameCommand {

    public LoadGameSnapshotFromFileCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.load();
    }
}

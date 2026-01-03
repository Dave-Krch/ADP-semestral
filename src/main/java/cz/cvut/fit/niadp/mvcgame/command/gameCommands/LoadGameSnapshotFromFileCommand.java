package cz.cvut.fit.niadp.mvcgame.command.gameCommands;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class LoadGameSnapshotFromFileCommand extends AbstractGameCommand {

    private String path;

    public LoadGameSnapshotFromFileCommand(IGameModel model, String path) {
        this.model = model;
        this.path = path;
    }

    @Override
    protected void execute() {
        if (path != null) {
            model.loadFromFile(path);
        }
    }
}

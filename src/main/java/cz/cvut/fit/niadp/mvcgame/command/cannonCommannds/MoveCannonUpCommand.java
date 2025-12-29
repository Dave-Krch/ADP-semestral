package cz.cvut.fit.niadp.mvcgame.command.cannonCommannds;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class MoveCannonUpCommand extends AbstractGameCommand {

    public MoveCannonUpCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.moveCannonUp();
    }
}

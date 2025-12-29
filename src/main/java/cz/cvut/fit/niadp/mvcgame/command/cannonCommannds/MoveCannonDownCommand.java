package cz.cvut.fit.niadp.mvcgame.command.cannonCommannds;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class MoveCannonDownCommand extends AbstractGameCommand {

    public MoveCannonDownCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.moveCannonDown();
    }
}

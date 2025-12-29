package cz.cvut.fit.niadp.mvcgame.command.cannonCommannds;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class PowerUpCannonCommand extends AbstractGameCommand {

    public PowerUpCannonCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.cannonPowerUp();
    }
}

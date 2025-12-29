package cz.cvut.fit.niadp.mvcgame.command.cannonCommannds;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ShootCannonCommand extends AbstractGameCommand {

    public ShootCannonCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.cannonShoot();
    }
}

package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.command.cannonCommannds.*;
import cz.cvut.fit.niadp.mvcgame.command.gameCommands.*;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameKeys;
import cz.cvut.fit.niadp.mvcgame.view.GameView;

public class GameController {

    private final IGameModel model;
    private final GameView view;

    public GameController(IGameModel model, GameView gameView) {
        this.model = model;
        this.view = gameView;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameKeys.UP_KEY:
                    model.registerCommand(new MoveCannonUpCommand(model));
                    break;
                case MvcGameKeys.DOWN_KEY:
                    model.registerCommand(new MoveCannonDownCommand(model));
                    break;
                case MvcGameKeys.SHOOT_KEY:
                    model.registerCommand(new ShootCannonCommand(model));
                    break;
                case MvcGameKeys.AIM_UP_KEY:
                    model.registerCommand(new AimUpCannonCommand(model));
                    break;
                case MvcGameKeys.AIM_DOWN_KEY:
                    model.registerCommand(new AimDownCannonCommand(model));
                    break;
                case MvcGameKeys.POWER_UP_KEY:
                    model.registerCommand(new PowerUpCannonCommand(model));
                    break;
                case MvcGameKeys.POWER_DOWN_KEY:
                    model.registerCommand(new PowerDownCannonCommand(model));
                    break;
                case MvcGameKeys.TOGGLE_MOVING_STRATEGY_KEY:
                    model.registerCommand(new ToggleMovingStrategyCommand(model));
                    break;
                case MvcGameKeys.TOGGLE_SHOOTING_MODE_KEY:
                    model.registerCommand(new ToggleShootingModeCommand(model));
                    break;
                case MvcGameKeys.STORE_GAME_SNAPSHOT_KEY:
                    model.registerCommand(new StoreGameSnapshotCommand(model));
                    //pressedKeysCodes.remove(MvcGameKeys.STORE_GAME_SNAPSHOT_KEY);
                    break;
                case MvcGameKeys.LOAD_GAME_SNAPSHOT_KEY:
                    String path = view.openFileDialog();
                    model.registerCommand(new LoadGameSnapshotFromFileCommand(model, path));
                    //pressedKeysCodes.remove(MvcGameKeys.LOAD_GAME_SNAPSHOT_KEY);
                    break;
                case MvcGameKeys.RESTORE_GAME_SNAPSHOT_KEY:
                    model.registerCommand(new RestoreGameSnapshotCommand(model));
                    break;
                case MvcGameKeys.UNDO_LAST_COMMAND:
                    model.undoLastCommand();
                    break;
                case MvcGameKeys.DEBUG_MODE_KEY:
                    view.switchDrawerModes();
                    break;
                case MvcGameKeys.EXIT_KEY:
                    System.exit(0);
                    break;
                default:
                    //nothing
            }
        }
        model.update();
        pressedKeysCodes.clear();
    }
}

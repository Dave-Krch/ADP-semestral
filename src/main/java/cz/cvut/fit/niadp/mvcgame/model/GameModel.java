package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;

import java.util.HashSet;
import java.util.Set;

public class GameModel implements IObservable {

    private final Cannon canon;
    private final Set<IObserver> observers;

    public GameModel() {
        canon = new Cannon(new Position (MvcGameConfig.CANON_POS_X, MvcGameConfig.CANON_POS_Y ));
        observers = new HashSet<IObserver>();
    }

    public Position getCanonPosition() {
        return canon.getPosition();
    }

    public void update() {
    }

    public void moveLogoUp() {
        canon.moveUp();
        notifyObservers();
    }

    public void moveLogoDown() {
        canon.moveDown();
        notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(IObserver::update);
    }
}

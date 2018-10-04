package com.hypeofpipe.sprinters.hypeengine.stages;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hypeofpipe.sprinters.game.stages.Game;
import com.hypeofpipe.sprinters.game.stages.StageSwitcher;
import com.hypeofpipe.sprinters.hypeengine.Screen;
import com.hypeofpipe.sprinters.hypeengine.managers.EntityManager;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Volodymyr on 11/3/2017.
 */

public class StageSwitcherUtil implements Observer{

    protected static StageSwitcher
            stageSwitcher;
    protected Stage activeStage;

    public static StageSwitcher getInstance() {
        if (stageSwitcher == null){
            stageSwitcher =
                    new StageSwitcher();
        }
        return stageSwitcher;
    }

    public void start() {
        EntityManager.getInstance().start(
                EntityManager.getInstance().all
        );
    }

    public void pause() {
        EntityManager.getInstance().pause(
                EntityManager.getInstance().all
        );
    }

    public void resume() {
        EntityManager.getInstance().resume(
                EntityManager.getInstance().all
        );
    }

    public void switchStage(Stage stage){
        pause();
        activeStage = stage;
        activeStage.setViewport(
                Screen.getViewport()
        );
        EntityManager.
                getInstance().
                changeActiveStage(
                        activeStage
                );
        resume();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Stage){
            switchStage((Stage) o);
        }
    }

    public ImmutableArray<Entity> getEntities(){
        return activeStage.getEntityImmutableArray();
    }

    public Stage getActiveStage() {
        return activeStage;
    }
}

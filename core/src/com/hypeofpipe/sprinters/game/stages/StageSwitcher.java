package com.hypeofpipe.sprinters.game.stages;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hypeofpipe.sprinters.hypeengine.Screen;
import com.hypeofpipe.sprinters.hypeengine.managers.EntityManager;
import com.hypeofpipe.sprinters.hypeengine.stages.Stage;
import com.hypeofpipe.sprinters.hypeengine.stages.StageSwitcherUtil;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Volodymyr on 10/14/2017.
 */

public class StageSwitcher extends StageSwitcherUtil {

    public static StageSwitcher getInstance() {
        if (stageSwitcher == null){
            stageSwitcher =
                    new StageSwitcher();
        }
        return stageSwitcher;
    }

    public void start() {
        switchStage(new Menu());
        super.start();
    }

}

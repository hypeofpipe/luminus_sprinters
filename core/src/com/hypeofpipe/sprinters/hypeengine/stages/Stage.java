package com.hypeofpipe.sprinters.hypeengine.stages;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

import com.hypeofpipe.sprinters.game.components.StageComponent;
import com.hypeofpipe.sprinters.game.stages.StageSwitcher;
import com.hypeofpipe.sprinters.hypeengine.utils.Observable;

/**
 * Created by Volodymyr on 10/14/2017.
 */

public class Stage extends com.badlogic.gdx.scenes.scene2d.Stage{

    protected Array<Entity> entityArray =
            new Array<Entity>();
    protected Observable observable =
            new Observable();
    protected Array<Actor> resizableActors =
            new Array<Actor>();
    protected Entity thisEntity =
            new Entity();

    protected void init(Stage stage){
        observable.addObserver(
                StageSwitcher.getInstance()
        );
        Gdx.input.setInputProcessor(this);
        thisEntity.add(new StageComponent(
                this
        ));
        addEntities(thisEntity);
    }

    protected void addEntities(Entity... entities){
            entityArray.addAll(entities);
    }

    public void resize(int width, int height){
        getViewport().update(
                width, height
        );

        for (Actor a:this.getResizeActors()) {
            a.setSize(
                    width,
                    height
            );
            if (a instanceof Table){
                ((Table) a).setFillParent(true);
            }
        }
    }

    protected Array<Actor> getResizeActors(){
        return resizableActors;
    }

    public ImmutableArray<Entity> getEntityImmutableArray(){
       return new ImmutableArray<>(entityArray);
    }
}

package com.hypeofpipe.sprinters.game.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.hypeofpipe.sprinters.game.components.StageComponent;

/**
 * Created by Volodymyr on 11/3/2017.
 */

public class StageDrawSystem extends EntitySystem{

    private ImmutableArray<Entity> entities;

    private ComponentMapper<StageComponent> sm =
            ComponentMapper.getFor(StageComponent.class);

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        StageComponent.class
                ).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            StageComponent component = sm.get(entity);

            component.stage.draw();
        }
    }

}

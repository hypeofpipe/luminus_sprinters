package com.hypeofpipe.sprinters.game.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.hypeofpipe.sprinters.game.components.ActorComponent;
import com.hypeofpipe.sprinters.game.components.StageComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;
import com.hypeofpipe.sprinters.game.stages.Game;
import com.hypeofpipe.sprinters.game.stages.StageSwitcher;

/**
 * Created by Volodymyr on 10/29/2017.
 */

public class ActorEntitySystem extends EntitySystem{

    private ImmutableArray<Entity> entities;

    private ComponentMapper<ActorComponent> am =
            ComponentMapper.getFor(ActorComponent.class);
    private ComponentMapper<TransformComponent> tm =
            ComponentMapper.getFor(TransformComponent.class);

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        ActorComponent.class,
                        TransformComponent.class
                ).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            ActorComponent actor =
                    am.get(entity);
            TransformComponent transform =
                    tm.get(entity);

            actor.actor.setPosition(
                    transform.posX,
                    transform.posY
            );
            actor.actor.setRotation(
                    transform.degree
            );
            actor.actor.setSize(
                    transform.sizeX,
                    transform.sizeY
            );


        }
    }

}

package com.hypeofpipe.sprinters.game.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hypeofpipe.sprinters.game.components.SpriteComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;

/**
 * Created by Volodymyr on 10/16/2017.
 */

public class SpriteRenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private Batch batch;

    private ComponentMapper<TransformComponent> tm
            = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<SpriteComponent> sm
            = ComponentMapper.getFor(SpriteComponent.class);

    public SpriteRenderSystem(Batch batch) {
        this.batch = batch;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        TransformComponent.class,
                        SpriteComponent.class
                ).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            TransformComponent transform = tm.get(entity);
            SpriteComponent sprite = sm.get(entity);

            batch.draw(
                    sprite.sprite,
                    transform.posX,
                    transform.posY
            );
        }
    }

}

package com.hypeofpipe.sprinters.game.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Transform;
import com.hypeofpipe.sprinters.game.Constants;
import com.hypeofpipe.sprinters.game.components.ActorComponent;
import com.hypeofpipe.sprinters.game.components.ColliderComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;
import com.hypeofpipe.sprinters.game.factories.ColliderFactory;

/**
 * Created by Volodymyr on 11/11/2017.
 */

public class ColliderTransformSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<TransformComponent> tm =
            ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<ColliderComponent> cm =
            ComponentMapper.getFor(ColliderComponent.class);

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        TransformComponent.class,
                        ColliderComponent.class
                ).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            ColliderComponent collider =
                    cm.get(entity);
            TransformComponent transform =
                    tm.get(entity);

            transform.posX =
                    (collider.body.getTransform().getPosition().x) -
                            transform.sizeX / 2;
            transform.posY =
                    (collider.body.getTransform().getPosition().y) -
                            transform.sizeY / 2;
            transform.degree =
                    (float) Math.toDegrees(collider.body.getAngle());

        }
    }

}

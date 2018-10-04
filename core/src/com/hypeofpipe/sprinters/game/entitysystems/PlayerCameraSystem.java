package com.hypeofpipe.sprinters.game.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.hypeofpipe.sprinters.game.components.CameraComponent;
import com.hypeofpipe.sprinters.game.components.ColliderComponent;
import com.hypeofpipe.sprinters.game.components.StageComponent;

/**
 * Created by Volodymyr on 11/20/2017.
 */

public class PlayerCameraSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<CameraComponent> cm =
            ComponentMapper.getFor(CameraComponent.class);
    private ComponentMapper<ColliderComponent> clm =
            ComponentMapper.getFor(ColliderComponent.class);

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        CameraComponent.class,
                        ColliderComponent.class
                ).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            CameraComponent cameraComponent =
                    cm.get(entity);
            ColliderComponent colliderComponent =
                    clm.get(entity);

            cameraComponent.camera.position.x =
                    colliderComponent.body.getTransform().getPosition().x;
            cameraComponent.camera.position.y =
                    colliderComponent.body.getTransform().getPosition().y;
            cameraComponent.camera.rotate(
                    new Vector3(
                            0,
                            0,
                            cameraComponent.camera.position.z
                            ),
                    (float) Math.toDegrees(colliderComponent.body.getAngle()
                    )
            );
        }
    }
}

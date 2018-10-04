package com.hypeofpipe.sprinters.game.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.hypeofpipe.sprinters.game.components.ColliderComponent;
import com.hypeofpipe.sprinters.game.components.InputComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;
import com.hypeofpipe.sprinters.hypeengine.Screen;

/**
 * Created by Volodymyr on 10/22/2017.
 */

public class InputSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<InputComponent> inM =
            ComponentMapper.getFor(InputComponent.class);
    private Camera cam;
    private Vector3 cursor;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        InputComponent.class
                ).get());
        cam = Screen.getCamera();
        cursor = cam.unproject(new Vector3(
                Gdx.input.getX(),
                Gdx.input.getY(),
                0
        ));
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            InputComponent inputComponent =
                    inM.get(entity);

            }

        }
}



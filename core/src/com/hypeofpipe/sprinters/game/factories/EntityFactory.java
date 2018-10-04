package com.hypeofpipe.sprinters.game.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.hypeofpipe.sprinters.game.components.ActorComponent;
import com.hypeofpipe.sprinters.game.components.CameraComponent;
import com.hypeofpipe.sprinters.game.components.ColliderComponent;
import com.hypeofpipe.sprinters.game.components.InputComponent;
import com.hypeofpipe.sprinters.game.components.StageComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;
import com.hypeofpipe.sprinters.game.io.Controller;
import com.hypeofpipe.sprinters.game.stages.Game;
import com.hypeofpipe.sprinters.hypeengine.Screen;

import java.util.Random;

/**
 * Created by Volodymyr on 10/15/2017.
 */

public class EntityFactory {

    public static Entity createCar(
            Texture t
            )
    {
        Entity car =
                new Entity();

        ActorComponent ac =
                new ActorComponent(
                        new Image(
                                t
                        )
                );
        TransformComponent tc =
                new TransformComponent(
                        0,
                        0,
                        0,
                        300,
                        500,
                        100f
                );


        ac.actor.setPosition(
                tc.posX,
                tc.posY
        );

        ac.actor.setRotation(
                tc.degree
        );

        ac.actor.setSize(
                tc.sizeX,
                tc.sizeY
        );

        ColliderComponent cc =
                ColliderFactory.createCarColliderComponent(
                        ac,
                        tc,
                        100f
                );

        CameraComponent camC =
                new CameraComponent(
                        Screen.getCamera()
                );

        ac.actor.setOrigin(
                ac.actor.getWidth() / 2,
                ac.actor.getHeight() / 2
        );

        car.add(
                ac
        ).add(
                tc
        ).add(
                cc
        ).add(
                camC
        );

        return car;
    }

}

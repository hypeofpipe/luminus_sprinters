package com.hypeofpipe.sprinters.game.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hypeofpipe.sprinters.game.components.ActorComponent;
import com.hypeofpipe.sprinters.game.components.ColliderComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;
import com.hypeofpipe.sprinters.game.stages.StageSwitcher;
import com.hypeofpipe.sprinters.hypeengine.managers.EntityManager;

/**
 * Created by Volodymyr on 11/7/2017.
 */

public class ColliderFactory {

    public static ColliderComponent createCarColliderComponent(
            ActorComponent actorComponent,
            TransformComponent transformComponent,
            float mass
    )
    {
        World world =
                EntityManager.getInstance().getWorld();

        ColliderComponent component =
                new ColliderComponent();

        component.bodyDef = new BodyDef();
        component.bodyDef.position.set(
                transformComponent.getPosVec2()
        );
        component.bodyDef.angle =
                transformComponent.degree;
        component.bodyDef.type =
                BodyDef.BodyType.DynamicBody;

        component.body =
                world.createBody(
                        component.bodyDef
                );

        PolygonShape shape =
                new PolygonShape();
        shape.setAsBox(
                actorComponent.actor.getWidth() / 2,
                actorComponent.actor.getHeight() / 2
        );

        float areaOfShape =
                actorComponent.actor.getWidth()
                        * actorComponent.actor.getHeight();

        component.fixtureDef =
                new FixtureDef();
        component.fixtureDef.shape =
                shape;
        component.fixtureDef.density =
                mass/areaOfShape;
        component.fixtureDef.friction =
                0.1f;
        component.fixtureDef.restitution =
                0.1f;

        component.fixture = component.body.createFixture(
                component.fixtureDef
        );

        shape.dispose();

        return component;
    }

}

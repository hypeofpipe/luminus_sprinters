package com.hypeofpipe.sprinters.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.hypeofpipe.sprinters.game.factories.ColliderFactory;

/**
 * Created by Volodymyr on 11/7/2017.
 */

public class ColliderComponent implements Component {

    public BodyDef bodyDef;
    public BodyDef.BodyType bodyType;
    public Shape shape;
    public Body body;
    public FixtureDef fixtureDef;
    public Fixture fixture;

    public ColliderComponent()
    {

    }

    

}

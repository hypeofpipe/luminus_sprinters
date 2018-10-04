package com.hypeofpipe.sprinters.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Volodymyr on 10/21/2017.
 */

public class ActorComponent implements Component{

    public Actor actor =
            new Actor();

    public ActorComponent(
            Actor a
    ){
        actor = a;
    }

}

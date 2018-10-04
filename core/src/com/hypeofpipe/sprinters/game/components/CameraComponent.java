package com.hypeofpipe.sprinters.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Camera;

/**
 * Created by Volodymyr on 11/20/2017.
 */

public class CameraComponent implements Component {

    public Camera camera;

    public CameraComponent(
            Camera camera
    ){
        this.camera = camera;
    }


}

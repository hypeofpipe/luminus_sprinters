package com.hypeofpipe.sprinters.hypeengine.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Hype on 12/28/2017.
 */

public class Stage2DHelper {

    public static SpriteDrawable PathToDrawable(String texturePath) {
        return new SpriteDrawable(
                new Sprite(
                        new Texture(
                                texturePath
                        )
                )
        );
    }

    public static Image PathToImage(String texturePath) {
        return new Image(
                new Texture(
                        texturePath
                )
        );
    }

    public static Vector2 unproject(Camera camera, Vector2 unproject) {
        Vector3 vector3 = camera.unproject(
                new Vector3(
                        unproject.x,
                        unproject.y,
                        0f
                )
        );

        return new Vector2(
                vector3.x,
                vector3.y
        );
    }
}

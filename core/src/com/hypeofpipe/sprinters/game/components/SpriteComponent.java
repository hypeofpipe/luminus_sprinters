package com.hypeofpipe.sprinters.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Volodymyr on 10/15/2017.
 */

public class SpriteComponent implements Component {
    public Sprite sprite
            = new Sprite(
                    new Texture(
                            "badlogic.jpg"
                    )
    );

    public SpriteComponent(
            Sprite sprite
    )
    {
      this.sprite = sprite;
    }

}

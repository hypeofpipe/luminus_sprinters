package com.hypeofpipe.sprinters.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Volodymyr on 10/22/2017.
 */

public class TransformComponent implements Component {

    public float posX = 0;
    public float posY = 0;
    private Vector2 posVec2 =
            new Vector2(
                    posX,
                    posY
            );
    public float degree = 0f;
    public float sizeX = 1f;
    public float sizeY = 1f;
    public float velocity = 0f;

    public TransformComponent(float posX,
                              float posY,
                              float degree,
                              float sizeX,
                              float sizeY,
                              float velocity)
    {
        this.posX = posX;
        this.posY = posY;
        this.degree = degree;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.velocity = velocity;
    }

    public TransformComponent(float posX,
                              float posY,
                              float degree,
                              float sizeX,
                              float sizeY)
    {
        this.degree = degree;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posVec2 =
                new Vector2(
                        posX,
                        posY
                );
        this.posX = posVec2.x;
        this.posY = posVec2.y;
    }

    public Vector2 getPosVec2() {
        return posVec2;
    }

    public void setPosVec2(Vector2 posVec2) {
        this.posVec2 = posVec2;
    }
}

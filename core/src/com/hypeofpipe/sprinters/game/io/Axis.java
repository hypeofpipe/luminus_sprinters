package com.hypeofpipe.sprinters.game.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.hypeofpipe.sprinters.game.Resources;
import com.hypeofpipe.sprinters.hypeengine.Screen;
import com.hypeofpipe.sprinters.hypeengine.utils.MathHelper;
import com.hypeofpipe.sprinters.hypeengine.utils.Stage2DHelper;

import javax.swing.GroupLayout;

import static com.hypeofpipe.sprinters.game.io.Axis.AXIS_TYPE.UP_DOWN;

/**
 * Created by Hype on 12/28/2017.
 */

public class Axis extends Group {

    public enum AXIS_TYPE{
        UP_DOWN, LEFT_RIGHT
    }

    private Actor ball;
    private Actor axisLine;
    private Actor pointer;
    private AXIS_TYPE axis_type;

    private float axisSensitivity = 0.5f;

    public Axis(AXIS_TYPE axis_type){
        this.axis_type = axis_type;

        ball = Stage2DHelper.PathToImage(
                Resources.BALL
        );

        switch (axis_type){
            case UP_DOWN:
                axisLine = Stage2DHelper.PathToImage(
                        Resources.Y_AXIS
                );
                break;
            case LEFT_RIGHT:
                axisLine = Stage2DHelper.PathToImage(
                        Resources.X_AXIS
                );
                break;
        }

        pointer = Stage2DHelper.PathToImage(
                Resources.POINTER
        );

        addActor(ball);
        addActor(axisLine);
        addActor(pointer);

        debugIt();
        adjust();
        addListeners(axis_type);
    }

    private void adjust(){
        pointer.setPosition(
                ball.getWidth() / 2 - pointer.getWidth() / 2,
                ball.getHeight() / 2 - pointer.getHeight() / 2
        );
    }

    private void addListeners(AXIS_TYPE axis_type) {
        switch (axis_type) {
            case LEFT_RIGHT:
                pointer.addCaptureListener(
                        new ClickListener() {
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                Vector2 updPos =
                                        updatedPointerPosition(x, getPointer().getY());
                                getPointer().setPosition(
                                        updPos.x,
                                        updPos.y
                                );
                                return super.touchDown(event, x, y, pointer, button);
                            }

                            @Override
                            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                                super.touchDragged(event, x, y, pointer);
                                Vector2 updPos =
                                        updatedPointerPosition(x, getPointer().getY());
                                getPointer().setPosition(
                                        updPos.x,
                                        updPos.y
                                );
                            }
                        }
                );
                break;
            case UP_DOWN:
                pointer.addCaptureListener(
                        new ClickListener() {
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                Vector2 updPos =
                                        updatedPointerPosition(getPointer().getX(), y);
                                getPointer().setPosition(
                                        updPos.x,
                                        updPos.y
                                );
                                return super.touchDown(event, x, y, pointer, button);
                            }

                            @Override
                            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                                super.touchDragged(event, x, y, pointer);
                                Vector2 updPos =
                                        updatedPointerPosition(getPointer().getX(), y);
                                getPointer().setPosition(
                                        updPos.x,
                                        updPos.y
                                );
                            }
                        }
                );
                break;
        }
    }

    private void debugIt(){
        ball.debug();
        axisLine.debug();
        pointer.debug();
    }

    private Vector2 updatedPointerPosition(float x, float y){
        Vector2 ret = new Vector2();

        if (x == getPointer().getX()){
            ret.set(
                    x,
                    y * axisSensitivity
            );
        } else if (y == getPointer().getY()){
            ret.set(
                    x * axisSensitivity,
                    y
            );
        }

        if (ret.x > axisLine.getWidth() - getPointer().getWidth()){
            ret.set(
                    axisLine.getWidth() - getPointer().getWidth(),
                    ret.y
            );
        } else if (ret.x < axisLine.getX()){
            ret.set(
                    axisLine.getX(),
                    ret.y
            );
        } else if (ret.y > axisLine.getHeight() - getPointer().getHeight()){
            ret.set(
                    ret.x,
                    axisLine.getHeight() - getPointer().getHeight()
            );
        } else if (ret.y < axisLine.getY()){
            ret.set(
                    ret.x,
                    axisLine.getY()
            );
        }

        return ret;
    }

    public Actor getBall() {
        return ball;
    }

    public void setBall(Actor ball) {
        this.ball = ball;
    }

    public Actor getAxisLine() {
        return axisLine;
    }

    public void setAxisLine(Actor axisLine) {
        this.axisLine = axisLine;
    }

    public Actor getPointer() {
        return pointer;
    }

    public void setPointer(Actor pointer) {
        this.pointer = pointer;
    }

    //TO-DO: Fix this please, it's not working and start position's got value -0.0625
    public float getValue(){
        float retV = 0f;
        //max pos of pointer and that's 1.0
        //Because we need to divide it by 2, and before that find a difference between ball's values and pointer
        float maxHeight = (ball.getHeight() - getPointer().getHeight()) / 2;
        float maxWidth = (ball.getWidth() - getPointer().getWidth()) / 2;
        switch (axis_type){
            case UP_DOWN:
                //getting delta between pointer and middle of ball
                float deltaY = pointer.getY(Align.center) -  (ball.getHeight() / 2);
                //transforming it in (0,1) range
                float maxTransformedY = 1 / maxHeight;
                retV = maxTransformedY * deltaY;
                break;
            case LEFT_RIGHT:
                //getting delta between pointer and middle of ball
                //IMPORTANT: Using 'Align.center' is necessary for correct calculations
                float deltaX = pointer.getX(Align.center) - (ball.getWidth() / 2);
                //transforming it in (0,1) range
                float maxTransformedX = 1 / maxWidth;
                retV = maxTransformedX * deltaX;
                break;
        }
        Gdx.app.log(" " + Axis.class, " " + retV);
        return retV;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        getValue();
    }
}

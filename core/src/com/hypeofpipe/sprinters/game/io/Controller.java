package com.hypeofpipe.sprinters.game.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hypeofpipe.sprinters.hypeengine.Screen;

/**
 * Created by Volodymyr on 11/27/2017.
 */

public class Controller extends Group{

    private Axis xAxis;
    private Axis yAxis;

    public Controller(){
        xAxis = new Axis(Axis.AXIS_TYPE.LEFT_RIGHT);
        yAxis = new Axis(Axis.AXIS_TYPE.UP_DOWN);

        addActor(xAxis);
        addActor(yAxis);
    }
}

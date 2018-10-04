package com.hypeofpipe.sprinters.game.stages;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.hypeofpipe.sprinters.game.components.ActorComponent;
import com.hypeofpipe.sprinters.game.components.ColliderComponent;
import com.hypeofpipe.sprinters.game.components.InputComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;
import com.hypeofpipe.sprinters.game.factories.EntityFactory;
import com.hypeofpipe.sprinters.game.io.Axis;
import com.hypeofpipe.sprinters.game.io.Controller;
import com.hypeofpipe.sprinters.hypeengine.Screen;
import com.hypeofpipe.sprinters.hypeengine.managers.EntityManager;
import com.hypeofpipe.sprinters.hypeengine.stages.Stage;
import com.hypeofpipe.sprinters.hypeengine.utils.Stage2DHelper;

/**
 * Created by Volodymyr on 10/14/2017.
 */

public class Game extends Stage {

    Entity car;
    Color color;
    Image image;

    public Game(){
        init(this);

        createUI();
    }

    private void createUI(){
        Controller controller = new Controller();

        addActor(controller);

        controller.setSize(
                getViewport().getWorldWidth(),
                getViewport().getWorldHeight()
        );

        controller.debug();
    }
}

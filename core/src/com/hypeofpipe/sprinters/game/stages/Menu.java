package com.hypeofpipe.sprinters.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hypeofpipe.sprinters.hypeengine.Screen;
import com.hypeofpipe.sprinters.hypeengine.stages.Stage;

/**
 * Created by Volodymyr on 10/22/2017.
 */

public class Menu extends Stage {

    public Menu(){
        init(this);

        Table table =
                new Table();
        getResizeActors().add(table);
        table.setSize(
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );
        Image i =
                new Image(
                        new Texture("badlogic.jpg"
                        )
                );

        i.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                observable.setChanged();
                observable.notifyObservers(new Game());
            }
        });

        table.add(i);

        addActor(
                table
        );
    }

}

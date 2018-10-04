package com.hypeofpipe.sprinters.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Volodymyr on 10/21/2017.
 */

public class StageComponent implements Component {

    public Stage stage = new Stage();

    public StageComponent (
            Viewport v
    )
    {
        stage.setViewport(v);
    }

    public StageComponent(
            Viewport v,
            Stage s
    ){
        stage = s;
        stage.setViewport(
                v
        );
    }

    public StageComponent(Stage s){
        stage = s;
    }
}

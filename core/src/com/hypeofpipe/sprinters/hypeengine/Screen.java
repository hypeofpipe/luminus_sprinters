package com.hypeofpipe.sprinters.hypeengine;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hypeofpipe.sprinters.game.components.InputComponent;
import com.hypeofpipe.sprinters.game.stages.StageSwitcher;
import com.hypeofpipe.sprinters.hypeengine.managers.EntityManager;
import com.hypeofpipe.sprinters.hypeengine.stages.Stage;

/**
 * Created by Volodymyr on 10/14/2017.
 */

public class Screen implements com.badlogic.gdx.Screen {

    private final static int WORLD_SIZE_X = 1080;
    private static final int WORLD_SIZE_Y = 1920;

    private static Batch batch;
    private static Viewport viewport;
    private static OrthographicCamera camera;
    private EntityManager entityManager;
    private StageSwitcher stageSwitcher;

    private static Screen screen;

    public static Screen getInstance() {
        if (screen == null) {
            screen = new Screen();
        }
        return screen;
    }

    public Screen(){
        batch = new SpriteBatch();

        camera = new OrthographicCamera();

        viewport = new StretchViewport(
                WORLD_SIZE_X,
                WORLD_SIZE_Y,
                camera);
        viewport.apply();

        camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());

        stageSwitcher = StageSwitcher.getInstance();
        entityManager = EntityManager.getInstance();

        entityManager.setBatch(batch);
        stageSwitcher.start();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();

        batch.setProjectionMatrix(
                camera.combined
        );

        Gdx.gl.glClearColor(
                0f, 0f, 1f, 0f
        );
        Gdx.gl.glClear(
                GL20.GL_COLOR_BUFFER_BIT
        );

        entityManager.update(
                entityManager.all
        );

        batch.begin();
        entityManager.draw(delta);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        StageSwitcher.getInstance().getActiveStage().resize(width, height);
        Gdx.app.log("Resize: ", "Actual resize W/H : " + width + ", " + height + "; Resized stage " + StageSwitcher.getInstance().getActiveStage() + StageSwitcher.getInstance().getActiveStage().getWidth() + ", " + StageSwitcher.getInstance().getActiveStage().getHeight() + "Gdx graphics: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public static Viewport getViewport() {
        return viewport;
    }

    public static Batch getBatch() {
        return batch;
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }


}

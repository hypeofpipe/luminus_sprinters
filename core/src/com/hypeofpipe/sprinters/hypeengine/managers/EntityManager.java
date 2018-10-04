package com.hypeofpipe.sprinters.hypeengine.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hypeofpipe.sprinters.game.Constants;
import com.hypeofpipe.sprinters.game.components.StageComponent;
import com.hypeofpipe.sprinters.game.components.TransformComponent;
import com.hypeofpipe.sprinters.game.entitysystems.ActSystem;
import com.hypeofpipe.sprinters.game.entitysystems.ActorEntitySystem;
import com.hypeofpipe.sprinters.game.entitysystems.ColliderTransformSystem;
import com.hypeofpipe.sprinters.game.entitysystems.InputSystem;
import com.hypeofpipe.sprinters.game.entitysystems.PlayerCameraSystem;
import com.hypeofpipe.sprinters.game.entitysystems.StageDrawSystem;
import com.hypeofpipe.sprinters.game.stages.StageSwitcher;
import com.hypeofpipe.sprinters.hypeengine.Screen;
import com.hypeofpipe.sprinters.hypeengine.interfaces.Tickable;
import com.hypeofpipe.sprinters.hypeengine.stages.Stage;

/**
 * Created by Volodymyr on 10/15/2017.
 */

public class EntityManager implements Tickable {

    public ImmutableArray<Entity> all;
    private static EntityManager entityManager;
    private Engine engine;
    private boolean engineSwitcher = false;
    private Batch batch;
    private World world;
    private StageDrawSystem drawSystem;
    private Box2DDebugRenderer box2DDebugRenderer;

    private EntityManager(){
        engine = new Engine();
        drawSystem = new StageDrawSystem();
        drawSystem.addedToEngine(engine);
        world = new World(
            Constants.GRAVITY,
                false
        );
        box2DDebugRenderer =
                new Box2DDebugRenderer();
    }

    public static EntityManager getInstance() {
        if (entityManager == null){
            entityManager = new EntityManager();
        }
        return entityManager;
    }


    @Override
    public void start(ImmutableArray<Entity> entities) {
        if (batch != null){
            setEngineSwitcher(true);
            init(true);
        }
    }

    @Override
    public void pause(ImmutableArray<Entity> entities) {
        setEngineSwitcher(false);
    }

    @Override
    public void resume(ImmutableArray<Entity> entities) {
        if (batch != null){
            setEngineSwitcher(true);
            init(false);
        }
    }

    @Override
    public void update(ImmutableArray<Entity> entities) {
        if (engineSwitcher) {
            engine.update(
                    Gdx.graphics.getDeltaTime()
            );
            doPhysicsStep(
                    Gdx.graphics.getDeltaTime()
            );
        }
    }

    public void draw(float delta){
        if (engineSwitcher){
            drawSystem.update(delta);
            box2DDebugRenderer.render(
                    world,
                    Screen.getCamera().combined
            );
        }
    }


    public boolean isEngineSwitcher() {
        return engineSwitcher;
    }

    public void setEngineSwitcher(boolean engineSwitcher) {
        this.engineSwitcher = engineSwitcher;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    private void init(boolean firstTime){
        if (firstTime){
            engine.addSystem(new InputSystem());
            engine.addSystem(new ActSystem());
            engine.addSystem(new ActorEntitySystem());
            engine.addSystem(new ColliderTransformSystem());
            engine.addSystem(new PlayerCameraSystem());
        } else {
            for (EntitySystem es:engine.getSystems()) {
                es.addedToEngine(engine);
            }
        }
        all = engine.getEntities();
    }

    private void updateEntities(ImmutableArray<Entity> entities){
        for (Entity e:engine.getEntities()) {
            engine.removeEntity(e);
        }

        for (Entity e:entities){
            Gdx.app.log("Im adding", " " + e);
            engine.addEntity(e);
        }
    }

    private void doPhysicsStep(float deltaTime) {
       world.step(deltaTime, 1, 1);
    }

    public World getWorld() {
        return world;
    }

    public void changeActiveStage(Stage stage){
        updateEntities(
                stage.getEntityImmutableArray()
        );
    }

    public Engine getEngine() {
        return engine;
    }
}

package com.leftshoeblue.runner.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.actor.RunnerMan;
import com.leftshoeblue.runner.io.FlickDirection;
import com.leftshoeblue.runner.util.RunnerUtils;
import com.leftshoeblue.runner.world.body.BodyFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/10/06
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RunnerStage extends Stage {

    private static final double RELOAD_TIME = 1;
    public static final int INITIAL_BULLET_COUNT = 3;

    World world;
    RunnerMan runnerMan;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera;
    EnemyGenerator enemyGenerator;
    float runningReloadBurndownTime = 0f;

    List<Body> bodiesToClear = new ArrayList<Body>();


    public RunnerStage() {
        super();
        initStage();
    }

    private void initStage() {
        setupCamera();
        setupWorld();
        setupRunner();

        enemyGenerator = new EnemyGenerator();
        debugRenderer = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(new GestureDetector(new GestureHandler()));
    }

    private void setupRunner() {
        runnerMan = new RunnerMan(BodyFactory.runnerManBody(world));
        runnerMan.addBullets(INITIAL_BULLET_COUNT);
        addActor(runnerMan);
    }

    private void setupWorld() {
        world = new World(new Vector2(0, -40f), true);
        world.setContactListener(new StageContactListener());
        BodyFactory.groundBody(world);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
        camera.position.set(Constants.CAMERA_WIDTH / 2, Constants.CAMERA_HEIGHT / 2, 0);
        getViewport().setCamera(camera);
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        if(enemyGenerator.isEnemyReady(delta)){
            enemyGenerator.createEnemy(world);
        }

        if(runnerMan.isOutOfBullets()){
            runningReloadBurndownTime += delta;
            if(runningReloadBurndownTime >= RELOAD_TIME){
                runnerMan.addBullets(INITIAL_BULLET_COUNT);
                runningReloadBurndownTime = 0;
            }
        }

        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void draw() {
        super.draw();    //To change body of overridden methods use File | Settings | File Templates.

        removeDeadBodies();

        debugMatrix = getBatch().getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METER,
                Constants.PIXELS_TO_METER, 0);
        debugRenderer.render(world, debugMatrix);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("PRINT MOTHERFUCKER");
        //fire the gun
        runnerMan.shoot(world);
        return true;
    }


    private class StageContactListener implements ContactListener{

        @Override
        public void beginContact(Contact contact) {
            if(contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureB().getBody().getUserData() != null){
                addBodyToRemove(contact.getFixtureA().getBody());
                addBodyToRemove(contact.getFixtureB().getBody());
            }
        }

        @Override
        public void endContact(Contact contact) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }



    private void addBodyToRemove(Body body){
        bodiesToClear.add(body);
    }

    private void removeDeadBodies(){
        for (Body body : bodiesToClear) {
            world.destroyBody(body);
        }

        bodiesToClear = new ArrayList<Body>();

    }

    public class GestureHandler implements GestureDetector.GestureListener {
        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            runnerMan.shoot(world);
            return true;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {

            System.out.println(String.format("Flick VeloX: %f\nFlick VeloY: %f", velocityX, velocityY));

            boolean ret = false;
            FlickDirection fd = RunnerUtils.determineFlick(velocityX, velocityY);

            switch (fd) {
                case NONE:
                    break;
                case LEFT:
                    runnerMan.runLeft();
                    ret = true;
                    break;
                case RIGHT:
                    runnerMan.runRight();
                    ret = true;
                    break;
                case DOWN:
                    break;
                case UP:
                    runnerMan.jump();
                    ret = true;
                    break;
                default:
                    break;
            }

            return ret;  //To change body of implemented methods use File | Settings | File Templates.*/
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

    }

}

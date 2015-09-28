package com.leftshoeblue.runner.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.RunnerGame;
import com.leftshoeblue.runner.actor.RunnerMan;
import com.leftshoeblue.runner.actor.Spikes;
import com.leftshoeblue.runner.actor.Text;
import com.leftshoeblue.runner.io.FlickDirection;
import com.leftshoeblue.runner.util.RunnerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/07
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen, GestureDetector.GestureListener {

    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT = 480;
    Stage stage;
    RunnerGame game;
    World world;
    RunnerMan runnerMan;
    Spikes spikes;
    Body worldBody;

    Text runnerPosDebug;
    Text boxPosDebug;
    Text flickVeloDebug;

    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    public GameScreen(final RunnerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.setGameState(RunnerGame.GameState.INIT);
        world = new World(new Vector2(0, -9.8f), true);

        setupCollisionDetection();

        Viewport viewport = new ScalingViewport(Scaling.stretch, WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);

        runnerMan = new RunnerMan(world);
        spikes = new Spikes(world, WORLD_WIDTH * 0.75f, WORLD_HEIGHT * 0.2f);

        runnerPosDebug = new Text();
        boxPosDebug = new Text();
        flickVeloDebug = new Text();

        runnerPosDebug.setX(0);
        runnerPosDebug.setY(100);
        boxPosDebug.setX(0);
        boxPosDebug.setY(50);
        flickVeloDebug.setX(0);
        flickVeloDebug.setY(150);

        stage.addActor(runnerMan);
        stage.addActor(spikes);
        stage.addActor(boxPosDebug);
        stage.addActor(runnerPosDebug);
        stage.addActor(flickVeloDebug);

        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);

        BodyDef worldBodyDef = new BodyDef();
        worldBodyDef.type = BodyDef.BodyType.StaticBody;
        worldBodyDef.position.set(0, 0);
        float w = RunnerUtils.pixelsToMeters(WORLD_WIDTH);
        float h = RunnerUtils.pixelsToMeters(WORLD_HEIGHT);

        FixtureDef worldFd = new FixtureDef();
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(0, 0.2f * h, w, 0.2f * h);
        worldFd.shape = edgeShape;

        worldBody = world.createBody(worldBodyDef);
        worldBody.createFixture(worldFd);
        edgeShape.dispose();

        debugRenderer = new Box2DDebugRenderer();
    }

    private void setupCollisionDetection() {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (isRunnerAndSpikesContact(contact)) {
                    game.pause();
                }
            }

            private boolean isRunnerAndSpikesContact(Contact contact) {
                return (contact.getFixtureA().getBody().equals(runnerMan.getRunnerManBody()) && contact.getFixtureB().getBody().equals(spikes.getSpikesBody())) || (contact.getFixtureB().getBody().equals(runnerMan.getRunnerManBody()) && contact.getFixtureA().getBody().equals(spikes.getSpikesBody()));
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
        });
    }

    @Override
    public void render(float delta) {
        //To change body of implemented methods use File | Settings | File Templates.
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);


        /*playerPos.draw(stage.getBatch(), String.format("Player Position: (%s px, %s px)", runnerMan.getX(), runnerMan.getY()), 100, 100);
        boxPos.draw(stage.getBatch(), String.format("Player Position: (%s px, %s px)", runnerMan.getRunnerManBody().getPosition().x, runnerMan.getRunnerManBody().getPosition().y), 100, 200);*/

        runnerPosDebug.setTextContent(String.format("Player Position: (%s px, %s px)", runnerMan.getX(), runnerMan.getY()));
        boxPosDebug.setTextContent(String.format("Box Position: (%s m, %s m)", runnerMan.getRunnerManBody().getPosition().x, runnerMan.getRunnerManBody().getPosition().y));

        debugMatrix = stage.getBatch().getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METER,
                Constants.PIXELS_TO_METER, 0);

        stage.draw();


        debugRenderer.render(world, debugMatrix);
        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void resize(int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        //runnerMan.jump();
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        flickVeloDebug.setTextContent(String.format("Flick: velocityX = %s; velocityY = %s", velocityX, velocityY));

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

        return ret;  //To change body of implemented methods use File | Settings | File Templates.
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

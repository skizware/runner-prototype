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
import com.leftshoeblue.runner.stage.RunnerStage;
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



    public GameScreen(final RunnerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.setGameState(RunnerGame.GameState.INIT);

        stage = new RunnerStage();

        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);

    }

    @Override
    public void render(float delta) {
        //To change body of implemented methods use File | Settings | File Templates.
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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

        return false;
/*
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

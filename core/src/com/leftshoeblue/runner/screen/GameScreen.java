package com.leftshoeblue.runner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.leftshoeblue.runner.RunnerGame;
import com.leftshoeblue.runner.stage.RunnerStage;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/07
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen {

    Stage stage;
    RunnerGame game;



    public GameScreen(final RunnerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.setGameState(RunnerGame.GameState.INIT);

        stage = new RunnerStage();
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


}

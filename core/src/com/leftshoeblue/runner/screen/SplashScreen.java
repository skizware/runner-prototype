package com.leftshoeblue.runner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.leftshoeblue.runner.Assets;
import com.leftshoeblue.runner.RunnerGame;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/07
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplashScreen implements Screen {

    private RunnerGame game;
    private Stage stage;

    public SplashScreen(final RunnerGame game){
        this.game = game;
    }

    @Override
    public void show() {
        Assets.loadSplash();
        stage = new Stage();
        Image splashImage = new Image(Assets.splashTexture);

        splashImage.addAction( Actions.sequence( Actions.fadeOut( 0.001f ), Actions.fadeIn( 3f ), Actions.run(onSplashFinishedRunnable) ) );

        stage.addActor(splashImage);
    }

    Runnable onSplashFinishedRunnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            game.setScreen(new GameScreen(game));
        }
    };

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
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
        stage.dispose();
    }
}

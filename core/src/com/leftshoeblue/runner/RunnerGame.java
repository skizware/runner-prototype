package com.leftshoeblue.runner;

import com.badlogic.gdx.Game;
import com.leftshoeblue.runner.screen.SplashScreen;

public class RunnerGame extends Game {

    GameState state = GameState.INIT;

    @Override
    public void create() {
        this.pause();
        setScreen(new SplashScreen(this));

    }

    public void setGameState(GameState gameState){
        this.state = gameState;
    }

    public GameState getState() {
        return state;
    }

    public enum GameState{
        INIT,
        PAUSED,
        COUNT_DOWN,
        PLAY
    }


}

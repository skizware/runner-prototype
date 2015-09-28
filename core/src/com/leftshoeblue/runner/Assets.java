package com.leftshoeblue.runner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/07
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assets {

    public static Texture splashTexture;

    public static void loadSplash(){
        if(splashTexture == null){
            splashTexture = new Texture(Gdx.files.internal("splash.png"));
        }
    }

}

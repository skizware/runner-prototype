package com.leftshoeblue.runner.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/08
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Text extends Actor {

    String textContent = "";
    BitmapFont bitmapFont;

    public Text(){
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 1, 1, 1);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {

        bitmapFont.draw(batch, textContent, getX(), getY());

    }

    public void setTextContent(final String textContent) {
        this.textContent = textContent;
    }
}

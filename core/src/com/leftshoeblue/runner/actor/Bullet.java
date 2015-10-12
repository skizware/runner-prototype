package com.leftshoeblue.runner.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/10/12
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bullet extends GameActor {

    private Texture texture;

    public Bullet(Body body) {
        super(body);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}

package com.leftshoeblue.runner.actor;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/10/06
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameActor extends Actor {

    private Body body;

    public GameActor(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
}

package com.leftshoeblue.runner.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leftshoeblue.runner.util.RunnerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/08
 * Time: 12:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Spikes extends Actor {

    private static final Integer HEIGHT = 32;
    private static final Integer WIDTH = 32;

    private World world;
    private Body spikesBody;
    private Texture spikesTexture;

    public Spikes(World world, Float startX, Float startY) {
        this.world = world;


        spikesTexture = new Texture(Gdx.files.internal("spikes.png"));

        this.setX(startX);
        this.setY(startY);

        BodyDef spikesBodyDef = new BodyDef();
        spikesBodyDef.position.set(RunnerUtils.pixelsToMeters(startX + WIDTH/2), RunnerUtils.pixelsToMeters(startY + HEIGHT/2));
        spikesBody = world.createBody(spikesBodyDef);
        spikesBody.setType(BodyDef.BodyType.StaticBody);


        CircleShape shape = new CircleShape();
        shape.setRadius(RunnerUtils.pixelsToMeters(WIDTH / 2));

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;

        spikesBody.createFixture(fd);

        shape.dispose();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {


        batch.draw(spikesTexture, getX(), getY());



    }

    public Body getSpikesBody() {
        return spikesBody;
    }
}

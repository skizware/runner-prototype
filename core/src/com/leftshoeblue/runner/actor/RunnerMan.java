package com.leftshoeblue.runner.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leftshoeblue.runner.io.FlickDirection;
import com.leftshoeblue.runner.screen.GameScreen;
import com.leftshoeblue.runner.util.RunnerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/07
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class RunnerMan extends Actor {

    public static final int AVE_CHAR_WIDTH = 30;
    public static final int AVE_CHAR_HEIGHT = 45;
    public static final int JUMP_VELOCITY = 6;
    private World world;
    private Body runnerManBody;
    private TextureAtlas runningTextureAtlas;
    private Animation runningAnimation;
    float elapsedTime = 0;

    public RunnerMan(final World world){
        this.world = world;
        runningTextureAtlas = new TextureAtlas(Gdx.files.internal("spaceman.atlas"));
        runningAnimation = new Animation(1/10f, runningTextureAtlas.getRegions());




        restartRunner();
    }

    @Override
    public void act (float delta) {
        super.act(delta);


        setX(RunnerUtils.metersToPixels(runnerManBody.getPosition().x - RunnerUtils.pixelsToMeters(AVE_CHAR_WIDTH) / 2));
        setY(RunnerUtils.metersToPixels(runnerManBody.getPosition().y - RunnerUtils.pixelsToMeters(AVE_CHAR_HEIGHT) / 2));

        elapsedTime = elapsedTime + delta;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {


        batch.draw(runningAnimation.getKeyFrame(elapsedTime, true), getX(), getY());



    }

    public void restartRunner(){
        setX(GameScreen.WORLD_WIDTH/4);
        setY(GameScreen.WORLD_HEIGHT/2);

        BodyDef bd = new BodyDef();
        bd.position.set(RunnerUtils.pixelsToMeters(getX() + AVE_CHAR_WIDTH /2),
                RunnerUtils.pixelsToMeters(getY() + AVE_CHAR_HEIGHT /2));

        bd.type = BodyDef.BodyType.DynamicBody;

        runnerManBody = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(RunnerUtils.pixelsToMeters(AVE_CHAR_WIDTH)/2,
                RunnerUtils.pixelsToMeters(AVE_CHAR_HEIGHT)/2);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        runnerManBody.createFixture(fd);
        shape.dispose();
    }

    public void jump(){
        this.runnerManBody.setLinearVelocity(this.runnerManBody.getLinearVelocity().x, JUMP_VELOCITY);
    }

    public void runRight(){
        this.runnerManBody.setLinearVelocity(JUMP_VELOCITY, 0);
    }

    public void runLeft(){
        this.runnerManBody.setLinearVelocity(-JUMP_VELOCITY, 0);
    }

    public Body getRunnerManBody() {
        return runnerManBody;
    }
}

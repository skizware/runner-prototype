package com.leftshoeblue.runner.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.screen.GameScreen;
import com.leftshoeblue.runner.util.RunnerUtils;
import com.leftshoeblue.runner.world.body.BodyFactory;
import com.leftshoeblue.runner.world.body.PositionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/07
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class RunnerMan extends GameActor {

    public static final int JUMP_VELOCITY = 6;
    private TextureAtlas runningTextureAtlas;
    private Animation runningAnimation;
    float elapsedTime = 0;

    public RunnerMan(final Body body){
        super(body);

        //animating/render logic
        runningTextureAtlas = new TextureAtlas(Gdx.files.internal("spaceman.atlas"));
        runningAnimation = new Animation(1 / 10f, runningTextureAtlas.getRegions());
        restartRunner();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //world position AND render logic logic?

        updatePositionFromBody();
        elapsedTime = elapsedTime + delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


        //animating/render logic
        batch.draw(runningAnimation.getKeyFrame(elapsedTime, true), getX(), getY());


    }

    private void updatePositionFromBody(){
        setX(RunnerUtils.metersToPixels(getBody().getPosition().x - RunnerUtils.pixelsToMeters(Constants.RUNNER_WIDTH) / 2));
        setY(RunnerUtils.metersToPixels(getBody().getPosition().y - RunnerUtils.pixelsToMeters(Constants.RUNNER_HEIGHT) / 2));
    }

    public void restartRunner() {
        getBody().setTransform(PositionFactory.runnerManStartPosition(), 0);
    }

    public void jump() {
        this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x, JUMP_VELOCITY);
    }

    public void runRight() {
        this.getBody().setLinearVelocity(JUMP_VELOCITY, 0);
    }

    public void runLeft() {
        this.getBody().setLinearVelocity(-JUMP_VELOCITY, 0);
    }

    public Bullet shoot(final World world){
        //Don't want to return body here as it gives RunnerMan a dependency on World. Why not tho when its already dependent on box2d?.

        Body bulletBody = BodyFactory.bulletBody(world, getBody().getPosition().x + RunnerUtils.pixelsToMeters(Constants.RUNNER_WIDTH),
                getBody().getPosition().y);


        return new Bullet(bulletBody);
    }
}

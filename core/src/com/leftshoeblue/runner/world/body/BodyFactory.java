package com.leftshoeblue.runner.world.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.util.RunnerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/10/06
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BodyFactory {

    public static Body runnerManBody(World world){
        BodyDef runnerManBodyDef = new BodyDef();

        runnerManBodyDef.type = BodyDef.BodyType.DynamicBody;
        runnerManBodyDef.position.set(100f,100f);
        Body runnerBody = world.createBody(runnerManBodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(RunnerUtils.pixelsToMeters(Constants.RUNNER_WIDTH) / 2,
                RunnerUtils.pixelsToMeters(Constants.RUNNER_HEIGHT) / 2);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.3f;
        runnerBody.createFixture(fd);
        shape.dispose();

        return runnerBody;
    }

    public static Body groundBody(World world){
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = world.createBody(groundBodyDef);
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(0,0), new Vector2(RunnerUtils.pixelsToMeters(Gdx.graphics.getWidth()), 0 ));
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        groundBody.createFixture(fd);
        shape.dispose();

        return groundBody;
    }

    public static Body bulletBody(World world, Float x, Float y){
        BodyDef bulletBodyDef = new BodyDef();
        bulletBodyDef.type = BodyDef.BodyType.KinematicBody;
        bulletBodyDef.position.set(x,y);
        bulletBodyDef.linearVelocity.set(14f,0);


        Body bulletBody = world.createBody(bulletBodyDef);
        FixtureDef bulletFixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.1f, .1f);
        bulletFixtureDef.shape = shape;
        bulletBody.createFixture(bulletFixtureDef);
        bulletBody.setUserData(true);
        shape.dispose();

        return bulletBody;
    }


    public static Body enemyBody(World world){
        BodyDef bd = new BodyDef();



        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(PositionFactory.enemyPosition());
        bd.linearVelocity.set(-20f, 0f);
        Body body = world.createBody(bd);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(RunnerUtils.pixelsToMeters(Constants.RUNNER_WIDTH) / 2,
                RunnerUtils.pixelsToMeters(Constants.RUNNER_HEIGHT) / 2);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        body.createFixture(fd);
        shape.dispose();

        body.setUserData(true);
        return body;
    }
}

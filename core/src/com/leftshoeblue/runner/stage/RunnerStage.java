package com.leftshoeblue.runner.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.actor.RunnerMan;
import com.leftshoeblue.runner.world.body.BodyFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/10/06
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RunnerStage extends Stage {

    World world;
    RunnerMan runnerMan;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera;

    List<Body> bodiesToClear = new ArrayList<Body>();


    public RunnerStage() {
        super();

        camera = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(new StageContactListener());
        runnerMan = new RunnerMan(BodyFactory.runnerManBody(world));
        BodyFactory.groundBody(world);
        addActor(runnerMan);
        debugRenderer = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(this);
    }




    @Override
    public void act(float delta) {
        super.act(delta);


        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void draw() {
        super.draw();    //To change body of overridden methods use File | Settings | File Templates.

        removeDeadBodies();

        debugMatrix = getBatch().getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METER,
                Constants.PIXELS_TO_METER, 0);
        debugRenderer.render(world, debugMatrix);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("PRINT MOTHERFUCKER");
        //fire the gun
        runnerMan.shoot(world);
        BodyFactory.enemyBody(world);
        return true;
    }

    private class StageContactListener implements ContactListener{

        @Override
        public void beginContact(Contact contact) {
            if(contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureB().getBody().getUserData() != null){
                addBodyToRemove(contact.getFixtureA().getBody());
                addBodyToRemove(contact.getFixtureB().getBody());
            }
        }

        @Override
        public void endContact(Contact contact) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private void addBodyToRemove(Body body){
        bodiesToClear.add(body);
    }

    private void removeDeadBodies(){
        for (Body body : bodiesToClear) {
            world.destroyBody(body);
        }

        bodiesToClear = new ArrayList<Body>();

    }

}

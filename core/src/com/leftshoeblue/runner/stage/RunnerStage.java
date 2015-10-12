package com.leftshoeblue.runner.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.actor.RunnerMan;
import com.leftshoeblue.runner.world.body.BodyFactory;

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


    public RunnerStage() {
        super();

        camera = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
        world = new World(new Vector2(0, -9.8f), true);
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

        debugMatrix = getBatch().getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METER,
                Constants.PIXELS_TO_METER, 0);
        debugRenderer.render(world, debugMatrix);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //fire the gun
        //add a bullet to the world at playerX+playerWidth,playerY+1/4playerHeight - allow the shooter to add the bullet to the world? or just add it.
        runnerMan.shoot(world);
        runnerMan.getBody().applyLinearImpulse(new Vector2(0, 13f), runnerMan.getBody().getWorldCenter(), true);
        return true;
    }
}

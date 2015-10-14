package com.leftshoeblue.runner.stage;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.leftshoeblue.runner.actor.GameActor;
import com.leftshoeblue.runner.world.body.BodyFactory;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/10/14
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EnemyGenerator {

    private float lastEnemyCreatedTime = 0f;
    private float timeToNextEnemy = 1;
    private Random random = new Random();

    public GameActor createEnemy(World world){
        return new GameActor(BodyFactory.enemyBody(world));
    }

    private void setTimeToNextEnemy(){
        timeToNextEnemy = random.nextFloat() * 4;
    }

    public boolean isEnemyReady(float deltaTime){

        lastEnemyCreatedTime += deltaTime;
        if((lastEnemyCreatedTime += deltaTime) > timeToNextEnemy){
            lastEnemyCreatedTime = 0f;
            setTimeToNextEnemy();
            return true;
        }

        return false;
    }

}

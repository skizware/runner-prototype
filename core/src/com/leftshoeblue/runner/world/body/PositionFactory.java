package com.leftshoeblue.runner.world.body;

import com.badlogic.gdx.math.Vector2;
import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.util.RunnerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/10/06
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class PositionFactory {

    public static Vector2 runnerManStartPosition(){
        return new Vector2(RunnerUtils.pixelsToMeters(Constants.RUNNER_WIDTH/2+100),
                RunnerUtils.pixelsToMeters(Constants.RUNNER_HEIGHT/2+100));
    }

}

package com.leftshoeblue.runner.util;

import com.leftshoeblue.runner.Constants;
import com.leftshoeblue.runner.io.FlickDirection;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/04/07
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class RunnerUtils {

    public static FlickDirection determineFlick(final float velocityX, final float velocityY){
        FlickDirection fd = FlickDirection.NONE;

        if (Math.abs(velocityX) > Math.abs(velocityY)){
            //check for horizontal swipes
            if(velocityX > Constants.FLICK_VELO_THRESHOLD){
                fd = FlickDirection.RIGHT;
            }
            else if(velocityX < -1*Constants.FLICK_VELO_THRESHOLD){
                fd = FlickDirection.LEFT;
            }
        }else{
            //check for vertical swipes
            if(velocityY > Constants.FLICK_VELO_THRESHOLD){
                fd = FlickDirection.DOWN;
            }
            else if(velocityY < -1*Constants.FLICK_VELO_THRESHOLD){
                fd = FlickDirection.UP;
            }
        }

        return fd;
    }

    public static float metersToPixels(final float meterValue){
        return meterValue*Constants.PIXELS_TO_METER;
    }

    public static float pixelsToMeters(final float pixelValue){
        return pixelValue/Constants.PIXELS_TO_METER;
    }

}

package org.bjorn.lathespeed;

import android.util.Log;

/**
 * Created by bjorn on 3/27/16.
 */
public class RPMcalculator {

    public static final String TAG = LatheSpeedActivity.class.getSimpleName();

    public void fuckme(){
        Log.d(TAG,"FUCK");
    }

    public double rpmFromDiamAndCS(double diam,double cutSpeed){
        double rpms = (cutSpeed * 12.0) / (Math.PI * diam);
        Log.d(TAG, "calculated rpms: " + rpms);

        return rpms;

    }
}

package org.bjorn.lathespeed;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjorn on 3/27/16.
 */
public class RPMcalculator {
    public static final String TAG = LatheSpeedActivity.class.getSimpleName();
    public static final Map<Integer,String> MATERIAL_SPEEDS_MAP = new HashMap<Integer,String>() {{
        put(38,"High-Carbon");
        put(45,"Stainless");
        put(65,"Cast Iron");
        put(70,"Copper");
        put(80,"Mild Steel");
        put(150,"Brass/Bronze");
        put(250,"Aluminum");
    }};


    public void fuckme(){
        Log.d(TAG,"FUCK");
    }

    public double rpmFromDiamAndCS(double diam,double cutSpeed){
        double rpms = (cutSpeed * 12.0) / (Math.PI * diam);
        Log.d(TAG, "calculated rpms: " + rpms);

        return rpms;

    }
}

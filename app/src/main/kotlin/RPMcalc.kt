/**
 * Created by bjorn on 5/22/16.
 */
package latheUtils

import android.util.Log
val TAG = "RPMcalc.kt"

fun rpmFromDiamAndCS(currentDiameter:Double, currentCuttingSpeed:Double): Double {
    var rpms:Double = (currentCuttingSpeed * 12.0) / (Math.PI * currentDiameter)

    return rpms
}

// cutting speeds in FPM
fun materialNameFromCutSpeed(cutSpeed:Double): String {
    val cutSpeedMap = hashMapOf(80 to "High-Carbon",
                                110 to "Stainless",
                                150 to "Cast Iron",
                                100 to "Mild Steel",
                                210 to "Brass/Bronze",
                                550 to "Aluminum",
                                700 to "Cast Aluminum")

    val nearest = cutSpeedMap.keys.minBy { Math.abs(it-cutSpeed)}
    Log.d(TAG,"CS:"+cutSpeed+" nearest to:"+nearest+" material:"+cutSpeedMap[nearest])

    return cutSpeedMap[nearest] as String
}

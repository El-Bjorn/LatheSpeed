/**
 * Created by bjorn on 5/22/16.
 */
package latheUtils

import android.util.Log

fun tst(): String{
    return "boo"
}

fun materialNameFromCutSpeed(cutSpeed:Double): String {
    val cutSpeedMap = hashMapOf(38 to "High-Carbon",
                                45 to "Stainless",
                                65 to "Cast Iron",
                                70 to "Copper",
                                80 to "Mild Steel",
                                150 to "Brass/Bronze",
                                250 to "Aluminum")

    val nearest = cutSpeedMap.keys.minBy { Math.abs(it-cutSpeed)}

    return cutSpeedMap[nearest] as String
}

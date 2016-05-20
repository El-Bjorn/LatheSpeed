package org.bjorn.lathespeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LatheSpeedActivity extends AppCompatActivity {

    public static final String TAG = LatheSpeedActivity.class.getSimpleName();

    // buttery widgets
    @InjectView(R.id.rpmDisplayView) TextView RPMDisplayView;
    // Diameter
    @InjectView(R.id.diameterDisplayView) TextView diameterDisplayView;
    @InjectView(R.id.diameterSeekBar) SeekBar diameterSeekBar;
    // Cutting Speed
    @InjectView(R.id.CSdisplayView) TextView CSDisplayView;
    @InjectView(R.id.CSseekBar) SeekBar CSseekBar;
    // material
    @InjectView(R.id.materialTextView) TextView materialDisplayView;
    //@InjectView(R.id.cutcircle) ImageView cutCircleImage;

    private double currentDiameter;
    private double currentCuttingSpeed;
    private ImageView wheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lathe_speed);
        ButterKnife.inject(this);

        // spinny thing!
        wheel = (ImageView)findViewById(R.id.cutcircle);
        wheel.startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.constant_rotation));


        // set initial values
        diameterSeekBar.setProgress((int) (1.25 * 100));
        currentDiameter = 1.25;
        CSseekBar.setProgress(75);
        currentCuttingSpeed = 75.0;
        materialDisplayView.setText(RPMcalculator.materialFromCS(currentCuttingSpeed));

        recalculateRPMs();

        // get widget references
        //RPMTextView = (TextView)findViewById(R.id.rpmtextView);
        //RPMseekBar = (SeekBar)findViewById(R.id.rpmSeekBar);

        diameterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "diameter seek bar value = " + progress);
                double diameter = (double)progress / 100;
                diameterDisplayView.setText("diameter: "+diameter+" inches");
                currentDiameter = diameter;

                recalculateRPMs();
                
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        CSseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "CS seek bar value = " + progress);
                CSDisplayView.setText("cutting speed: " + progress + " (ft/min.)");
                currentCuttingSpeed = progress;
                //String materialString =
                materialDisplayView.setText(RPMcalculator.materialFromCS(progress));
                /*if (RPMcalculator.MATERIAL_SPEEDS_MAP.containsKey(progress)){
                    //Toast.makeText(this,"Test toast",Toast.LENGTH_SHORT).show();
                    Toast materialToast = Toast.makeText(getApplicationContext(),
                            RPMcalculator.MATERIAL_SPEEDS_MAP.get(progress)+" ~"+progress,
                            Toast.LENGTH_SHORT );
                    materialToast.setGravity(Gravity.CENTER_HORIZONTAL,0,70);
                    materialToast.show();

                } */

                recalculateRPMs();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void recalculateRPMs() {
        RPMcalculator calculator = new RPMcalculator();
        double newRPMs = calculator.rpmFromDiamAndCS(currentDiameter,currentCuttingSpeed);

        Log.d(TAG,"reculated rpms: "+newRPMs);

        RPMDisplayView.setText( String.format("%.1f",newRPMs));

    }
}

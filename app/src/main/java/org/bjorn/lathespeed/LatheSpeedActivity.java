package org.bjorn.lathespeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

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

    private double currentDiameter;
    private double currentCuttingSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lathe_speed);
        ButterKnife.inject(this);

        // set initial values
        diameterSeekBar.setProgress((int) (1.25 * 100));
        currentDiameter = 1.25;
        CSseekBar.setProgress(75);
        currentCuttingSpeed = 75.0;
        recalculateRPMs();

        // get widget references
        //RPMTextView = (TextView)findViewById(R.id.rpmtextView);
        //RPMseekBar = (SeekBar)findViewById(R.id.rpmSeekBar);

        diameterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "diameter seek bar value = " + progress);
                double diameter = (double)progress / 100;
                diameterDisplayView.setText(diameter+" inches");
                currentDiameter = diameter;

                recalculateRPMs();


                // update rpms
                /*RPMcalculator calculator = new RPMcalculator();
                double rpms = calculator.rpmFromDiamAndCS() */
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
                CSDisplayView.setText(progress + " (ft/min.)");
                currentCuttingSpeed = progress;

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

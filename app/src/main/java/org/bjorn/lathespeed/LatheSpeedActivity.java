package org.bjorn.lathespeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LatheSpeedActivity extends AppCompatActivity {

    public static final String TAG = LatheSpeedActivity.class.getSimpleName();
    public static final float MIN_DIAMETER = 0.0f;
    public static final float MAX_DIAMETER = 5.0f;

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
    private ImageView rotatingPiece;
    private Animation pieceAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lathe_speed);
        ButterKnife.inject(this);

        // spinny thing!
        rotatingPiece = (ImageView)findViewById(R.id.cutcircle);
        pieceAnimation = AnimationUtils.loadAnimation(this, R.anim.constant_rotation);
        //wheel.startAnimation(
          //      AnimationUtils.loadAnimation(this, R.anim.constant_rotation));
        rotatingPiece.startAnimation(pieceAnimation);

        //calculateRPMsKt.tstin();
        Log.d(TAG, latheUtils.RPMcalcKt.tst());


        // set initial values
        diameterSeekBar.setProgress((int) (1.25 * 100));
        currentDiameter = 1.25;
        CSseekBar.setProgress(75);
        currentCuttingSpeed = 75.0;
        //materialDisplayView.setText(RPMcalculator.materialFromCS(currentCuttingSpeed));
        materialDisplayView.setText(latheUtils.RPMcalcKt.materialNameFromCutSpeed(currentCuttingSpeed));

        recalculateRPMs();

        // get widget references
        //RPMTextView = (TextView)findViewById(R.id.rpmtextView);
        //RPMseekBar = (SeekBar)findViewById(R.id.rpmSeekBar);

        // DIAMETER
        diameterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "diameter seek bar value = " + progress);
                double diameter = (double)progress / 100;
                diameterDisplayView.setText("diameter: "+diameter+" inches");
                currentDiameter = diameter;

                // change piece animation
                float diamScale = (float) (diameter/MAX_DIAMETER);
                Log.d(TAG,"diam scale= "+diamScale);
                rotatingPiece.setScaleX(diamScale);
                rotatingPiece.setScaleY(diamScale);

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
                //materialDisplayView.setText(RPMcalculator.materialFromCS(progress));
                materialDisplayView.setText(latheUtils.RPMcalcKt.materialNameFromCutSpeed(progress));

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
        //double newRPMs = calculator.rpmFromDiamAndCS(currentDiameter,currentCuttingSpeed);
        double newRPMs = latheUtils.RPMcalcKt.rpmFromDiamAndCS(currentDiameter, currentCuttingSpeed);

        Log.d(TAG,"reculated rpms: "+newRPMs);

        RPMDisplayView.setText( String.format("%.1f",newRPMs));

        double rotDuration = (1.0/(newRPMs/6000))*10.0;
        Log.d(TAG,"new rotation duration= "+rotDuration);

        pieceAnimation.setDuration(Math.round(rotDuration));

    }
}

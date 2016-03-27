package org.bjorn.lathespeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class LatheSpeedActivity extends AppCompatActivity {

    public static final String TAG = LatheSpeedActivity.class.getSimpleName();

    private SeekBar RPMseekBar;
    private TextView RPMTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lathe_speed);

        // get widget references
        RPMTextView = (TextView)findViewById(R.id.rpmtextView);
        RPMseekBar = (SeekBar)findViewById(R.id.rpmSeekBar);

        RPMseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "seek bar value = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

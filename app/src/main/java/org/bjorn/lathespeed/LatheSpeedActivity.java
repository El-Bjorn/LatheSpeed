package org.bjorn.lathespeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class LatheSpeedActivity extends AppCompatActivity {

    private SeekBar RPMseekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lathe_speed);

        // get widget references
        RPMseekBar = (SeekBar)findViewById(R.id.rpmSeekBar);
    }
}

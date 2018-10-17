package com.example.dude.workoutproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.dude.workoutproject.R;

public class StopWatch extends AppCompatActivity {

    Button startStopWatch;
    Button stopStopWatch;
    Button restartStopWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        initGUI();
    }

    private void initGUI() {
        startStopWatch = findViewById(R.id.start_stopwatch);
        stopStopWatch = findViewById(R.id.stop_stopwatch);
        restartStopWatch = findViewById(R.id.restart_stopwatch);

    }
}

package com.example.dude.workoutproject.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dude.workoutproject.R;

public class StopWatch extends AppCompatActivity {

    Button startStopWatch;
    Button stopStopWatch;
    Button resetStopWatch;
    boolean running;
    int seconds;
    TextView stopWatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        initGUI();
    }

    public void btnStart(View view) {
        running = true;
    }

    public void btnStop(View view) {
        running = false;
    }

    public void btnReset(View view) {
        running = false;
        seconds = 0;
    }

    private void initGUI() {
        startStopWatch = findViewById(R.id.start_stopwatch);
        stopStopWatch = findViewById(R.id.stop_stopwatch);
        resetStopWatch = findViewById(R.id.restart_stopwatch);
        stopWatches = findViewById(R.id.stop_watch);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
             int hours = seconds/3600;
             int minutes = (seconds%3600)/60;
             int sec = seconds/60;

             String time = String.format("%d:%02d:%02d", hours, minutes, sec);
             stopWatches.setText(time);
             if(running){
                 seconds++;
             }
             handler.postDelayed(this, 1000);
            }
        });

    }
}

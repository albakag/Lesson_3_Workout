package com.example.dude.workoutproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.dude.workoutproject.R;


public class WorkoutListActivity extends AppCompatActivity {

    Button pullUpButton;
    Button pushUpButton;
    Button squitting;
    Button call;
    final String TAG = "WorkoutListActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        initGUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Вызван метод onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Вызван метод onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Вызван метод onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Вызван метод onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Вызван метод onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Вызван метод onDestroy");
    }

    private void initGUI() {
        pullUpButton = findViewById(R.id.pull_up_button);
        pushUpButton = findViewById(R.id.push_up_button);
        squitting = findViewById(R.id.squitting_button);
        call = findViewById(R.id.calling);

        pullUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workOutDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startActivity(workOutDetailActivity);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendInfoPage = new Intent(".Activity.WorkoutSendInfo");
                startActivity(sendInfoPage);
            }
        });

        pushUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPush = new Intent(".Activity.WorkoutDetailActivity");
                startActivity(intentPush);
            }
        });

        squitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSquitting = new Intent(".Activity.WorkoutDetailActivity");
                startActivity(intentSquitting);
            }
        });
    }

}


package com.example.dude.workoutproject.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dude.workoutproject.Model.Workout;
import com.example.dude.workoutproject.Model.WorkoutList;
import com.example.dude.workoutproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;



public class WorkoutDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView recordDate;
    private TextView recordRepsCount;
    private TextView recordWeight;
    private TextView weight;
    private SeekBar weightSeekBar;
    private EditText repsCountEditText;
    private Button saveRecordButton;
    final String LOG = "WorkoutDetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail_pull_up);
        Log.d(LOG, "Вызван метод onCreat");

        Intent intent = getIntent();
        int index = intent.getIntExtra("workout_index", 0);
        Workout workout = WorkoutList.getInstance()
                .getWorkouts()
                .get(index);

        initGUI(workout);
        addListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG, "Вызван метод onStart");
    }

    //ДЗ - восстановление состояний
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recordRepsCount.setText(savedInstanceState.getString("Repeats"));
        recordWeight.setText(savedInstanceState.getString("Weight"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG, "Вызван метод onResume");
    }

    //ДЗ - Сохранение состояний
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Repeats", recordRepsCount.getText().toString());
        outState.putString("Weight", recordWeight.getText().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG, "Вызван метод onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG, "Вызван метод onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "Вызван метод onDestroy");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_share:
                shareRecord();
                return true;
            case R.id.action_settings:
                nextAcivityPage();
                return true;
            case R.id.action_quit:
                quitFromApp();
                return true;
                default:return super.onOptionsItemSelected(item);
        }
    }

    private void nextAcivityPage() {
        Intent nextActivityIntent = new Intent(".Activity.ListItem");
        startActivity(nextActivityIntent);

    }


    //ДЗ - Выход из приложения по нажатию в меню кнопки Quit
    private void quitFromApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }


    private void shareRecord() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Message");
        sendIntent.setType("text/plain");

        if(sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    private void addListeners() {
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                weight.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saveRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int oldValue = Integer.parseInt(recordWeight.getText().toString());

                int newValue = Integer.parseInt(String.valueOf(weightSeekBar.getProgress()));

                if (oldValue < newValue || oldValue == 0) {
                    recordDate.setText(new String(new SimpleDateFormat("dd MMMM yy").format(new Date())));
                    recordWeight.setText(String.valueOf(weightSeekBar.getProgress()));
                    recordRepsCount.setText(String.valueOf(repsCountEditText.getText()));
                }
            }
        });
    }

    private void initGUI(Workout workout) {
        title = findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedRecordDate());
        recordRepsCount = findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight = findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));

        weight = findViewById(R.id.workout_detail_weight);
        weightSeekBar = findViewById(R.id.workout_detail_weight_seek_bar);
        repsCountEditText = findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = findViewById(R.id.workout_detail_save_button);
    }
}

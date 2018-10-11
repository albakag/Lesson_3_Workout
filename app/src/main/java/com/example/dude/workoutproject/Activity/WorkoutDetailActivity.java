package com.example.dude.workoutproject.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dude.workoutproject.Model.Workout;
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
    private Button nextActivityPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail_pull_up);

        Workout workout = new Workout("Подтягивания", "Подтягивания на перекладине", 0, new Date(), 0);
        initGUI(workout);
        addListeners();
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

        //После комментария на ГитХаб перенес обработчик в слушателей
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

package com.example.dude.workoutproject.Fragments;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.dude.workoutproject.Model.Workout;
import com.example.dude.workoutproject.Model.WorkoutList;
import com.example.dude.workoutproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class WorkoutDetailFragment extends Fragment {
    private final static String WORKOUT_INDEX = "workoutIndex";
    private TextView title;
    private TextView recordDate;
    private TextView recordRepsCount;
    private TextView recordWeight;
    private TextView weight;
    private SeekBar weightSeekBar;
    private EditText repsCountEditText;
    private Button saveRecordButton;
    private Button sampleAnime;

    public static WorkoutDetailFragment initFragment(int workoutIndex) {
        WorkoutDetailFragment fragment = new WorkoutDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(WORKOUT_INDEX , workoutIndex);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        Workout workout = WorkoutList.getInstance().getWorkouts().get(getArguments().getInt(WORKOUT_INDEX));

        initGUI(root, workout);
        addListeners();
        return root;
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_detail_activity, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.action_share:
//                shareRecord();
//                return true;
//            case R.id.action_settings:
//                nextAcivityPage();
//                return true;
//            case R.id.action_quit:
//                quitFromApp();
//                return true;
//                default:return super.onOptionsItemSelected(item);
//        }
//    }

//    private void nextAcivityPage() {
//        Intent nextActivityIntent = new Intent(".Activity.ListItem");
//        startActivity(nextActivityIntent);
//
//    }

    private void shareRecord() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Message");
        sendIntent.setType("text/plain");

        if (sendIntent.resolveActivity(getActivity().getPackageManager()) != null) {
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

        sampleAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(5)
                        .playOn(sampleAnime);
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

    private void initGUI(View view, Workout workout) {
        title = view.findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = view.findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedRecordDate());
        recordRepsCount = view.findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight = view.findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        weight = view.findViewById(R.id.workout_detail_weight);
        weightSeekBar = view.findViewById(R.id.workout_detail_weight_seek_bar);
        repsCountEditText = view.findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = view.findViewById(R.id.workout_detail_save_button);
        sampleAnime = view.findViewById(R.id.fragment_detail_anime_sample);
    }
}

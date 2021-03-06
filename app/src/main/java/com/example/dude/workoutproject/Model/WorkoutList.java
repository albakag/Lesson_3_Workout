package com.example.dude.workoutproject.Model;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class WorkoutList {
    private static final WorkoutList ourInstance = new WorkoutList();

    public List<Workout> getWorkouts() {
        return workouts;
    }

    private List<Workout> workouts;

    public static WorkoutList getInstance() {
        return ourInstance;
    }

    private WorkoutList() {
        workouts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Workout workout = new Workout("Упражнение №" + (i + 1));
            workout.setDescription("Описание упражнения №" + (i + 1));
            workout.setRecordDate(new Date());
            workout.setRecordRepsCount(random.nextInt(101));
            workout.setRecordWeight(random.nextInt(101));
            workouts.add(workout);

        }
    }
}

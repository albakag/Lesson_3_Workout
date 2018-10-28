package com.example.dude.workoutproject.List;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dude.workoutproject.Model.Workout;
import com.example.dude.workoutproject.R;
import com.example.dude.workoutproject.interfaces.OnListItemClickListener;
import com.squareup.picasso.Picasso;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView tittle;
    private TextView description;
    private TextView recordDate;
    private TextView recordRepsCount;
    private TextView recordWeight;
    private ImageView imageView;

    public WorkoutViewHolder(@NonNull final View itemView) {
        super(itemView);
        tittle = itemView.findViewById(R.id.list_item_text_tittle_card_view);
        description = itemView.findViewById(R.id.list_item_description_text_view);
        recordDate = itemView.findViewById(R.id.list_item_calendar);
        recordRepsCount = itemView.findViewById(R.id.list_item_repeats_card_view);
        recordWeight = itemView.findViewById(R.id.list_item_weight_card_view);
        cardView = itemView.findViewById(R.id.card_view);
        imageView = itemView.findViewById(R.id.list_item_squad_image_card_view);


    }

    public void bindView(Workout workout, final int index, final OnListItemClickListener listener){
        tittle.setText(workout.getTitle());
        description.setText(workout.getDescription());
        recordDate.setText(workout.getFormattedRecordDate());
        recordWeight.setText(toString().valueOf(workout.getRecordWeight()));
        recordRepsCount.setText(toString().valueOf(workout.getRecordRepsCount()));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClickListener(index);
            }
        });
        Picasso.get()
                .load("http://i.imgur.com/DvpvklR.png")
                .into(imageView);
    }
}

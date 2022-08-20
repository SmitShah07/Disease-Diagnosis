package com.deepmodi.www.diseasediagnosis.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepmodi.www.diseasediagnosis.R;
import com.google.android.material.slider.Slider;

public class SelectAgeViewHolder extends RecyclerView.ViewHolder {

    public Slider slider;
    public TextView textView_selected_age;
    public SelectAgeViewHolder(@NonNull View itemView) {
        super(itemView);
        slider = itemView.findViewById(R.id.select_age_slider);
        textView_selected_age = itemView.findViewById(R.id.selected_age);
    }

}

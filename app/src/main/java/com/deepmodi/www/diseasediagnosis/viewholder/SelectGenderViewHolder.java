package com.deepmodi.www.diseasediagnosis.viewholder;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepmodi.www.diseasediagnosis.R;
import com.google.android.material.card.MaterialCardView;

public class SelectGenderViewHolder extends RecyclerView.ViewHolder {
    public MaterialCardView male_card;
    public MaterialCardView female_card;

    public SelectGenderViewHolder(@NonNull View itemView) {
        super(itemView);
        male_card = itemView.findViewById(R.id.male_cardView);
        female_card = itemView.findViewById(R.id.female_cardview);
    }
}

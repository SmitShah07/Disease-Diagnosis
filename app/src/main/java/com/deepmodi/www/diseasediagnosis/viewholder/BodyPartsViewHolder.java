package com.deepmodi.www.diseasediagnosis.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepmodi.www.diseasediagnosis.R;
import com.deepmodi.www.diseasediagnosis.interfaces.BodyClickListner;
import com.google.android.material.checkbox.MaterialCheckBox;

public class BodyPartsViewHolder extends RecyclerView.ViewHolder{
    public MaterialCheckBox checkbox_symptons;

    public BodyPartsViewHolder(@NonNull View itemView) {
        super(itemView);
        checkbox_symptons = itemView.findViewById(R.id.checkbox_symptons);
    }


}

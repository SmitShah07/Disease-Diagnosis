package com.deepmodi.www.diseasediagnosis.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepmodi.www.diseasediagnosis.R;
import com.deepmodi.www.diseasediagnosis.interfaces.BodyClickListner;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recyclerView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.body_parts_recyclerview);
        }

    }

package com.deepmodi.www.diseasediagnosis.viewholder;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.deepmodi.www.diseasediagnosis.R;
import com.google.android.material.textfield.TextInputEditText;

public class GeneralQuestionsViewHolder extends RecyclerView.ViewHolder {
    public TextInputEditText textInputFirstName,textInputLastName;
    public GeneralQuestionsViewHolder(@NonNull View itemView) {
        super(itemView);
        textInputFirstName = itemView.findViewById(R.id.textInputFirstName);
        textInputLastName = itemView.findViewById(R.id.textInputLastName);
    }
}

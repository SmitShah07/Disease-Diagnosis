package com.deepmodi.www.diseasediagnosis.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepmodi.www.diseasediagnosis.R;
import com.deepmodi.www.diseasediagnosis.interfaces.BodyClickListner;
import com.deepmodi.www.diseasediagnosis.model.BodyParts;
import com.deepmodi.www.diseasediagnosis.model.DataModel;
import com.deepmodi.www.diseasediagnosis.viewholder.BodyPartsViewHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BodyPartsAdapter extends RecyclerView.Adapter<BodyPartsViewHolder> {

    Context context;
    List<DataModel> symptoms;
    LayoutInflater layoutInflater;
    Map<String,String> selectedData;
    Map<String, Integer> mapSet;

    public BodyPartsAdapter(Context context,List<DataModel> symptoms,Map<String,String> selectedData, Map<String, Integer>  mapSet){
        this.context = context;
        this.symptoms = symptoms;
        layoutInflater = LayoutInflater.from(context);
        this.selectedData = selectedData;
        this.mapSet = mapSet;
    }

    @NonNull
    @Override
    public BodyPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_view_body_parts,parent,false);
        return new BodyPartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartsViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.checkbox_symptons.setText(symptoms.get(position).getSymptom());
        if (mapSet.containsKey(symptoms.get(position).getSymptom())){
            holder.checkbox_symptons.setChecked(true);
        }else{
            holder.checkbox_symptons.setChecked(false);
        }
        holder.checkbox_symptons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DataModel tempModel = symptoms.get(position);
                    tempModel.setChecked(true);
                    mapSet.put(symptoms.get(position).getSymptom(),position);
                }else{
                    DataModel tempModel = symptoms.get(position);
                    tempModel.setChecked(false);
                    mapSet.remove(symptoms.get(position).getSymptom());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(symptoms != null){
            return symptoms.size();
        }else{
            return 0;
        }
    }

}

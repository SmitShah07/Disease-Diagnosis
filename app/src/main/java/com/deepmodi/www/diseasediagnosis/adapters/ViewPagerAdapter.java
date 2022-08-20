package com.deepmodi.www.diseasediagnosis.adapters;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.deepmodi.www.diseasediagnosis.R;
import com.deepmodi.www.diseasediagnosis.interfaces.BodyClickListner;
import com.deepmodi.www.diseasediagnosis.model.BodyParts;
import com.deepmodi.www.diseasediagnosis.model.DataModel;
import com.deepmodi.www.diseasediagnosis.viewholder.CustomViewHolder;
import com.deepmodi.www.diseasediagnosis.viewholder.GeneralQuestionsViewHolder;
import com.deepmodi.www.diseasediagnosis.viewholder.SelectAgeViewHolder;
import com.deepmodi.www.diseasediagnosis.viewholder.SelectGenderViewHolder;
import com.google.android.material.slider.Slider;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    LayoutInflater inflater;
    List<Integer> viewHolderList;
    Context context;
    List<DataModel> symptoms;
    Map<String,String> selectedData;
    Map<String,Integer> mapSet;

    private static int SELECT_AGE = 1;
    private static int SELECT_GENDER = 2;
    private static int SELECT_GENERAL_QUESTIONS = 3;
    private static int SELECT_GENERAL_VIEW = 4;

    public ViewPagerAdapter(Context context, List<Integer> viewHolderList, List<DataModel> symptoms, Map<String,String> selectedData,Map<String,Integer> mapSet){
        inflater = LayoutInflater.from(context);
        this.viewHolderList = viewHolderList;
        this.context = context;
        this.symptoms = symptoms;
        this.selectedData = selectedData;
        this.mapSet = mapSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == SELECT_AGE){
            view = inflater.inflate(R.layout.select_age_item_view,parent,false);
            return new SelectAgeViewHolder(view);
        }else if (viewType == SELECT_GENDER){
            view = inflater.inflate(R.layout.select_gender_item_view,parent,false);
            return new SelectGenderViewHolder(view);
        }else if (viewType == SELECT_GENERAL_QUESTIONS){
            view = inflater.inflate(R.layout.general_questions_itemview,parent,false);
            return new GeneralQuestionsViewHolder(view);
        }else{
            view = inflater.inflate(R.layout.itemview_viewpager,parent,false);
            return new CustomViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == SELECT_AGE){
            final SelectAgeViewHolder selectAgeViewHolder = ((SelectAgeViewHolder)holder);
            selectAgeViewHolder.slider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                    selectAgeViewHolder.textView_selected_age.setText("Age: "+String.valueOf((int)value));
                    if (fromUser){
                        if (selectedData.containsKey("age")){
                            selectedData.remove("age");
                            selectedData.put("age",String.valueOf(value));
                        }else{
                            selectedData.put("age",String.valueOf(value));
                        }
                    }
                }
            });
        }else if (getItemViewType(position) == SELECT_GENDER){
           final SelectGenderViewHolder selectGenderViewHolder = ((SelectGenderViewHolder)holder);
           selectGenderViewHolder.male_card.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  if (selectedData.containsKey("gender")){
                      selectedData.remove("gender");
                      selectedData.put("gender","male");
                      selectGenderViewHolder.male_card.setCardBackgroundColor(context.getResources().getColor(R.color.dark_green));
                      selectGenderViewHolder.male_card.setStrokeColor(context.getResources().getColor(R.color.light_green));
                      selectGenderViewHolder.female_card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                      selectGenderViewHolder.female_card.setStrokeColor(context.getResources().getColor(R.color.text_color));
                  }else{
                      selectedData.put("gender","male");
                      selectGenderViewHolder.male_card.setCardBackgroundColor(context.getResources().getColor(R.color.dark_green));
                      selectGenderViewHolder.male_card.setStrokeColor(context.getResources().getColor(R.color.light_green));
                      selectGenderViewHolder.female_card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                      selectGenderViewHolder.female_card.setStrokeColor(context.getResources().getColor(R.color.text_color));
                  }
               }
           });
           selectGenderViewHolder.female_card.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (selectedData.containsKey("gender")){
                       selectedData.remove("gender");
                       selectedData.put("gender","female");
                       selectGenderViewHolder.female_card.setCardBackgroundColor(context.getResources().getColor(R.color.dark_green));
                       selectGenderViewHolder.female_card.setStrokeColor(context.getResources().getColor(R.color.light_green));
                       selectGenderViewHolder.male_card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                       selectGenderViewHolder.male_card.setStrokeColor(context.getResources().getColor(R.color.text_color));
                   }else{
                       selectGenderViewHolder.female_card.setCardBackgroundColor(context.getResources().getColor(R.color.dark_green));
                       selectGenderViewHolder.female_card.setStrokeColor(context.getResources().getColor(R.color.light_green));
                       selectGenderViewHolder.male_card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                       selectGenderViewHolder.male_card.setStrokeColor(context.getResources().getColor(R.color.text_color));
                       selectedData.put("gender","female");
                   }
               }
           });

        }else if (getItemViewType(position) == SELECT_GENERAL_QUESTIONS){
           GeneralQuestionsViewHolder generalQuestionsViewHolder = ((GeneralQuestionsViewHolder) holder);
           generalQuestionsViewHolder.textInputFirstName.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String firstName = s.toString();
                   if (selectedData.containsKey("firstName")) {
                       selectedData.remove("firstName");
                       selectedData.put("firstName", firstName);
                   }else{
                       selectedData.put("firstName", firstName);
                   }
               }

               @Override
               public void afterTextChanged(Editable s) {

               }
           });
            generalQuestionsViewHolder.textInputLastName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String lastName = s.toString();
                    if (selectedData.containsKey("lastName")) {
                        selectedData.remove("lastName");
                        selectedData.put("lastName", lastName);
                    }else{
                        selectedData.put("lastName", lastName);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else{
            CustomViewHolder customViewHolder = ((CustomViewHolder) holder);
            BodyPartsAdapter adapter = new BodyPartsAdapter(context,symptoms,selectedData,mapSet);
            customViewHolder.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (viewHolderList.get(position) == SELECT_AGE){
            return SELECT_AGE;
        }else if (viewHolderList.get(position) == SELECT_GENDER){
            return SELECT_GENDER;
        }else if(viewHolderList.get(position) == SELECT_GENERAL_QUESTIONS){
            return SELECT_GENERAL_QUESTIONS;
        }else{
            return SELECT_GENERAL_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        if (viewHolderList != null){
            return viewHolderList.size();
        }else{
            return 0;
        }
    }
}

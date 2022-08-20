package com.deepmodi.www.diseasediagnosis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.deepmodi.www.diseasediagnosis.adapters.BodyPartsAdapter;
import com.deepmodi.www.diseasediagnosis.adapters.ViewPagerAdapter;
import com.deepmodi.www.diseasediagnosis.model.BodyParts;
import com.deepmodi.www.diseasediagnosis.model.DataModel;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    Button btn_next,btn_back,btn_done;
    List<DataModel> symptoms = new ArrayList<>();
    Interpreter tf_lite;
    private float[] inputValue = new float[132];
    float[][] outputValue = new float[1][41];
    int tempCounter = 0;
    Map<String,String> selectedData = new HashMap<>();
    Map<String,Integer> mapSet = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final List<Integer> viewHolders = new ArrayList<>();
        viewHolders.add(3);
        viewHolders.add(2);
        viewHolders.add(1);
        viewHolders.add(4);


        viewPager2 = findViewById(R.id.viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (viewPager2.getCurrentItem() > 0){
                    btn_back.setVisibility(View.VISIBLE);
                }else{
                    btn_back.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        btn_done = findViewById(R.id.btn_done);
        btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1,true);
                if(viewPager2.getCurrentItem() > 0){
                    btn_back.setVisibility(View.VISIBLE);
                }else{
                    btn_back.setVisibility(View.GONE);
                    btn_done.setVisibility(View.GONE);
                }
                if(viewPager2.getCurrentItem() == viewHolders.size()-1){
                    btn_done.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.GONE);
                }
            }
        });

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager2.getCurrentItem() > 0){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1,true);
                    btn_done.setVisibility(View.GONE);
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    btn_done.setVisibility(View.GONE);
                    btn_back.setVisibility(View.GONE);
                }
            }
        });
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCounter = 0;
                for(int i=0;i<inputValue.length;i++) {
                    DataModel dataModel = symptoms.get(i);
                    if(dataModel.isChecked()) {
                        inputValue[i] = 1;
                        tempCounter++;
                    }
                    else {
                        inputValue[i] = 0;
                    }
                }
                Log.d("MainActivity", Arrays.toString(inputValue));
                if (tempCounter >=3) {
                    getOutput();
                }else{
                    Toast.makeText(MainActivity.this, "Please select at least 3 symptoms.", Toast.LENGTH_LONG).show();
                }
            }
        });

        getCheckBox();

        viewPager2.setAdapter(new ViewPagerAdapter(this, viewHolders, symptoms, selectedData,mapSet));
    }

    private void getCheckBox() {
        String[] temp_symptoms = {"Itching", "Skin rash", "Nodal skin eruptions", "Continuous sneezing",
                "Shivering", "Chills", "Joint pain", "Stomach pain", "Acidity", "Ulcers on tongue",
                "Muscle wasting", "Vomiting", "Burning micturition", "Spotting urination", "Fatigue",
                "Weight gain", "Anxiety", "Cold hands and feets", "Mood swings", "Weight loss", "Restlessness",
                "Lethargy", "Patches in throat", "Irregular sugar level", "Cough", "High fever", "Sunken eyes",
                "Breathlessness", "Sweating", "Dehydration", "Indigestion", "Headache", "Yellowish skin", "Dark urine",
                "Nausea", "Loss of appetite", "Pain behind the eyes", "Back pain", "Constipation",
                "Abdominal pain", "Diarrhoea", "Mild fever", "Yellow urine", "Yellowing of eyes",
                "Acute liver failure", "Fluid overload", "Swelling of stomach", "Swelled lymph nodes",
                "Malaise", "Blurred and distorted vision", "Phlegm", "Throat irritation", "Redness of eyes",
                "Sinus pressure", "Runny nose", "Congestion", "Chest pain", "Weakness in limbs", "Fast heart rate",
                "Pain during bowel movements", "Pain in anal region", "Bloody stool", "Irritation in anus",
                "Neck pain", "Dizziness", "Cramps", "Bruising", "Obesity", "Swollen legs", "Swollen blood vessels",
                "Puffy face and eyes", "Enlarged thyroid", "Brittle nails", "Swollen extremeties",
                "Excessive hunger", "Extra marital contacts", "Drying and tingling lips", "Slurred speech",
                "Knee pain", "Hip joint pain", "Muscle weakness", "Stiff neck", "Swelling joints", "Movement stiffness", "Spinning movements",
                "Loss of balance", "Unsteadiness", "Weakness of one body side", "Loss of smell", "Bladder discomfort", "Foul smell of urine",
                "Continuous feel of urine", "Passage of gases", "Internal itching", "Toxic look typhos", "Depression", "Irritability", "Muscle pain",
                "Altered sensorium", "Red spots over body", "Belly pain", "Abnormal menstruation", "Dischromic patches", "Watering from eyes",
                "Increased appetite", "Polyuria", "Family history", "Mucoid sputum", "Rusty sputum", "Lack of concentration", "Visual disturbances",
                "Receiving blood transfusion", "Receiving unsterile injections", "Coma", "Stomach bleeding", "Distention of abdomen", "History of alcohol consumption",
                "Fluid overload", "Blood in sputum", "Prominent veins on calf", "Palpitations", "Painful walking", "Pus filled pimples", "Blackheads", "Scurring",
                "Skin peeling", "Silver like dusting", "Small dents in nails", "Inflammatory nails", "Blister", "Red sore around nose", "Yellow crust ooze"};

        for (String temp_symptom : temp_symptoms) {
            symptoms.add(new DataModel(temp_symptom, false));
        }
    }

    private void inference() {
        try {
            tf_lite = new Interpreter(loadModelFile());
        } catch(Exception e){
            e.printStackTrace();
        }
        tf_lite.run(inputValue,outputValue);
    }

    private void getOutput() {
        inference();
        double []probability = new double[41];
        for(int i=0;i<41;i++) {
            probability[i] = Math.floor(outputValue[0][i] *100);
        }

        double max_prob = probability[0];
        int index = 0;
        for(int i=0;i<probability.length;i++) {
            if(max_prob<probability[i]) {
                max_prob = probability[i];
                index = i;
            }
        }
        Intent intent = new Intent(MainActivity.this,TestingActivity.class);
        intent.putExtra("max_prob",max_prob);
        intent.putExtra("index",index);
        if (selectedData.containsKey("firstName")){
            intent.putExtra("firstName",selectedData.get("firstName"));
        }else{
            intent.putExtra("firstName","Guest");
        }
        if (selectedData.containsKey("lastName")){
            intent.putExtra("lastName",selectedData.get("lastName"));
        }else{
            intent.putExtra("lastName","User");
        }
        if (selectedData.containsKey("gender")){
            intent.putExtra("gender",selectedData.get("gender"));
        }else{
            intent.putExtra("gender","Default gender");
        }
        if (selectedData.containsKey("age")){
            intent.putExtra("age",String.valueOf(selectedData.get("age")));
        }else{
            intent.putExtra("age","Default age");
        }
        startActivity(intent);
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("keras_final_model.tflite");
        FileInputStream fileInputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffSets = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffSets,declaredLength);
    }
}
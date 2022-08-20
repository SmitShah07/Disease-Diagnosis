package com.deepmodi.www.diseasediagnosis.model;

public class DataModel {
    private String symptom;
    private boolean checked;


    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public DataModel(String symptom, boolean checked) {
        this.symptom = symptom;
        this.checked = checked;

    }
}

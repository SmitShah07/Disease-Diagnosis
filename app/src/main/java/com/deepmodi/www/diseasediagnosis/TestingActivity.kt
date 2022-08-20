package com.deepmodi.www.diseasediagnosis

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.netopen.hotbitmapgg.library.view.RingProgressBar
import java.text.MessageFormat
import java.util.*

class TestingActivity : AppCompatActivity() {
    private var probability = 0.0
    private var index = 0
    private var resultTV: TextView? = null
    private  var diseaseTV:TextView? = null
    private  var medTV1:TextView? = null
    private  var medTV2:TextView? = null
    private  var medTV3:TextView? = null
    lateinit var ringProgressBar2: RingProgressBar
    lateinit var firstName : String
    lateinit var lastName : String
    lateinit var gender : String
    lateinit var age : String
    lateinit var patientName : TextView
    lateinit var patientAge : TextView
    lateinit var patientGender : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

        resultTV = findViewById(R.id.result_tv)
        diseaseTV = findViewById<TextView>(R.id.name_tv)
        ringProgressBar2 = findViewById(R.id.progress_circular2)
        medTV1 = findViewById<TextView>(R.id.mad_tv1)
        medTV2 = findViewById<TextView>(R.id.mad_tv2)
        medTV3 = findViewById<TextView>(R.id.mad_tv3)
        patientAge = findViewById(R.id.textView_name_age)
        patientName = findViewById(R.id.textView_name_patient)
        patientGender = findViewById(R.id.textView_name_gender)

        val intent = intent
        probability = intent.getDoubleExtra("max_prob", 0.0)
        index = intent.getIntExtra("index", 0)
        firstName = intent.getStringExtra("firstName")!!
        lastName = intent.getStringExtra("lastName")!!
        gender = intent.getStringExtra("gender")!!
        age = intent.getStringExtra("age")!!
        ringProgressBar2.setProgress(probability.toInt())
        getDiseaseName()
        getMedicines()
        patientName.text = MessageFormat.format("{0} {1}",firstName,lastName)
        patientAge.text = MessageFormat.format("{0} {1} {2}","Age: ",age,"years")
        patientGender.text = MessageFormat.format("{0} {1}","Gender: ",gender)
    }

    private fun getMedicines() {
        val medicines = arrayOf(arrayOf("Itraconazole", "Fluconazole", "Albaconazole"), arrayOf("Cetirizine", "Fexofenadine", "Desloratadine"), arrayOf("Riopan", "Rolaids", "Mylanta"), arrayOf("anabolic steroids", "amoxicillin", "minocycline"), arrayOf("antihistamine", "diphenhydramine", "Benadryl"), arrayOf("omeprazole", "lansoprazole", "rabeprazole"), arrayOf("abacavir", "lamivudine", "zidovudine"), arrayOf("Metformin", "metformin", "Meglitinides"), arrayOf("bismuth subsalicylate", "loperamide", " Ibuprofen"), arrayOf("bronchodilators", "corticosteroids", "Steroids"), arrayOf("Angiotensin-converting enzyme", "Chlorothiazide", "Bumetanide "), arrayOf("sumatriptan", "rizatriptan", "Topiramate "), arrayOf("Corticosteroids", "acetaminophen", "hydrocodone"), arrayOf("Punarnava powder", "Ashwagandha powder", "Majoon-e-Azaraqi "), arrayOf("Carduus Marianus", "Carduus Marianus", "Nux Vomica"), arrayOf("Mefloquine", "Primaquine phosphate", "quinine"), arrayOf("acyclovir", "Privigen", "Dulcamara"), arrayOf("Acetaminophen", " aspirin", "Eupatorium Perfoliatum "), arrayOf("Ciprofloxacin ", "Azithromycin", "cepalosporin, "), arrayOf("Your body will clear the hepatitis A virus on its own", "Rest more", "Avoid alcohol"), arrayOf("Viread", "Vemlidy", "Rest more"), arrayOf("simeprevir", "sofosbuvir", "Rest more"), arrayOf("interferon", "Avoid alcohol", "Rest more"), arrayOf("Your body will clear the hepatitis A virus on its own", "Avoid alcohol", "Rest more"), arrayOf("Your body will clear the hepatitis A virus on its own", "Avoid alcohol", "Rest more"), arrayOf("isoniazid", "rifampin", "Ethambutol"), arrayOf("acetaminophen", "decongestant", "Vicks Nyquil Severe"), arrayOf("Macrolide antibiotics", "Fluoroquinolones", "Tetracyclines"), arrayOf("Eat high-fiber foods", "Soak regularly in a warm bath or sitz bath", "Take oral pain relievers"), arrayOf("Aspirin", "Thrombolytics", "Pain relievers"), arrayOf("polidocanol", "Sotradecol", "Asclera"), arrayOf("Westhroid", "Cytomel", "Levothyroxine"), arrayOf("Propylthiouracil ", "Methimazole", "Agranulocytosis"), arrayOf("glucagon", "diazoxide", "Biguanides"), arrayOf("Acetaminophen", "Duloxetine ", "Nonsteroidal anti-inflammatory"), arrayOf("Painkillers", "Counterirritants", "hydroxychloroquine"), arrayOf("dimenhydrinate ", "meclizine", "vestibular rehabilitation"), arrayOf("Retinoids", "Dapsone", "Antibiotics."), arrayOf("penicillin", "Fosfomycin", "antibiotics"), arrayOf("Corticosteroids", "Retinoids", "Anthralin"), arrayOf("Augmentin", "Cleocin", "minocycline"))
        val temp = arrayOfNulls<String>(3)
        for (i in 0..2) {
            temp[i] = medicines[index][i]
        }
        medTV1?.setText(temp[0])
        medTV2?.setText(temp[1])
        medTV3?.setText(temp[2])
    }


    private fun getDiseaseName() {
        val dict = Hashtable<Int, String>()
        val diseases = arrayOf("Fungal infection", "Allergy", "GERD", "Chronic cholestasis",
                "Drug Reaction", "Peptic ulcer disease", "AIDS", "Diabetes ", "Gastroenteritis", "Bronchial Asthma", "Hypertension ", "Migraine",
                "Cervical spondylosis", "Paralysis (brain hemorrhage)", "Jaundice", "Malaria", "Chicken pox", "Dengue", "Typhoid", "hepatitis A", "Hepatitis B", "Hepatitis C",
                "Hepatitis D", "Hepatitis E", "Alcoholic hepatitis", "Tuberculosis", "Common Cold", "Pneumonia", "Dimorphic hemorrhoids(piles)", "Heart attack",
                "Varicose veins", "Hypothyroidism", "Hyperthyroidism", "Hypoglycemia", "Osteoarthristis", "Arthritis", "(vertigo) Paroymsal  Positional Vertigo", "Acne",
                "Urinary tract infection", "Psoriasis", "Impetigo")
        for (i in 0..40) {
            dict[i] = diseases[i]
        }
        val final_name = dict[index]
        resultTV!!.text = MessageFormat.format("There are {0}% chances that you are suffering from {1}", probability, final_name)
        diseaseTV?.text = final_name
    }
}
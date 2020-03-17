package com.tahirabuzetoglu.corona_survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {


    private static final String MY_DATA = "MY_DATA";
    private static final String MY_LANGUAGE = "MY_LANGUAGE";

    private SharedPreferences myData;
    private static SharedPreferences.Editor myEditor;

    private boolean isEng = false;
    
    private int frequencyLanguage =  R.array.frequency;

    private TextView tv_result;

    private TextView tv_calculate;
    private TextView tv_fever;
    private TextView tv_tiredness;
    private TextView tv_dry_cough;
    private TextView tv_breath;
    private TextView tv_cough;
    private TextView tv_soreness;
    private TextView tv_sneeze;
    private TextView tv_runny_nose;
    private TextView tv_nose_congestion;
    private TextView tv_eye;
    private TextView tv_sore_throat;
    private TextView tv_diarrhea;
    
    
    private ImageView iv_scroll;

    private Spinner sp_heat;
    private Spinner sp_tiredness;
    private Spinner sp_dry_cough;
    private Spinner sp_hard_breath;
    private Spinner sp_cough;
    private Spinner sp_pain;
    private Spinner sp_sneeze;
    private Spinner sp_runny_nose;
    private Spinner sp_nose_congestion;
    private Spinner sp_watery_eye;
    private Spinner sp_sore_throat;
    private Spinner sp_diarrhea;


    //Heat values
    private String currentHeatFrequency;
    private int heatRateOld = 0;
    private int heatRateNew = 0;


    //Tiredness values
    private String currentTirednessFrequency;
    private int tirednessRateOld = 0;
    private int tirednessRateNew = 0;

    //Tiredness values
    private String currentDryCoughFrequency;
    private int dryCoughRateOld = 0;
    private int dryCoughRateNew = 0;

    //Tiredness values
    private String currentHardBreathFrequency;
    private int hardBreathRateOld = 0;
    private int hardBreathRateNew = 0;

    //Tiredness values
    private String currentCoughFrequency;
    private int coughRateOld = 0;
    private int coughRateNew = 0;

    //Tiredness values
    private String currentPainFrequency;
    private int painRateOld = 0;
    private int painRateNew = 0;

    //Tiredness values
    private String currentSnezeeFrequency;
    private int sneezeRateOld = 0;
    private int sneezeRateNew = 0;

    //Tiredness values
    private String currentRunnyNoseFrequency;
    private int runnyNosesRateOld = 0;
    private int runnyNoseRateNew = 0;

    //Tiredness values
    private String currentNoseCongestionFrequency;
    private int noseCongestionRateOld = 0;
    private int noseCongestionRateNew = 0;

    //Tiredness values
    private String currentWateryEyeFrequency;
    private int wateryEyeRateOld = 0;
    private int wateryEyeRateNew = 0;


    //Tiredness values
    private String currentThroathAcheFrequency;
    private int throathAcheRateOld = 0;
    private int throathAcheRateNew = 0;

    //Tiredness values
    private String currentDiarrheaFrequency;
    private int diarrheaRateOld = 0;
    private int diarrheaRateNew = 0;



    private int sum;
    private ArrayAdapter<CharSequence> frequencies;
    private ArrayAdapter<CharSequence> rates;


    //Advertising
    private InterstitialAd interstitialAd;
    private AdView mAdView;


    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // tv_result = findViewById(R.id.tv_result);

        tv_calculate = findViewById(R.id.tv_calculate);
        tv_fever = findViewById(R.id.tv_fever);
        tv_dry_cough = findViewById(R.id.tv_dry_cough);
        tv_tiredness = findViewById(R.id.tv_tiredness);
        tv_breath = findViewById(R.id.tv_breath);
        tv_cough = findViewById(R.id.tv_cough);
        tv_sneeze = findViewById(R.id.tv_sneeze);
        tv_soreness = findViewById(R.id.tv_soreness);
        tv_runny_nose = findViewById(R.id.tv_runny_nose);
        tv_nose_congestion = findViewById(R.id.tv_nose_congestion);
        tv_eye = findViewById(R.id.tv_eye);
        tv_sore_throat = findViewById(R.id.tv_sore_throat);
        tv_diarrhea = findViewById(R.id.tv_diarrhea);
        
        iv_scroll = findViewById(R.id.iv_scroll);

        setAnimation();

        setEditorAndPrefs();
        loadMe();


        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-4049974656278684~7309122887");
        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-4049974656278684/4892565305");
        //Test için alttaki
        //interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        LoadInterstitialAd();

        //Banner ADVERSISEMENT
        mAdView = findViewById(R.id.adView);
        //Test add ca-app-pub-3940256099942544/6300978111
        //Orijinal ca-app-pub-4049974656278684/3369877871
        LoadBanner();



      /*  frequencies = ArrayAdapter.createFromResource(this, frequencyLanguage, android.R.layout.simple_list_item_1);
        rates = ArrayAdapter.createFromResource(this, R.array.rates, android.R.layout.simple_list_item_1);*/

      changeLanguage();

        spinnerHeat();
        spinnerTiredness();
        spinnerDryCough();
        spinnerHardBreath();
        spinnerDryCough();
        spinnerCough();
        spinnerRunnyNose();
        spinnerPain();
        spinnerSneeze();
        spinnerNoseCongestion();
        spinnerWateryEye();
        spinnerThroathAche();
        spinnerDiarrhea();


        tv_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCorona()){
                    Intent intent = new Intent(MainActivity.this, CoronaActivity.class);
                    showInterstitial();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, HealthActivity.class);
                    showInterstitial();
                    startActivity(intent);
                }

            }
        });

    }

    private void changeLanguage(){
        if(isEng){
            frequencyLanguage = R.array.frequency;
            frequencies = ArrayAdapter.createFromResource(this, frequencyLanguage, android.R.layout.simple_list_item_1);
            rates = ArrayAdapter.createFromResource(this, R.array.rates, android.R.layout.simple_list_item_1);

            tv_calculate.setText(R.string.calculate);
            tv_fever.setText(R.string.fever);
            tv_tiredness.setText(R.string.tiredness);
            tv_dry_cough.setText(R.string.dry_cough);
            tv_breath.setText(R.string.hard_breath);
            tv_cough.setText(R.string.cough);
            tv_soreness.setText(R.string.pain);
            tv_runny_nose.setText(R.string.runny_nose);
            tv_nose_congestion.setText(R.string.nose_congestion);
            tv_eye.setText(R.string.watery_eye);
            tv_sore_throat.setText(R.string.sore_throat);
            tv_diarrhea.setText(R.string.diarrhea);

        }else{
            frequencyLanguage = R.array.frequency_tr;
            frequencies = ArrayAdapter.createFromResource(this, frequencyLanguage, android.R.layout.simple_list_item_1);
            rates = ArrayAdapter.createFromResource(this, R.array.rates_tr, android.R.layout.simple_list_item_1);

            tv_calculate.setText(R.string.calculate_tr);
            tv_fever.setText(R.string.fever_tr);
            tv_dry_cough.setText(R.string.dry_cough_tr);
            tv_tiredness.setText(R.string.tiredness_tr);
            tv_breath.setText(R.string.hard_breath_tr);
            tv_cough.setText(R.string.cough_tr);
            tv_sneeze.setText(R.string.sneeze_tr);
            tv_soreness.setText(R.string.pain_tr);
            tv_runny_nose.setText(R.string.runny_nose_tr);
            tv_nose_congestion.setText(R.string.nose_congestion_tr);
            tv_eye.setText(R.string.watery_eye_tr);
            tv_sore_throat.setText(R.string.sore_throat_tr);
            tv_diarrhea.setText(R.string.diarrhea_tr);
        }
    }

    private void LoadBanner(){
        if(!mAdView.isLoading()){
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
    }

    private void LoadInterstitialAd() {
        // Request a new ad if one isn't already loaded.
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
            //  Toast.makeText(this, "Reklam Hazır", Toast.LENGTH_SHORT).show();
        }
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }


    private void setAnimation(){
        YoYo.with(Techniques.BounceInUp).duration(1000).repeat(YoYo.INFINITE).playOn(iv_scroll);
    }
    
    Boolean isCorona(){
        if((sum >= 80 &&
                (currentHeatFrequency == frequencies.getItem(3) ||  currentHeatFrequency  == frequencies.getItem(4)) &&
                (currentTirednessFrequency  == frequencies.getItem(3) || currentTirednessFrequency  == frequencies.getItem(4)) &&
                (currentDryCoughFrequency   == frequencies.getItem(3) ||currentDryCoughFrequency == frequencies.getItem(4)) &&
                (currentHardBreathFrequency == frequencies.getItem(3) || currentHardBreathFrequency  == frequencies.getItem(4)) &&
                (currentCoughFrequency  == frequencies.getItem(3) ||currentCoughFrequency  == frequencies.getItem(4)) &&
                (currentPainFrequency  == frequencies.getItem(2) || currentPainFrequency  == frequencies.getItem(3)) &&
                (currentSnezeeFrequency  == frequencies.getItem(0) ||currentSnezeeFrequency  == frequencies.getItem(1)) &&
                (currentRunnyNoseFrequency   == frequencies.getItem(2) || currentRunnyNoseFrequency  == frequencies.getItem(3)) &&
                (currentNoseCongestionFrequency == frequencies.getItem(0) || currentNoseCongestionFrequency == frequencies.getItem(1) ) &&
                (currentWateryEyeFrequency == frequencies.getItem(0) || currentWateryEyeFrequency == frequencies.getItem(1)) &&
                (currentThroathAcheFrequency  == frequencies.getItem(2) || currentThroathAcheFrequency  == frequencies.getItem(3)) &&
                (currentDiarrheaFrequency   == frequencies.getItem(2) || currentDiarrheaFrequency   == frequencies.getItem(3)))
                    ||
                        (sum >= 20 &&
                                (currentHeatFrequency == frequencies.getItem(2) ||  currentHeatFrequency  == frequencies.getItem(3) ||  currentHeatFrequency  == frequencies.getItem(4)) &&
                                (currentDryCoughFrequency   == frequencies.getItem(3) ||currentDryCoughFrequency == frequencies.getItem(4)) &&
                                (currentHardBreathFrequency == frequencies.getItem(3) || currentHardBreathFrequency  == frequencies.getItem(4))
                        )
                    ||
                        (sum >= 20 &&
                                (currentDryCoughFrequency   == frequencies.getItem(3) ||currentDryCoughFrequency == frequencies.getItem(4)) &&
                                (currentHardBreathFrequency == frequencies.getItem(3) || currentHardBreathFrequency  == frequencies.getItem(4))
                        )
                    ||
                        (
                                (currentHeatFrequency == frequencies.getItem(2) ||  currentHeatFrequency  == frequencies.getItem(3) ||  currentHeatFrequency  == frequencies.getItem(4)) &&
                                (currentHardBreathFrequency == frequencies.getItem(3) || currentHardBreathFrequency  == frequencies.getItem(4))
                        )
                    ||
                        (
                                (currentDryCoughFrequency   == frequencies.getItem(3) ||currentDryCoughFrequency == frequencies.getItem(4)) &&
                                (currentHardBreathFrequency == frequencies.getItem(2) || currentHardBreathFrequency  == frequencies.getItem(3) || currentHardBreathFrequency  == frequencies.getItem(4))
                        )
                    ||
                        (
                                (currentHeatFrequency == frequencies.getItem(2) ||  currentHeatFrequency  == frequencies.getItem(3) ||  currentHeatFrequency  == frequencies.getItem(4)) &&
                                (currentDryCoughFrequency   == frequencies.getItem(2) ||currentDryCoughFrequency == frequencies.getItem(3) ||currentDryCoughFrequency == frequencies.getItem(4)) &&
                                (currentHardBreathFrequency == frequencies.getItem(2) || currentHardBreathFrequency  == frequencies.getItem(3) || currentHardBreathFrequency  == frequencies.getItem(4))
                        )
        ){
            return true;
        }else{
            return false;
        }
    }


    private void spinnerHeat() {

        sp_heat = findViewById(R.id.sp_heat);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_heat.setAdapter(adapterSpinner);
        sp_heat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentHeatFrequency = parent.getItemAtPosition(position).toString();

                heatRateOld = heatRateNew;
                heatRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += heatRateNew;
                sum -= heatRateOld;
                
               //  //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentHeatFrequency = "Choose";
            }
        });
    }

    private void spinnerTiredness() {

        sp_tiredness = findViewById(R.id.sp_tiredness);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_tiredness.setAdapter(adapterSpinner);
        sp_tiredness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentTirednessFrequency = parent.getItemAtPosition(position).toString();

                tirednessRateOld = tirednessRateNew;
                tirednessRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += tirednessRateNew;
                sum -= tirednessRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerDryCough() {

        sp_dry_cough = findViewById(R.id.sp_dry_cough);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_dry_cough.setAdapter(adapterSpinner);
        sp_dry_cough.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentDryCoughFrequency = parent.getItemAtPosition(position).toString();

                dryCoughRateOld = dryCoughRateNew;
                dryCoughRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += dryCoughRateNew;
                sum -= dryCoughRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerHardBreath() {

        sp_hard_breath = findViewById(R.id.sp_hard_breath);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_hard_breath.setAdapter(adapterSpinner);
        sp_hard_breath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentHardBreathFrequency = parent.getItemAtPosition(position).toString();

                hardBreathRateOld = hardBreathRateNew;
                hardBreathRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += hardBreathRateNew;
                sum -= hardBreathRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerCough() {

        sp_cough = findViewById(R.id.sp_cough);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_cough.setAdapter(adapterSpinner);
        sp_cough.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCoughFrequency = parent.getItemAtPosition(position).toString();

                coughRateOld = coughRateNew;
                coughRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += coughRateNew;
                sum -= coughRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerPain() {

        sp_pain = findViewById(R.id.sp_pain);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_pain.setAdapter(adapterSpinner);
        sp_pain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentPainFrequency = parent.getItemAtPosition(position).toString();

                painRateOld = painRateNew;
                painRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += painRateNew;
                sum -= painRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerSneeze() {

        sp_sneeze = findViewById(R.id.sp_sneeze);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_sneeze.setAdapter(adapterSpinner);
        sp_sneeze.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSnezeeFrequency = parent.getItemAtPosition(position).toString();

                sneezeRateOld = sneezeRateNew;
                sneezeRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += sneezeRateNew;
                sum -= sneezeRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerRunnyNose() {

        sp_runny_nose = findViewById(R.id.sp_runny_nose);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_runny_nose.setAdapter(adapterSpinner);
        sp_runny_nose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentRunnyNoseFrequency = parent.getItemAtPosition(position).toString();

                runnyNosesRateOld = runnyNoseRateNew;
                runnyNoseRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += runnyNoseRateNew;
                sum -= runnyNosesRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerNoseCongestion() {

        sp_nose_congestion = findViewById(R.id.sp_nose_congestion);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_nose_congestion.setAdapter(adapterSpinner);
        sp_nose_congestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentNoseCongestionFrequency = parent.getItemAtPosition(position).toString();

                noseCongestionRateOld = noseCongestionRateNew;
                noseCongestionRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += noseCongestionRateNew;
                sum -= noseCongestionRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerWateryEye() {

        sp_watery_eye = findViewById(R.id.sp_watery_eye);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_watery_eye.setAdapter(adapterSpinner);
        sp_watery_eye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentWateryEyeFrequency = parent.getItemAtPosition(position).toString();

                wateryEyeRateOld = wateryEyeRateNew;
                wateryEyeRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += wateryEyeRateNew;
                sum -= wateryEyeRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerThroathAche() {

        sp_sore_throat = findViewById(R.id.sp_throat_ache);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_sore_throat.setAdapter(adapterSpinner);
        sp_sore_throat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentThroathAcheFrequency = parent.getItemAtPosition(position).toString();

                throathAcheRateOld = throathAcheRateNew;
                throathAcheRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += throathAcheRateNew;
                sum -= throathAcheRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void spinnerDiarrhea() {

        sp_diarrhea = findViewById(R.id.sp_diarrhea);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, frequencyLanguage, R.layout.spinner_checked);
        adapterSpinner.setDropDownViewResource(R.layout.my_spinner_dropdown);
        sp_diarrhea.setAdapter(adapterSpinner);
        sp_diarrhea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentDiarrheaFrequency = parent.getItemAtPosition(position).toString();

                diarrheaRateOld = diarrheaRateNew;
                diarrheaRateNew =  Integer.valueOf(rates.getItem(position).toString());
                sum += diarrheaRateNew;
                sum -= diarrheaRateOld;
                 //tv_result.setText(String.valueOf(sum));
               // Toast.makeText(MainActivity.this, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentTirednessFrequency = "Choose";
            }
        });
    }

    private void setEditorAndPrefs(){
        myData = getSharedPreferences(MY_DATA ,MODE_PRIVATE);
        myEditor = myData.edit();
    }


    private void loadMe(){
        isEng =  myData.getBoolean(MY_LANGUAGE, false);
    }


}

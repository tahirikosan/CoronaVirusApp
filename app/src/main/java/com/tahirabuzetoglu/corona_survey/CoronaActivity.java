package com.tahirabuzetoglu.corona_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class CoronaActivity extends AppCompatActivity {


    private static final String MY_DATA = "MY_DATA";
    private static final String MY_LANGUAGE = "MY_LANGUAGE";

    private SharedPreferences myData;
    private static SharedPreferences.Editor myEditor;

    private boolean isEng = false;

    private ImageView iv_corona_1;
    private ImageView iv_corona_2;
    private ImageView iv_corona_3;

    private TextView tv_warning_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        tv_warning_content = findViewById(R.id.tv_warning_content);

        iv_corona_1 = findViewById(R.id.iv_corona_1);
        iv_corona_2 = findViewById(R.id.iv_corona_2);
        iv_corona_3 = findViewById(R.id.iv_corona_3);

        setEditorAndPrefs();
        loadMe();
        ChangeLanguage();

        setAnimation();
    }

    private void ChangeLanguage(){
        if(isEng){
            tv_warning_content.setText(R.string.warningContent);
        }else{
            tv_warning_content.setText(R.string.warningContent_tr);
        }
    }

    private void setAnimation(){
        YoYo.with(Techniques.Pulse).duration(1000).repeat(YoYo.INFINITE).playOn(iv_corona_1);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(YoYo.INFINITE).playOn(iv_corona_2);
        YoYo.with(Techniques.FadeOut).duration(1000).repeat(YoYo.INFINITE).playOn(iv_corona_3);
    }

    private void setEditorAndPrefs(){
        myData = getSharedPreferences(MY_DATA ,MODE_PRIVATE);
        myEditor = myData.edit();
    }


    private void loadMe(){
        isEng =  myData.getBoolean(MY_LANGUAGE, false);
    }
}


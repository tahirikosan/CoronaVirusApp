package com.tahirabuzetoglu.corona_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class HealthActivity extends AppCompatActivity {


    private static final String MY_DATA = "MY_DATA";
    private static final String MY_LANGUAGE = "MY_LANGUAGE";

    private SharedPreferences myData;
    private static SharedPreferences.Editor myEditor;

    private boolean isEng = false;

    private ImageView iv_health;
    private TextView tv_warning_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        iv_health = findViewById(R.id.iv_health);

        tv_warning_content = findViewById(R.id.tv_warning_content);


        setEditorAndPrefs();
        loadMe();
        ChangeLanguage();

        setAnimation();
    }

    private void ChangeLanguage(){
        if(isEng){
            tv_warning_content.setText(R.string.notificationContent);
        }else{
            tv_warning_content.setText(R.string.notificationContent_tr);
        }
    }


    private void setAnimation(){
        YoYo.with(Techniques.Pulse).duration(1000).repeat(YoYo.INFINITE).playOn(iv_health);
    }

    private void setEditorAndPrefs(){
        myData = getSharedPreferences(MY_DATA ,MODE_PRIVATE);
        myEditor = myData.edit();
    }


    private void loadMe(){
        isEng =  myData.getBoolean(MY_LANGUAGE, false);
    }
}

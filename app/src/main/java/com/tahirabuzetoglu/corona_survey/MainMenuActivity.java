package com.tahirabuzetoglu.corona_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenuActivity extends AppCompatActivity {

    private ImageView iv_turkey_flag;
    private ImageView iv_america_flag;

    private static final String MY_DATA = "MY_DATA";
    private static final String MY_LANGUAGE = "MY_LANGUAGE";
    private static final String MY_CHECKER = "MY_CHECKER";

    private SharedPreferences myData;
    private static SharedPreferences.Editor myEditor;


    private boolean isEng = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        iv_turkey_flag = findViewById(R.id.iv_turkey_flag);
        iv_america_flag = findViewById(R.id.iv_america_flag);

        setEditorAndPrefs();
        loadMe();

        iv_turkey_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
                isEng = false;

                saveMe();

                startActivity(intent);
            }
        });

        iv_america_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
                isEng = true;

                saveMe();

                startActivity(intent);
            }
        });
    }

    private void loadMe(){
        boolean isChecked =  myData.getBoolean(MY_CHECKER, false);

        if(isChecked){
            Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void saveMe(){
        myEditor.putBoolean(MY_LANGUAGE, isEng);
        myEditor.putBoolean(MY_CHECKER, true);
        myEditor.apply();
    }

    private void setEditorAndPrefs(){
        myData = getSharedPreferences(MY_DATA ,MODE_PRIVATE);
        myEditor = myData.edit();
    }
}

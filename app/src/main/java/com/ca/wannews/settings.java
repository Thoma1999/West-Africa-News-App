package com.ca.wannews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class settings extends AppCompatActivity {
    DMsharedPref dMsharedPref;
    Switch dmSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dMsharedPref = new DMsharedPref(this);
        dmSwitch = (Switch)findViewById(R.id.DMSwitch);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            dmSwitch.setChecked(true);
        }
        dmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dMsharedPref.setNightModeState(true);
                    restartApp();
                } else {
                    dMsharedPref.setNightModeState(false);
                    restartApp();
                }

            }

        });

    }
    public void restartApp () {
        Intent intent = new Intent(getApplicationContext(),settings.class);
        startActivity(intent);
        finish();
    }

}

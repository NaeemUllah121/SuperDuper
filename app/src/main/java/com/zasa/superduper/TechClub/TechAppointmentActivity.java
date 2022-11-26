package com.zasa.superduper.TechClub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.zasa.superduper.R;

public class TechAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_appointment);
    }

    public void btn_back(View view) {
        finish();
    }


    public void btn_GoToBack(View view) {
        finish();
    }
}
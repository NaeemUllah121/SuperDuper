package com.zasa.superduper.eLearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zasa.superduper.R;

public class LanguageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_details);
    }



    public void btn_Enroll(View view) {
        startActivity(new Intent(this, YTCoursePlaylistActivity.class));
        finish();
    }
}
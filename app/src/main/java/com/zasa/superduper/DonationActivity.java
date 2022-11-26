package com.zasa.superduper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DonationActivity extends AppCompatActivity {

    RadioGroup RG_DonationStatus;
    RadioButton RB_DonationStatus;
    String st_SelectedMethod;
    TextInputEditText et_CNICDonation, et_VoucherIdDonation;
    TextInputLayout lay_CNICDonation, lay_VoucherIdDonation;

    View lay_donation, lay_Parceldelivery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

    }

}
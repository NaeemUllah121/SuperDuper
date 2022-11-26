package com.zasa.superduper.ForgetPass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Utils.SharedPrefManager;

public class ForgetPassActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;
    EditText pass_Mobile,new_pass,cnf_pass,pass_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        context = ForgetPassActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        pass_Mobile=findViewById(R.id.pass_Mobile);
        new_pass=findViewById(R.id.new_pass);
        cnf_pass=findViewById(R.id.cnf_pass);
        pass_Email=findViewById(R.id.pass_Email);
    }

    public void btn_back(View view) {
        finish();
    }

    public void btn_UpdatePass(View view) {


        String st_pass_Mobile = pass_Mobile.getText().toString().trim();
        String st_new_pass = new_pass.getText().toString().trim();
        String st_cnf_pass = cnf_pass.getText().toString().trim();
        String st_pass_Email = pass_Email.getText().toString().trim();


        if (st_pass_Mobile.length() != 11) {
            pass_Mobile.requestFocus();
            pass_Mobile.setError("Enter correct mobile number!");
            return;
        }

        if (st_new_pass.length() < 6 || st_new_pass.isEmpty()) {
            new_pass.requestFocus();
            new_pass.setError("Password must contains 6-digits!");
            return;
        }
        if (!st_cnf_pass.equals(st_new_pass) || st_cnf_pass.isEmpty()) {
            cnf_pass.requestFocus();
            cnf_pass.setError("Password did not match!");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(st_pass_Email).matches()) {
            pass_Email.requestFocus();
            pass_Email.setError("Enter correct email!");
            return;
        }

        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }
}
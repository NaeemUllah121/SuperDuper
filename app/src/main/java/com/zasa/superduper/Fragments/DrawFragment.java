package com.zasa.superduper.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;


public class DrawFragment extends Fragment {


    ProgressDialog progressDialog;
    Context context;

    ImageView btn_backDraw;
    Button btn_continueShopping;

    public DrawFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_draw, container, false);

        context = getActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        btn_backDraw = view.findViewById(R.id.btn_backDraw);
        btn_backDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), HomeActivity.class));
                    requireActivity().finishAffinity();

            }
        });
        btn_continueShopping = view.findViewById(R.id.btn_ContinueShopping);
        btn_continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HomeActivity.class));
                    requireActivity().finishAffinity();


            }
        });

        return view;
    }
}
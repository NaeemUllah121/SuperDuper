package com.zasa.superduper.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Utils.Constant;
import com.zasa.superduper.Utils.SharedPrefManager;

public class InviteFragment extends Fragment {

    public InviteFragment() {
        // Required empty public constructor
    }

    Context context;
    SharedPrefManager sharedPrefManager;

    ImageView btn_back;
    Button btn_InviteFriends,btn_ReferralCode;
    TextInputEditText et_ReferralCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_invite, container, false);

        context = requireActivity();
        sharedPrefManager = new SharedPrefManager(context);

        btn_back=view.findViewById(R.id.btn_back);
        btn_InviteFriends=view.findViewById(R.id.btn_InviteFriends);
        btn_ReferralCode=view.findViewById(R.id.btn_ReferralCode);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                requireActivity().finishAffinity();
            }
        });

        btn_InviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.shareApp(context);
            }
        });
        btn_ReferralCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        return view;
    }
}
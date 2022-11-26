package com.zasa.superduper.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zasa.superduper.BuildConfig;
import com.zasa.superduper.ContactUsActivity;
import com.zasa.superduper.CourierService.CourierServiceFragment;
import com.zasa.superduper.DonationVerification.DonationVerificationActivity;
import com.zasa.superduper.FAQs.FAQsActivity;
import com.zasa.superduper.Home.WebViewActivity;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.Profile.ProfileActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.TechClub.TechClubFragment;
import com.zasa.superduper.WasteMaterialRequestSubmit.SaveEarthActivity;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.Utils.SharedPrefManager;


public class MoreFragment extends Fragment implements View.OnClickListener {

    public MoreFragment() {
        // Required empty public constructor
    }

    Context context;
    SharedPrefManager sharedPrefManager;
    SharedPreferences sharedPreferences;

    View more_logout_btn, more_profile_btn, more_back_btn, more_scanAndGian_btn,
            more_Draw_btn, more_privacy_policy_btn, more_Terms_Conditions_btn, more_ContactUs_btn,more_FAQs_btn, more_TechClub_btn,more_LoyaltyBunchFamily_btn,more_CourierService_btn;
    TextView tv_versionName;
    String Privacy_Policy_Url,Terms_Conditions_Url,Contact_Number,Support_Email,Website_Url;
    // ImageView
    int int_Member_Type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        context = requireActivity();
        sharedPrefManager = new SharedPrefManager(context);

        AppversionListModel appversionListModel = sharedPrefManager.getAppversionListModel();
        if (appversionListModel != null) {
            Privacy_Policy_Url= appversionListModel.getPrivacy_Policy_Url();
            Contact_Number= appversionListModel.getContact_Number();
            Support_Email= appversionListModel.getSupport_Email();
            Website_Url= appversionListModel.getWebsite_Url();
            Terms_Conditions_Url= appversionListModel.getTerms_Conditions_Url();
        }


        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            int_Member_Type = memberDetailsApiModel.getMember_Type();

        } else {
            //login shared pref
            sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            int_Member_Type = sharedPreferences.getInt("Member_Type", 0);

        }



        more_logout_btn = view.findViewById(R.id.more_logout_btn);
        more_logout_btn.setOnClickListener(this);

        more_profile_btn = view.findViewById(R.id.more_profile_btn);
        more_profile_btn.setOnClickListener(this);

        more_scanAndGian_btn = view.findViewById(R.id.more_scanAndGian_btn);
        more_scanAndGian_btn.setOnClickListener(this);

        more_Draw_btn = view.findViewById(R.id.more_Draw_btn);
        more_Draw_btn.setOnClickListener(this);

        more_back_btn = view.findViewById(R.id.more_back_btn);
        more_back_btn.setOnClickListener(this);

        more_privacy_policy_btn = view.findViewById(R.id.more_privacy_policy_btn);
        more_privacy_policy_btn.setOnClickListener(this);

        more_Terms_Conditions_btn = view.findViewById(R.id.more_Terms_Conditions_btn);
        more_Terms_Conditions_btn.setOnClickListener(this);

        more_ContactUs_btn = view.findViewById(R.id.more_ContactUs_btn);
        more_ContactUs_btn.setOnClickListener(this);

        more_FAQs_btn = view.findViewById(R.id.more_FAQs_btn);
        more_FAQs_btn.setOnClickListener(this);

        more_TechClub_btn = view.findViewById(R.id.more_TechClub_btn);
        more_TechClub_btn.setOnClickListener(this);

        more_LoyaltyBunchFamily_btn = view.findViewById(R.id.more_LoyaltyBunchFamily_btn);
        more_LoyaltyBunchFamily_btn.setOnClickListener(this);

        more_CourierService_btn = view.findViewById(R.id.more_CourierService_btn);
        more_CourierService_btn.setOnClickListener(this);

        /*more_shareApp_btn = view.findViewById(R.id.more_shareApp_btn);
        more_shareApp_btn.setOnClickListener(this);*/


        tv_versionName = view.findViewById(R.id.tv_versionName);
        ///////set app version/////
        try {
            int versionCode = BuildConfig.VERSION_CODE;
            String versionName = BuildConfig.VERSION_NAME;
            tv_versionName.setText(versionCode + " (" + versionName + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }



        if (int_Member_Type == 3) {
            more_LoyaltyBunchFamily_btn.setVisibility(View.VISIBLE);
        }else {
            more_LoyaltyBunchFamily_btn.setVisibility(View.GONE);
        }


        return view;
    }



    private void logout() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Alert!");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you want to logout?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPrefManager.logout();
                startActivity(new Intent(context, LoginActivity.class));

                requireActivity().finishAffinity();


            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.more_logout_btn:
                logout();
                break;

            case R.id.more_back_btn:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                requireActivity().finishAffinity();
                break;

            case R.id.more_profile_btn:
                startActivity(new Intent(context, ProfileActivity.class));

                break;

            case R.id.more_scanAndGian_btn:
                startActivity( new Intent(context, SaveEarthActivity.class));
                break;
                /*case R.id.more_shareApp_btn:
                    Constant.shareApp(context);
                    //shareApp();
                break; */
                case R.id.more_TechClub_btn:
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout, new TechClubFragment(), "Tag")
                            //.addToBackStack("HomeFragment")
                            .addToBackStack(null)
                            .commit();
                break;

                case R.id.more_CourierService_btn:
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout, new CourierServiceFragment(), "Tag")
                            //.addToBackStack("HomeFragment")
                            .addToBackStack(null)
                            .commit();
                break;

                case R.id.more_LoyaltyBunchFamily_btn:
                startActivity( new Intent(context, DonationVerificationActivity.class));
                break;

                case R.id.more_FAQs_btn:
                startActivity( new Intent(context, FAQsActivity.class));
                break;
            case R.id.more_privacy_policy_btn:
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("URL",Privacy_Policy_Url);
                startActivity(intent);
                break;
            case R.id.more_Terms_Conditions_btn:
                Intent intent1 = new Intent(context, WebViewActivity.class);
                intent1.putExtra("URL",Terms_Conditions_Url);
                startActivity(intent1);
                break;
            case R.id.more_ContactUs_btn:
                startActivity(new Intent(context, ContactUsActivity.class));
                break;

            case R.id.more_Draw_btn:
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, new DrawFragment(), "Tag")
                        //.addToBackStack("HomeFragment")
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
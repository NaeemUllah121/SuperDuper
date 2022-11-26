package com.zasa.superduper.TechClub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;
import com.zasa.superduper.Splash.AppversionListModel;
import com.zasa.superduper.TechClub.Model.TechMemTypeListApi;
import com.zasa.superduper.TechClub.Model.TechMemTypeListModel;
import com.zasa.superduper.TechClub.Model.TechMembersListApi;
import com.zasa.superduper.TechClub.Model.TechMembersListModel;
import com.zasa.superduper.TechClub.Model.TechTeamListApi;
import com.zasa.superduper.TechClub.Model.TechTeamListModel;
import com.zasa.superduper.TechClub.Model.TechTopMembersListApi;
import com.zasa.superduper.TechClub.Model.TechTopMembersListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TechClubFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = "ttt";
    private int i = 0;

    public TechClubFragment() {
        // Required empty public constructor
    }


    String youtubeVideoId, videoTitle, videoDescription, videoDate;
    YouTubePlayerView youTubePlayerView;

    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;
    Context context;
    LinearLayoutManager linearLayoutManager;
    ImageView btn_play;
    NestedScrollView mScrollView;

    boolean isScroll = true;
    View pview;


    ImageView btn_back;
    View lay_elearning, lay_events, lay_Appointments, seeAllTechTeamBtn, seeAllTopMemBtn, seeAllMemBtn;

    ArrayList<TechMemTypeListModel> techMemTypeListModelArrayList = new ArrayList<>();

    RecyclerView techTeamRcv;
    TechTeamRcvAdapter techTeamRcvAdapter;
    ArrayList<TechTeamListModel> techTeamListModelArrayList = new ArrayList<>();

    RecyclerView techTopMembersRcv;
    TechTopMembersRcvAdapter techTopMembersRcvAdapter;
    ArrayList<TechTopMembersListModel> techTopMembersListModelArrayList = new ArrayList<>();

    RecyclerView techMembersRcv;
    TechMembersRcvAdapter techMembersRcvAdapter;
    ArrayList<TechMembersListModel> techMembersListModelArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tech_club, container, false);

        context = requireActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);


        AppversionListModel appversionListModel = sharedPrefManager.getAppversionListModel();
        if (appversionListModel != null) {
            String Tech_PromoLink = appversionListModel.getTech_Promo();
            String[] separated = Tech_PromoLink.split("=");
            // separated[0];
            youtubeVideoId = separated[1];
            Toast.makeText(context, "n", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "o", Toast.LENGTH_SHORT).show();
            youtubeVideoId = "ncq4cGETHfU";
        }

        pview = view.findViewById(R.id.view);
        btn_back = view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        lay_elearning = view.findViewById(R.id.lay_elearning);
        lay_elearning.setOnClickListener(this);

        lay_events = view.findViewById(R.id.lay_events);
        lay_events.setOnClickListener(this);

        lay_Appointments = view.findViewById(R.id.lay_Appointments);
        lay_Appointments.setOnClickListener(this);

        seeAllTechTeamBtn = view.findViewById(R.id.seeAllTechTeamBtn);
        seeAllTechTeamBtn.setOnClickListener(this);

        seeAllTopMemBtn = view.findViewById(R.id.seeAllTopMemBtn);
        seeAllTopMemBtn.setOnClickListener(this);

        seeAllMemBtn = view.findViewById(R.id.seeAllMemBtn);
        seeAllMemBtn.setOnClickListener(this);


        //tech Team Rcv
        techTeamRcv = view.findViewById(R.id.techTeamRcv);
        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        techTeamRcv.setLayoutManager(linearLayoutManager);


        //tech Top Members Rcv
        techTopMembersRcv = view.findViewById(R.id.techTopMembersRcv);
        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        techTopMembersRcv.setLayoutManager(linearLayoutManager);


        //tech Members Rcv
        techMembersRcv = view.findViewById(R.id.techMembersRcv);
        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        techMembersRcv.setLayoutManager(linearLayoutManager);


        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);


        mYouTubePlayer(view);
        mScrollView(view);

        mTechMemTypeListApi();
        mTechTeamListApi();

        mTechTopMembersListApi();
        mTechMembersListApi();


        return view;
    }

    private void mScrollView(View view) {

        mScrollView = view.findViewById(R.id.mScrollView);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                int scrolllX = v.getScrollX();
                int scrolllY = v.getScrollY(); // For ScrollView
                // Log.d("ScrollView", "scrollX_" + scrollX + "_scrollY_" + scrollY + "_oldScrollX_" + oldScrollX + "_oldScrollY_" + oldScrollY);
                //Do something


                Rect scrollBounds = new Rect();
                mScrollView.getHitRect(scrollBounds);

                if (youTubePlayerView.getLocalVisibleRect(scrollBounds)) {
                    if (!youTubePlayerView.getLocalVisibleRect(scrollBounds) || scrollBounds.height() < youTubePlayerView.getHeight()) {
                        Log.i(TAG, "VIEW APPEAR PARTIALLY");

                    } else {
                        mAdjustAudio(10);
                        Log.i(TAG, "VIEW APPEAR FULLY!!!");
                    }
                } else {
                    mAdjustAudio(0);
                    Log.i(TAG, "No VIEW");
                }




            /*    if (scrollY > 1 && i == 0) {
                    ++i;
                    mYouTubePlayer(view, "pause");
                    //Snackbar.make(pview, "if " + scrollY + " " + isScroll, Snackbar.LENGTH_LONG).show();
                    // Toast.makeText(context, "scrollY " + scrollY, Toast.LENGTH_SHORT).show();
                }
                if (scrollY == 0) {
                    i = 0;
                    mYouTubePlayer(view, "play");
                    //Snackbar.make(pview, "else " + scrollY + " " + isScroll, Snackbar.LENGTH_LONG).show();
                }*/
            }
        });

    }

    private void mAdjustAudio(int vol) {
        AudioManager audioManager;
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0);
    }

    private void mYouTubePlayer(View view) {

        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        //youTubePlayerView.enterFullScreen();


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(youtubeVideoId, 0);

             /*   if (a.equals("pause")) {
                    Toast.makeText(context, "pause call", Toast.LENGTH_SHORT).show();
                    youTubePlayer.pause();
                }
                if (a.equals("play")) {
                    youTubePlayer.play();
                    Toast.makeText(context, "play call", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

    }

    private void mTechMemTypeListApi() {

        progressDialog.show();
        Call<TechMemTypeListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mTechMemTypeListApi();

        call.enqueue(new Callback<TechMemTypeListApi>() {
            @Override
            public void onResponse(@NonNull Call<TechMemTypeListApi> call, @NonNull Response<TechMemTypeListApi> response) {

                TechMemTypeListApi techMemTypeListApi = response.body();

                if (response.isSuccessful()) {
                    if (techMemTypeListApi != null) {
                        if (techMemTypeListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            techMemTypeListModelArrayList = techMemTypeListApi.getTech_MTypelist();

                            //Toast.makeText(context, "" + techMemTypeListApi.getMessage(), Toast.LENGTH_SHORT).show();
                            sharedPrefManager.saveTechMemberTypeList(techMemTypeListModelArrayList);


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + techMemTypeListApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TechMemTypeListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mTechTeamListApi() {

        progressDialog.show();
        Call<TechTeamListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mTechTeamListApi();

        call.enqueue(new Callback<TechTeamListApi>() {
            @Override
            public void onResponse(@NonNull Call<TechTeamListApi> call, @NonNull Response<TechTeamListApi> response) {

                TechTeamListApi techTeamListApi = response.body();

                if (response.isSuccessful()) {
                    if (techTeamListApi != null) {
                        if (techTeamListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            techTeamListModelArrayList = techTeamListApi.getMemberList();

                            techTeamRcvAdapter = new TechTeamRcvAdapter(techTeamListModelArrayList, context);
                            techTeamRcv.setAdapter(techTeamRcvAdapter);
                            techTeamRcvAdapter.notifyItemInserted(techTeamListModelArrayList.size());
                            //techTeamRcvAdapter.notifyDataSetChanged();
                            sharedPrefManager.saveTechTeamList(techTeamListModelArrayList);

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + techTeamListApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TechTeamListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mTechTopMembersListApi() {

        progressDialog.show();
        Call<TechTopMembersListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mTechTopMembersListApi();

        call.enqueue(new Callback<TechTopMembersListApi>() {
            @Override
            public void onResponse(@NonNull Call<TechTopMembersListApi> call, @NonNull Response<TechTopMembersListApi> response) {

                TechTopMembersListApi techTopMembersListApi = response.body();

                if (response.isSuccessful()) {
                    if (techTopMembersListApi != null) {
                        if (techTopMembersListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            techTopMembersListModelArrayList = techTopMembersListApi.getMemberList();
                            techTopMembersRcvAdapter = new TechTopMembersRcvAdapter(techTopMembersListModelArrayList, context);
                            techTopMembersRcv.setAdapter(techTopMembersRcvAdapter);
                            techTopMembersRcvAdapter.notifyItemInserted(techTopMembersListModelArrayList.size());

                            sharedPrefManager.saveTechTopMemberList(techTopMembersListModelArrayList);

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + techTopMembersListApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TechTopMembersListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mTechMembersListApi() {

        progressDialog.show();
        Call<TechMembersListApi> call = RetrofitInstance
                .getInstance()
                .getApiInterface()
                .mTechMembersListApi();

        call.enqueue(new Callback<TechMembersListApi>() {
            @Override
            public void onResponse(@NonNull Call<TechMembersListApi> call, @NonNull Response<TechMembersListApi> response) {

                TechMembersListApi techMembersListApi = response.body();

                if (response.isSuccessful()) {
                    if (techMembersListApi != null) {
                        if (techMembersListApi.getStatus() == 1) {

                            progressDialog.dismiss();

                            techMembersListModelArrayList = techMembersListApi.getMemberList();
                            techMembersRcvAdapter = new TechMembersRcvAdapter(techMembersListModelArrayList, context);
                            techMembersRcv.setAdapter(techMembersRcvAdapter);
                            techMembersRcvAdapter.notifyItemInserted(techMembersListModelArrayList.size());

                            sharedPrefManager.saveTechMemberList(techMembersListModelArrayList);


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + techMembersListApi.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TechMembersListApi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.btn_back:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                requireActivity().finishAffinity();
                break;

                /* case R.id.seeAllTechTeamBtn:
                case R.id.seeAllTopMemBtn:
                    case R.id.seeAllMemBtn:
                startActivity(new Intent(context, SeeAllMembersActivity.class));
                break;*/


            case R.id.seeAllTechTeamBtn:
                Intent intent = new Intent(context, SeeAllMembersActivity.class);
                intent.putExtra("List", "techTeam");
                startActivity(intent);
                break;
            case R.id.seeAllTopMemBtn:
                Intent intent1 = new Intent(context, SeeAllMembersActivity.class);
                intent1.putExtra("List", "techTopMembers");
                startActivity(intent1);
                break;
            case R.id.seeAllMemBtn:
                Intent intent2 = new Intent(context, SeeAllMembersActivity.class);
                intent2.putExtra("List", "techMembers");
                startActivity(intent2);
                break;

            case R.id.lay_events:
                startActivity(new Intent(context, TechEventsActivity.class));
                break;

            case R.id.lay_Appointments:
                startActivity(new Intent(context, TechAppointmentActivity.class));
                //Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                break;

            case R.id.lay_elearning:
                startActivity(new Intent(context, ELearningActivity.class));

              /*  requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, new eLearnFragment(), "Tag")
                        //.addToBackStack("HomeFragment")
                        .addToBackStack(null)
                        .commit();*/
                break;
        }


    }
}
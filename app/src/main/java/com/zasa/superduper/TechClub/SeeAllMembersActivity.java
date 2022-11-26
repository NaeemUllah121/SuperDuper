package com.zasa.superduper.TechClub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zasa.superduper.R;
import com.zasa.superduper.TechClub.Model.TechMembersListModel;
import com.zasa.superduper.TechClub.Model.TechTeamListModel;
import com.zasa.superduper.TechClub.Model.TechTopMembersListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

public class SeeAllMembersActivity extends AppCompatActivity {

    Context context;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;

    RecyclerView seeAllRecycler;

    ArrayList<TechTeamListModel> techTeamListModelArrayList = new ArrayList<>();
    ArrayList<TechTopMembersListModel> techTopMembersListModelArrayList = new ArrayList<>();
    ArrayList<TechMembersListModel> techMembersListModelArrayList = new ArrayList<>();

    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_members);


        context = SeeAllMembersActivity.this;

        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);

        String showList = getIntent().getStringExtra("List");

       // Toast.makeText(context, ""+showList, Toast.LENGTH_SHORT).show();


        seeAllRecycler = findViewById(R.id.seeAll_members);
        gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        seeAllRecycler.setLayoutManager(gridLayoutManager);

        if (showList.equals("techTeam")) {

            techTeamListModelArrayList = sharedPrefManager.loadTechTeamListModel();

            TechTeamRcvAdapter techTeamRcvAdapter = new TechTeamRcvAdapter(techTeamListModelArrayList, context);
            seeAllRecycler.setAdapter(techTeamRcvAdapter);
            techTeamRcvAdapter.notifyItemInserted(techTeamListModelArrayList.size());

        } else if (showList.equals("techTopMembers")) {

            techTopMembersListModelArrayList = sharedPrefManager.loadTechTopMembersListModel();
            TechTopMembersRcvAdapter techTopMembersRcvAdapter = new TechTopMembersRcvAdapter(techTopMembersListModelArrayList, context);
            seeAllRecycler.setAdapter(techTopMembersRcvAdapter);
            techTopMembersRcvAdapter.notifyItemInserted(techTopMembersListModelArrayList.size());

        } else {
            techMembersListModelArrayList = sharedPrefManager.loadTechMembersListModel();
            SeeAllMembersRcvAdapter seeAllMembersRcvAdapter = new SeeAllMembersRcvAdapter(techMembersListModelArrayList, context);
            seeAllRecycler.setAdapter(seeAllMembersRcvAdapter);
            seeAllMembersRcvAdapter.notifyItemInserted(techMembersListModelArrayList.size());
        }


    }

    public void btn_back(View view) {
        finish();
    }
}
package com.zasa.superduper.Redeem;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zasa.superduper.CompanyListCategoryWiseS.Model;
import com.zasa.superduper.R;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;


public class CoinsFragment extends Fragment {

    public CoinsFragment() {
        // Required empty public constructor
    }

    ProgressDialog progressDialog;
    Context context;
    SharedPrefManager sharedPrefManager;


    RecyclerView rcv_coinsHistory;
    LinearLayoutManager linearLayoutManager;
    CoinsHistoryAdapter coinsHistoryAdapter;
    ArrayList<Model> models = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_coins, container, false);



        context = requireActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        sharedPrefManager = new SharedPrefManager(context);



        rcv_coinsHistory = view.findViewById(R.id.rcv_coinsHistory);
        rcv_coinsHistory.setNestedScrollingEnabled(true);

        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_coinsHistory.setLayoutManager(linearLayoutManager);

        coinsHistoryAdapter = new CoinsHistoryAdapter(models, context);
        rcv_coinsHistory.setAdapter(coinsHistoryAdapter);



        models.add(new Model("1000","0","12-2-22","124435"));
        models.add(new Model("2000","0","12-2-22","124435"));
        models.add(new Model("0","500","12-2-22","124435"));
        models.add(new Model("100","0","12-2-22","124435"));
        models.add(new Model("0","110","12-2-22","124435"));
        models.add(new Model("0","500","12-2-22","124435"));
        models.add(new Model("1000","0","12-2-22","124435"));
        models.add(new Model("0","100","12-2-22","124435"));

        coinsHistoryAdapter.notifyItemInserted(models.size());


        return view;
    }
}
package com.zasa.superduper.eLearn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.R;

import java.util.ArrayList;


public class eLearnFragment extends Fragment {

    public eLearnFragment() {
        // Required empty public constructor
    }



    RecyclerView courseCategoryRecyclerView;
    LinearLayoutManager linearLayoutManager;
    CourseCategoryRCVAdapter courseCategoryRCVAdapter;
    ArrayList<CourseCategoryModel> courseCategoryModelArrayList;

    RecyclerView myCourseRecyclerView;
    MyCourseRCVAdapter myCourseRCVAdapter;
    ArrayList<MyCourseModel> myCourseModelArrayList;

    View btn_back_eLearn;
    Button btn_continueShopping;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_e_learn, container, false);


        btn_back_eLearn = view.findViewById(R.id.btn_back_eLearn);
        btn_back_eLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                if (getActivity() != null) {
                    getActivity().finishAffinity();
                }
            }
        });
        btn_continueShopping = view.findViewById(R.id.btn_ContinueShopping);
        btn_continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                if (getActivity() != null) {
                    getActivity().finishAffinity();
                }

            }
        });


        //course category RCV
        courseCategoryRecyclerView = view.findViewById(R.id.chineseLangRcv);
        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        courseCategoryRecyclerView.setLayoutManager(linearLayoutManager);


        courseCategoryModelArrayList = new ArrayList<>();
        courseCategoryModelArrayList.add(new CourseCategoryModel("https://tinyurl.com/df6knh4e", "Languages"));
        courseCategoryModelArrayList.add(new CourseCategoryModel("https://tinyurl.com/4tthhyht", "Technical"));
        courseCategoryModelArrayList.add(new CourseCategoryModel("https://tinyurl.com/4scvn6us", "Non-Technical"));

        courseCategoryRCVAdapter = new CourseCategoryRCVAdapter(courseCategoryModelArrayList, getActivity());
        courseCategoryRecyclerView.setAdapter(courseCategoryRCVAdapter);
        courseCategoryRCVAdapter.notifyDataSetChanged();


        //my course RCV
        myCourseRecyclerView = view.findViewById(R.id.myCoursesRcv);
        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCourseRecyclerView.setLayoutManager(linearLayoutManager);


        myCourseModelArrayList = new ArrayList<>();
        myCourseModelArrayList.add(new MyCourseModel("https://tinyurl.com/3ttmype4","Typing Course","It is a long established fact that a reader will be distracted by the readable content","30 Days"));
        myCourseModelArrayList.add(new MyCourseModel("https://tinyurl.com/ytv5xbnb","Chinese Language","It is a long established fact that a reader will be distracted by the readable content","60 Days"));
        myCourseModelArrayList.add(new MyCourseModel("https://tinyurl.com/2b3sr8p5","Web Course","It is a long established fact that a reader will be distracted by the readable content","90 Days"));
        myCourseModelArrayList.add(new MyCourseModel("https://tinyurl.com/bcfnjk87","Graphics Designing","It is a long established fact that a reader will be distracted by the readable content","30 Days"));

        myCourseRCVAdapter = new MyCourseRCVAdapter(myCourseModelArrayList, getActivity());
        myCourseRecyclerView.setAdapter(myCourseRCVAdapter);
        myCourseRecyclerView.setNestedScrollingEnabled(true);
        myCourseRCVAdapter.notifyDataSetChanged();

        return view;
    }
}
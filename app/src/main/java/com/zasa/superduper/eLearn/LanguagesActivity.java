package com.zasa.superduper.eLearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.zasa.superduper.R;

import java.util.ArrayList;

public class LanguagesActivity extends AppCompatActivity {

    //chinese language RCV
    RecyclerView chineseLanguagesRecyclerView;
    ChineseLanguageRCVAdapter chineseLanguageRCVAdapter;
    ArrayList<ChineseLanguagesModel> chineseLanguagesModelArrayList;

    LinearLayoutManager linearLayoutManager;

    //english language RCV
    RecyclerView engLanguagesRecyclerView;
    EnglishLangRCVAdapter englishLangRCVAdapter;
    ArrayList<EnglishLangModel> englishLangModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);


        //chinese language RCV
        chineseLanguagesRecyclerView =findViewById(R.id.chineseLangRcv);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        chineseLanguagesRecyclerView.setLayoutManager(linearLayoutManager);


        chineseLanguagesModelArrayList = new ArrayList<>();
        chineseLanguagesModelArrayList.add(new ChineseLanguagesModel(R.drawable.basic, "Basic Chinese"));
        chineseLanguagesModelArrayList.add(new ChineseLanguagesModel(R.drawable.advance, "Advance Chinese"));

        chineseLanguageRCVAdapter = new ChineseLanguageRCVAdapter(chineseLanguagesModelArrayList, this);
        chineseLanguagesRecyclerView.setAdapter(chineseLanguageRCVAdapter);
        chineseLanguageRCVAdapter.notifyDataSetChanged();

        //english language RCV
        engLanguagesRecyclerView =findViewById(R.id.englishLangRcv);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        engLanguagesRecyclerView.setLayoutManager(linearLayoutManager);


        englishLangModelArrayList = new ArrayList<>();
        englishLangModelArrayList.add(new EnglishLangModel("Spoken English", "https://tinyurl.com/2zn9chce"));//https://tinyurl.com/2zn9chce
        englishLangModelArrayList.add(new EnglishLangModel("IELTS", "https://tinyurl.com/t4dvzk5r"));
        englishLangModelArrayList.add(new EnglishLangModel("PTE", "https://tinyurl.com/jf5njxc3"));

        englishLangRCVAdapter = new EnglishLangRCVAdapter(englishLangModelArrayList, this);
        engLanguagesRecyclerView.setAdapter(englishLangRCVAdapter);
        englishLangRCVAdapter.notifyDataSetChanged();
    }

    public void btn_back(View view) {
        finish();
    }
}
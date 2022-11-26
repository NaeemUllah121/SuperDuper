package com.zasa.superduper.eLearn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class EnglishLangRCVAdapter extends RecyclerView.Adapter<EnglishLangRCVAdapter.ViewHolder> {
    ArrayList<EnglishLangModel> englishLangModelArrayList;
    Context context;

    public EnglishLangRCVAdapter(ArrayList<EnglishLangModel> englishLangModelArrayList, Context context) {
        this.englishLangModelArrayList = englishLangModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EnglishLangRCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_english_course_item, parent, false);
        return new EnglishLangRCVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishLangRCVAdapter.ViewHolder holder, int position) {
        String ImageUrl = englishLangModelArrayList.get(position).getImg();
        String title = englishLangModelArrayList.get(position).getTitle();

        Glide.with(context).load(ImageUrl).into(holder.iv_courseImage);

        holder.tv_courseTitle.setText(title);

        holder.iv_courseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                context.startActivity(new Intent(context, LanguageDetailsActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return englishLangModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_courseTitle;
        ImageView iv_courseImage;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            iv_courseImage = itemView.findViewById(R.id.eng_course_Image);
            tv_courseTitle = itemView.findViewById(R.id.eng_course_name);
        }
    }
}
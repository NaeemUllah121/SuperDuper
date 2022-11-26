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

public class ChineseLanguageRCVAdapter extends RecyclerView.Adapter<ChineseLanguageRCVAdapter.ViewHolder> {
    ArrayList<ChineseLanguagesModel> chineseLanguagesModelArrayList;
    Context context;

    public ChineseLanguageRCVAdapter(ArrayList<ChineseLanguagesModel> chineseLanguagesModelArrayList, Context context) {
        this.chineseLanguagesModelArrayList = chineseLanguagesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChineseLanguageRCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_chinese_course_item, parent, false);
        return new ChineseLanguageRCVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChineseLanguageRCVAdapter.ViewHolder holder, int position) {

        int ImageUrl = chineseLanguagesModelArrayList.get(position).getImg();
        String title = chineseLanguagesModelArrayList.get(position).getTitle();

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
        return chineseLanguagesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_courseTitle;
        ImageView iv_courseImage;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            iv_courseImage = itemView.findViewById(R.id.chinese_course_Image);
            tv_courseTitle = itemView.findViewById(R.id.chinese_course_name);
        }
    }
}

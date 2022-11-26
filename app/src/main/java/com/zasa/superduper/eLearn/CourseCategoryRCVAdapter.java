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

public class CourseCategoryRCVAdapter extends RecyclerView.Adapter<CourseCategoryRCVAdapter.ViewHolder> {
    ArrayList<CourseCategoryModel> courseCategoryModelArrayList;
    Context context;

    public CourseCategoryRCVAdapter(ArrayList<CourseCategoryModel> courseCategoryModelArrayList, Context context) {
        this.courseCategoryModelArrayList = courseCategoryModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseCategoryRCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_course_category_item, parent, false);
        return new CourseCategoryRCVAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CourseCategoryRCVAdapter.ViewHolder holder, int position) {
        String Title = courseCategoryModelArrayList.get(position).getName();
        String Imageurl = courseCategoryModelArrayList.get(position).getImage();

        Glide.with(context).load(Imageurl).into(holder.iv_cImage);
         holder.tv_cTitle.setText(Title);

        holder.courseCategoryLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, LanguagesActivity.class);
              //  intent.putExtra("homeTopDeals", Title);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return courseCategoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View courseCategoryLay;
        TextView tv_cTitle;
        ImageView iv_cImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cImage = itemView.findViewById(R.id.course_Image);
            tv_cTitle = itemView.findViewById(R.id.course_name);
            courseCategoryLay = itemView.findViewById(R.id.course_category);




        }
    }
}

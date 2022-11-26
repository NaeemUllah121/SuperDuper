package com.zasa.superduper.eLearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class MyCourseRCVAdapter extends RecyclerView.Adapter<MyCourseRCVAdapter.ViewHolder> {
    ArrayList<MyCourseModel> myCourseModelArrayList;
    Context context;

    public MyCourseRCVAdapter(ArrayList<MyCourseModel> myCourseModelArrayList, Context context) {
        this.myCourseModelArrayList = myCourseModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCourseRCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_my_courses_item, parent, false);
        return new MyCourseRCVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCourseRCVAdapter.ViewHolder holder, int position) {
        String Title = myCourseModelArrayList.get(position).getCourseTitle();
        String description = myCourseModelArrayList.get(position).getCourseDiscript();
        String duration = myCourseModelArrayList.get(position).getCourseDuration();
        String ImageUrl = myCourseModelArrayList.get(position).getCourseImg();

        Glide.with(context).load(ImageUrl).into(holder.iv_courseImage);


        holder.tv_courseTitle.setText(Title);
        holder.tv_courseDis.setText(description);
        holder.tv_courseDuration.setText(duration);



        holder.btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   Intent intent = new Intent(context, HomeFragment.class);
                intent.putExtra("homeTopDeals", Title);
                context.startActivity(intent);
*/
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myCourseModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_courseTitle, tv_courseDis, tv_courseDuration, btn_continue;
        ImageView iv_courseImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_courseTitle = itemView.findViewById(R.id.courseTitle);
            tv_courseDis = itemView.findViewById(R.id.courseDiscription);
            tv_courseDuration = itemView.findViewById(R.id.courseDuration);
            iv_courseImage = itemView.findViewById(R.id.my_course_Image);
            btn_continue = itemView.findViewById(R.id.courseContiBtn);
        }
    }
}

package com.zasa.superduper.eLearn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.zasa.superduper.R;
import com.zasa.superduper.eLearn.YTModels.TypeThumbnail;
import com.zasa.superduper.eLearn.YTModels.Video;

import java.util.ArrayList;

public class YTAdapter extends RecyclerView.Adapter<YTAdapter.ViewHolder> {
    ArrayList<Video> arrayListVideos;
    Context context;

    public YTAdapter(ArrayList<Video> arrayListVideos, Context context) {
        this.arrayListVideos = arrayListVideos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Video video = arrayListVideos.get(position);
        if (video != null) {
            holder.tv_title.setText(position+1 + ". " + video.snippet.title);
            holder.tv_description.setText(video.snippet.description);
            // holder.tv_date.setText(video.snippet.publishedAt);


            String[] datee = video.snippet.publishedAt.split("T");
            String newDATE = datee[0].trim();
            String newTIME = datee[1].trim();

            holder.tv_date.setText(newDATE);


 /*           String date = video.snippet.publishedAt.replace("T", "  ");//replace  letter with space
            String newDate = date.replace("Z", "");//replace letters with space
            holder.tv_date.setText(newDate);*/


            if (video.snippet.thumbnails != null) {
                TypeThumbnail t = video.snippet.thumbnails.high;
                if (t == null) t = video.snippet.thumbnails.medium;
                if (t == null) t = video.snippet.thumbnails.standard;

                Glide.with(context).load(t.url).into(holder.iv_Thumbnail);

                //Toast.makeText(context, ""+holder.iv_Thumbnail, Toast.LENGTH_SHORT).show();

            }
        }


        holder.lay_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Video video1 = arrayListVideos.get(holder.getAdapterPosition());

                Intent intent = new Intent(context, YTPlayVideoActivity.class);
                String videoId = video.contentDetails.videoId;

                intent.putExtra("videoId", videoId);
                intent.putExtra("videoTitle", video.snippet.title);
                intent.putExtra("videoDescription", video.snippet.description);
                intent.putExtra("videoDate", video.snippet.publishedAt);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayListVideos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lay_item;
        TextView tv_title, tv_description, tv_date, tv_duration;
        ImageView iv_Thumbnail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.video_title);
            tv_description = itemView.findViewById(R.id.courseDescription);
            tv_date = itemView.findViewById(R.id.date);
            tv_duration = itemView.findViewById(R.id.courseDuration);
            iv_Thumbnail = itemView.findViewById(R.id.iv_item_cover);
            lay_item = itemView.findViewById(R.id.lay_item);

        }
    }
}

package com.zasa.superduper.TechClub;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zasa.superduper.R;
import com.zasa.superduper.TechClub.Model.TechMemTypeListModel;
import com.zasa.superduper.TechClub.Model.TechTeamListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TechTeamRcvAdapter extends RecyclerView.Adapter<TechTeamRcvAdapter.ViewHolder> {

    ArrayList<TechMemTypeListModel> techMemTypeListModelArrayList;

    ArrayList<TechTeamListModel> techTeamListModelArrayList;
    Context context;

    public TechTeamRcvAdapter(ArrayList<TechTeamListModel> techTeamListModelArrayList, Context context) {
        this.techTeamListModelArrayList = techTeamListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TechTeamRcvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_tech_team_item, parent, false);
        return new TechTeamRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechTeamRcvAdapter.ViewHolder holder, int position) {

        String Member_Unique = techTeamListModelArrayList.get(position).getMember_Unique();
        String Member_FName = techTeamListModelArrayList.get(position).getMember_FName();
        String Member_LName = techTeamListModelArrayList.get(position).getMember_LName();
        String Tech_Tagline = techTeamListModelArrayList.get(position).getTech_Tagline();
        int Tech_Member_Type = techTeamListModelArrayList.get(position).getTech_Member_Type();
        long Tech_Points = techTeamListModelArrayList.get(position).getTech_Points();

        String imgUrl = "http://apis.loyaltybunch.com/Member_Images/" + Member_Unique + ".jpg";


        if (TextUtils.isEmpty(Member_Unique)) {

            holder.circleImageView.setImageResource(R.drawable.noimg);

        } else {
            Glide.with(context)
                    .load(imgUrl)
                    .fitCenter().placeholder(R.drawable.noimg)
                    .into(holder.circleImageView);
        }

        holder.tv_TT_Name.setText(Member_FName + " " + Member_LName);
        holder.tv_TT_Tagline.setText(Tech_Tagline);
        holder.tv_TT_Points.setText(Tech_Points + "");


        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        techMemTypeListModelArrayList = sharedPrefManager.loadTechMemTypeListModel();

        for (int i = 0; i < techMemTypeListModelArrayList.size(); i++) {
            int Tech_Member_Type1 = techMemTypeListModelArrayList.get(i).getTech_Member_Type1();

            if (Tech_Member_Type1 == Tech_Member_Type) {

                String Tech_Member_Type_Title = techMemTypeListModelArrayList.get(i).getTech_Member_Type_Title();
                holder.tv_TT_MemType.setText(Tech_Member_Type_Title);

            }
        }


    }

    @Override
    public int getItemCount() {
        return techTeamListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView tv_TT_Name, tv_TT_MemType, tv_TT_Tagline, tv_TT_Points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.TT_UserImg);
            tv_TT_Name = itemView.findViewById(R.id.tv_TT_Name);
            tv_TT_MemType = itemView.findViewById(R.id.tv_TT_MemType);
            tv_TT_Tagline = itemView.findViewById(R.id.tv_TT_Tagline);
            tv_TT_Points = itemView.findViewById(R.id.tv_TT_Points);

        }
    }
}

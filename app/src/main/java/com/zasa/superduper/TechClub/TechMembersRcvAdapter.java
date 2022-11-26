package com.zasa.superduper.TechClub;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zasa.superduper.R;
import com.zasa.superduper.TechClub.Model.TechMemTypeListModel;
import com.zasa.superduper.TechClub.Model.TechMembersListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

public class TechMembersRcvAdapter extends RecyclerView.Adapter<TechMembersRcvAdapter.ViewHolder>{

    ArrayList<TechMemTypeListModel> techMemTypeListModelArrayList;

    ArrayList<TechMembersListModel> techMembersListModelArrayList;
    Context context;

    public TechMembersRcvAdapter(ArrayList<TechMembersListModel> techMembersListModelArrayList, Context context) {
        this.techMembersListModelArrayList = techMembersListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TechMembersRcvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rcv_tech_member_item, parent, false);
        return new TechMembersRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechMembersRcvAdapter.ViewHolder holder, int position) {


        String Member_Unique = techMembersListModelArrayList.get(position).getMember_Unique();
        String Member_FName = techMembersListModelArrayList.get(position).getMember_FName();
        String Member_LName = techMembersListModelArrayList.get(position).getMember_LName();
        String Tech_Tagline = techMembersListModelArrayList.get(position).getTech_Tagline();
        int Tech_Member_Type = techMembersListModelArrayList.get(position).getTech_Member_Type();
        long Tech_Points = techMembersListModelArrayList.get(position).getTech_Points();

        String imgUrl = "http://apis.loyaltybunch.com/Member_Images/" + Member_Unique + ".jpg";


        if (TextUtils.isEmpty(Member_Unique)) {

            holder.circleImageView.setImageResource(R.drawable.noimg);

        } else {
            Glide.with(context)
                    .load(imgUrl)
                    .fitCenter().placeholder(R.drawable.noimg)
                    .into(holder.circleImageView);
        }


        holder.tv_TM_Name.setText(Member_FName+" "+Member_LName);
        holder.tv_TM_Tagline.setText(Tech_Tagline);
        holder.tv_TM_Points.setText(Tech_Points+"");


        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        techMemTypeListModelArrayList = sharedPrefManager.loadTechMemTypeListModel();

        for (int i = 0; i < techMemTypeListModelArrayList.size(); i++) {
            int Tech_Member_Type1 = techMemTypeListModelArrayList.get(i).getTech_Member_Type1();

            if (Tech_Member_Type1 == Tech_Member_Type) {

                String Tech_Member_Type_Title = techMemTypeListModelArrayList.get(i).getTech_Member_Type_Title();
                holder.tv_TM_MemType.setText(Tech_Member_Type_Title);

            }
        }

    }

    @Override
    public int getItemCount() {
        return techMembersListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView circleImageView;
        TextView tv_TM_Name,tv_TM_MemType,tv_TM_Tagline,tv_TM_Points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.tv_TM_userImg);
            tv_TM_Name = itemView.findViewById(R.id.tv_TM_Name);
            tv_TM_Tagline = itemView.findViewById(R.id.tv_TM_Tagline);
            tv_TM_MemType = itemView.findViewById(R.id.tv_TM_MemType);
            tv_TM_Points = itemView.findViewById(R.id.tv_TM_Points);
        }
    }
}

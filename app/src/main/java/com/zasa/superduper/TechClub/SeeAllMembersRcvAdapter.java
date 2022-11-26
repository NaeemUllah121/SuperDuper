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
import com.zasa.superduper.TechClub.Model.TechMembersListModel;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SeeAllMembersRcvAdapter extends RecyclerView.Adapter<SeeAllMembersRcvAdapter.ViewHolder>{

    ArrayList<TechMemTypeListModel> techMemTypeListModelArrayList;

    ArrayList<TechMembersListModel> techMembersListModelArrayList;
    Context context;

    public SeeAllMembersRcvAdapter(ArrayList<TechMembersListModel> techMembersListModelArrayList, Context context) {
        this.techMembersListModelArrayList = techMembersListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_tech_see_all_member_item, parent, false);
        return new SeeAllMembersRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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


        holder.tv_TTM_Name.setText(Member_FName+" "+Member_LName);
        holder.tv_TTM_Tagline.setText(Tech_Tagline);
        holder.tv_TTM_Points.setText(Tech_Points+"");


        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        techMemTypeListModelArrayList = sharedPrefManager.loadTechMemTypeListModel();

        for (int i = 0; i < techMemTypeListModelArrayList.size(); i++) {
            int Tech_Member_Type1 = techMemTypeListModelArrayList.get(i).getTech_Member_Type1();

            if (Tech_Member_Type1 == Tech_Member_Type) {

                String Tech_Member_Type_Title = techMemTypeListModelArrayList.get(i).getTech_Member_Type_Title();
                holder.tv_TTM_MemType.setText(Tech_Member_Type_Title);

            }
        }


    }

    @Override
    public int getItemCount() {
        return techMembersListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView tv_TTM_Name,tv_TTM_MemType,tv_TTM_Tagline,tv_TTM_Points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.img_TTM_UserImg);
            tv_TTM_Name = itemView.findViewById(R.id.tv_TTM_Name);
            tv_TTM_MemType = itemView.findViewById(R.id.tv_TTM_MemType);
            tv_TTM_Tagline = itemView.findViewById(R.id.tv_TTM_Tagline);
            tv_TTM_Points = itemView.findViewById(R.id.tv_TTM_Points);

        }
    }
}

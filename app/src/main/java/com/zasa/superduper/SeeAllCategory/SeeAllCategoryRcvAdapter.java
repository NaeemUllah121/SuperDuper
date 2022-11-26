package com.zasa.superduper.SeeAllCategory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zasa.superduper.CompanyCategoryListTypeWiseS.SelectCategoryActivity;
import com.zasa.superduper.Home.CompanyTypeListModel;
import com.zasa.superduper.HotelAccommodation.HotelAccommodationActivity;
import com.zasa.superduper.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SeeAllCategoryRcvAdapter extends RecyclerView.Adapter<SeeAllCategoryRcvAdapter.ViewHolder> {
    ArrayList<CompanyTypeListModel> companyTypeListModelArrayList;
    Context context;

    public SeeAllCategoryRcvAdapter(ArrayList<CompanyTypeListModel> companyTypeListModelArrayList, Context context) {
        this.companyTypeListModelArrayList = companyTypeListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_see_all_category_item, parent, false);
        return new SeeAllCategoryRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Title = companyTypeListModelArrayList.get(position).getCompany_Type_Name();
        String code = companyTypeListModelArrayList.get(position).getCompany_Type_Code();
       // String imgUrl = "http://apis.loyaltybunch.com/CatIcons/" + code + ".png";
        String imgUrl = "http://apis.loyaltybunch.com/TypeIcons/" + code + ".png";


        if (code != null) {


            Glide.with(context)
                    .load(imgUrl).fitCenter()
                    .placeholder(R.drawable.noimgg)
                    /*.diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)*/
                    .into(holder.imageView);

           /* Picasso.get().load(imgUrl).fit().centerCrop()
                    .placeholder(R.drawable.noimg)
                    .error(R.drawable.noimg)
                    *//*.memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)*//*
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .into(holder.imageView);*/

        } else {
            holder.imageView.setImageResource(R.drawable.noimgg);
        }

        holder.tv_title.setText(Title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (code.equals("04")) {
                    Intent intent = new Intent(context, HotelAccommodationActivity.class);
                    intent.putExtra("Company_Type_Code", code);
                    intent.putExtra("companyName", Title);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, SelectCategoryActivity.class);
                    intent.putExtra("Company_Type_Code", code);
                    intent.putExtra("companyName", Title);
                    context.startActivity(intent);
                }
//                Intent intent = new Intent(context, CompanyListActivity.class);
//                intent.putExtra("companyCode", code);
//                intent.putExtra("companyName", Title);
//                // Toast.makeText(context, ""+code, Toast.LENGTH_SHORT).show();
//                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return companyTypeListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        CircleImageView imageView;
        //ImageButton imageView;
        // ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.seeAll_category_Title);
            imageView = itemView.findViewById(R.id.seeAll_category_image);
        }
    }
}

package com.zasa.superduper.Home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zasa.superduper.CompanyCategoryListTypeWiseS.SelectCategoryActivity;
import com.zasa.superduper.HotelAccommodation.HotelAccommodationActivity;
import com.zasa.superduper.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompanyTypeListRcvAdapter extends RecyclerView.Adapter<CompanyTypeListRcvAdapter.ViewHolder> {
    ArrayList<CompanyTypeListModel> companyTypeListModelArrayList;
    Context context;
    private static final String TAG = "checkkk";

    public CompanyTypeListRcvAdapter(ArrayList<CompanyTypeListModel> companyTypeListModelArrayList, Context context) {
        this.companyTypeListModelArrayList = companyTypeListModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public CompanyTypeListRcvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_company_type_list_item, parent, false);
        return new CompanyTypeListRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyTypeListRcvAdapter.ViewHolder holder, int position) {
        String Title = companyTypeListModelArrayList.get(position).getCompany_Type_Name();
        String code = companyTypeListModelArrayList.get(position).getCompany_Type_Code();
        // String imgUrl = "http://apis.loyaltybunch.com/CatIcons/" + code + ".png";
        String imgUrl = "http://apis.loyaltybunch.com/TypeIcons/" + code + ".png";


     /*   if (code.equals("09")) {
            holder.imageView.setImageResource(R.drawable.h);
        }else{*/
            if (code != null) {

                //RefreshPicassoCache(imgUrl);//extra
                Picasso.get().load(imgUrl).fit().centerCrop()
                        .placeholder(R.drawable.noimg)
                        .error(R.drawable.noimg)
                        //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        // .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .into(holder.imageView);

            /*Glide.with(context)
                    .load(imgUrl)
                    .fitCenter()
                    .placeholder(R.drawable.noimg)
                    //.diskCacheStrategy(DiskCacheStrategy.NONE)
                    //.skipMemoryCache(true)
                    .into(holder.imageView);*/


            } else {
                holder.imageView.setImageResource(R.drawable.noimg);
            }
      //  }




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

            tv_title = itemView.findViewById(R.id.category_Title);
            imageView = itemView.findViewById(R.id.category_image);
        }
    }

    public void RefreshPicassoCache(String path) {
        Picasso.get().invalidate("file:///" + path);
        Log.d(TAG, "RefreshPicassoCache: " + "file:///" + path);
    }

}

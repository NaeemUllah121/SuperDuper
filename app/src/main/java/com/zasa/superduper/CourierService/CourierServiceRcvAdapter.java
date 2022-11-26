package com.zasa.superduper.CourierService;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;
import com.zasa.superduper.CourierService.Models.CourierListModel;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class CourierServiceRcvAdapter extends RecyclerView.Adapter<CourierServiceRcvAdapter.ViewHolder> {
    ArrayList<CourierListModel> courierListModelArrayList;
    Context context;

    public CourierServiceRcvAdapter(ArrayList<CourierListModel> courierListModelArrayList, Context context) {
        this.courierListModelArrayList = courierListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_courier_item, parent, false);
        return new CourierServiceRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String Vendor_Unique = courierListModelArrayList.get(position).getVendor_Unique();
        String Vendor_Title = courierListModelArrayList.get(position).getVendor_Title();
        String Vendor_Full_Name = courierListModelArrayList.get(position).getVendor_Full_Name();
        String Vendor_Mobile = courierListModelArrayList.get(position).getVendor_Mobile();
        String Vendor_Dakiya = courierListModelArrayList.get(position).getVendor_Dakiya();
        int Vendor_Status = courierListModelArrayList.get(position).getVendor_Status();

        String imgUrl = "http://apis.loyaltybunch.com/Vendors_Logos/" + Vendor_Unique + ".png";
        if (Vendor_Unique != null) {

            /*      Glide.with(context)
                    .load(imgUrl).fitCenter()
                    .placeholder(R.drawable.noimg)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.iv_ItemImg);*/
            RefreshPicassoCache( imgUrl);
            Picasso.get().load(imgUrl).fit().centerInside()
                    .placeholder(R.drawable.onimage)
                    .error(R.drawable.onimage)
                    /*
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)*/
                    .into(holder.iv_ItemImg);

        } else {
            holder.iv_ItemImg.setImageResource(R.drawable.onimage);
        }

        holder.tv_courierName.setText(Vendor_Title);

        holder.tv_starRating.setText(holder.scaleRatingBar.getRating()+"");
        holder.scaleRatingBar.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(BaseRatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(BuyItemActivity.this, "rating "+ rating, Toast.LENGTH_SHORT).show();
                holder.tv_starRating.setText(rating+"");

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DakiyaActivity.class);
                intent.putExtra("Vendor_Unique",Vendor_Unique);
                intent.putExtra("Vendor_Title",Vendor_Title);
                intent.putExtra("Vendor_Full_Name",Vendor_Full_Name);
                intent.putExtra("Vendor_Mobile",Vendor_Mobile);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return courierListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_starRating,tv_courierName;
        ImageView iv_ItemImg;
        ScaleRatingBar scaleRatingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_starRating = itemView.findViewById(R.id.tv_starRating);
            tv_courierName = itemView.findViewById(R.id.tv_courierName);
            iv_ItemImg = itemView.findViewById(R.id.iv_ItemImg);
            scaleRatingBar = itemView.findViewById(R.id.simpleRatingBar);
        }
    }

    public void RefreshPicassoCache(String path) {
        Picasso.get().invalidate("file:///" + path);
    }
}

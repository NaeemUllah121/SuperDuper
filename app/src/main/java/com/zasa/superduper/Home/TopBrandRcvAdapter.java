package com.zasa.superduper.Home;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class TopBrandRcvAdapter extends RecyclerView.Adapter<TopBrandRcvAdapter.ViewHolder> {

    ArrayList<TopBrandListModel> topBrandListModelArrayList;
    Context context;
    RecyclerView recyclerView;

    /*public TopBrandRcvAdapter(ArrayList<TopBrandListModel> topBrandListModelArrayList, Context context) {
        this.topBrandListModelArrayList = topBrandListModelArrayList;
        this.context = context;
    }*/

    public TopBrandRcvAdapter(ArrayList<TopBrandListModel> topBrandListModelArrayList, Context context, RecyclerView recyclerView) {
        this.topBrandListModelArrayList = topBrandListModelArrayList;
        this.context = context;
        this.recyclerView = recyclerView;
    }
    //used to retrieve the effective item position in list
    public int getActualItemCount() {
        if (topBrandListModelArrayList == null) {
            topBrandListModelArrayList = new ArrayList<>();
        }
        return topBrandListModelArrayList.size();
    }
    @NonNull
    @Override
    public TopBrandRcvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_top_brand_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopBrandRcvAdapter.ViewHolder holder, int position) {
//          ////moving rcv
//        if (position == topBrandListModelArrayList.size() - 2) {
//            recyclerView.post(holder.runnable);
//        }
        String code = topBrandListModelArrayList.get(position).getCompany_Code();
        String imgUrl = "http://apis.loyaltybunch.com/Company_Logos/" + code + ".png";

        if (!TextUtils.isEmpty(code)) {

         /*   try {
                Glide.with(context.getApplicationContext())
                        .load(imgUrl)
                        //.centerInside()
                        //.fitCenter()
                        .centerCrop()
                        .placeholder(R.drawable.onimage)
                        .into(holder.imageView);
            } catch (Exception e) {

            }*/
            Glide.with(context.getApplicationContext())
                    .load(imgUrl)
                    //.centerInside()
                    .fitCenter()
                    //.centerCrop()
                    .placeholder(R.drawable.onimage)
                    .into(holder.imageView);

        } else {
            holder.imageView.setImageResource(R.drawable.noimg);
        }

      //  holder.tv_title.setText(""+position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
             /*   Intent intent = new Intent(context, HomeFragment.class);
                intent.putExtra("homeTopDeals", Title);
                context.startActivity(intent);
*/
            }
        });

          ////moving rcv
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
//                context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int positionn) {
//                Toast.makeText(context, "onItemClick" + getActualItemCount(), Toast.LENGTH_SHORT).show();
//                Log.i("recyclerView ", "onItemClick " + holder.getAdapterPosition());
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//                Log.i("recyclerView ", "onLongItemClick " + position);
//            }
//        }));



    }



    @Override
    public int getItemCount() {
          return topBrandListModelArrayList.size();

    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout topDealsLay;
        TextView tv_title;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.category_Title);
            imageView = itemView.findViewById(R.id.deal_Image);
            topDealsLay = itemView.findViewById(R.id.homeTopDeal);

        }

////moving rcv
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                topBrandListModelArrayList.addAll(topBrandListModelArrayList);
//                notifyDataSetChanged();
//
//            }
//        };



    }




}


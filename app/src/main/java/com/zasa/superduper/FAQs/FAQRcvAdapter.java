package com.zasa.superduper.FAQs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.zasa.superduper.R;

import java.util.ArrayList;


public class FAQRcvAdapter extends RecyclerView.Adapter<FAQRcvAdapter.ViewHolder> {


    ArrayList<FaqListModel> faqListModelArrayList;
    //ArrayList<CityListApiModel> cityListApiModelArrayList;
    Context context;

    int lastPosition = -1;

    public FAQRcvAdapter(ArrayList<FaqListModel> faqListModelArrayList, Context context) {
        this.faqListModelArrayList = faqListModelArrayList;
        this.context = context;
    }

   /* public FAQRcvAdapter(ArrayList<CityListApiModel> cityListApiModelArrayList, Context context) {
        this.cityListApiModelArrayList = cityListApiModelArrayList;
        this.context = context;
    }*/

    @NonNull
    @Override
    public FAQRcvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_faq_item, parent, false);
        return new FAQRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQRcvAdapter.ViewHolder holder, int position) {

        if (holder.getAdapterPosition() > lastPosition) {


            holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_rcv_row));

           // String FAQ_Title = cityListApiModelArrayList.get(position).getCity_Title();


            int FAQ_Id = faqListModelArrayList.get(position).getFAQ_Id();
            String FAQ_Title = faqListModelArrayList.get(position).getFAQ_Title();
            String FAQ_Details = faqListModelArrayList.get(position).getFAQ_Details();


            holder.tv_faqTitle.setText(FAQ_Title);
            holder.tv_faqDetail.setText(FAQ_Details);
            //holder.tv_faqTitleNo.setText(FAQ_Id+"-  ");


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        /////////////Expandable cardView////////////////
                    if (holder.tv_faqDetail.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.tv_faqDetail.setVisibility(View.VISIBLE);
                        holder.iv_expandIcon.setRotation(180);

                    } else {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.tv_faqDetail.setVisibility(View.GONE);
                        holder.iv_expandIcon.setRotation(360);

                    }


                }
            });


            lastPosition = holder.getAdapterPosition();


        }



       /* if (holder.getAdapterPosition() > lastPosition) {

            *//*Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_rcv_row);
            (holder.itemView).startAnimation(animation);*//*

            holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_rcv_row));

            // String FAQ_Title = cityListApiModelArrayList.get(position).getCity_Title();


            int FAQ_Id = faqListModelArrayList.get(position).getFAQ_Id();
            String FAQ_Title = faqListModelArrayList.get(position).getFAQ_Title();
            String FAQ_Details = faqListModelArrayList.get(position).getFAQ_Details();



            holder.tv_faqTitleNo.setText(FAQ_Id+"-  ");
            holder.tv_faqTitle.setText(FAQ_Title);
            holder.tv_faqDetail.setText(FAQ_Details);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (holder.tv_faqDetail.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.tv_faqDetail.setVisibility(View.VISIBLE);
                        holder.iv_expandIcon.setRotation(180);

                    } else {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.tv_faqDetail.setVisibility(View.GONE);
                        holder.iv_expandIcon.setRotation(360);

                    }


                }
            });

            lastPosition = holder.getAdapterPosition();


        }*/
    }

    @Override
    public int getItemCount() {
        return faqListModelArrayList.size();
       // return cityListApiModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_faqTitle, tv_faqDetail,tv_faqTitleNo;
        ImageView iv_expandIcon;
        CardView cardView;
        LinearLayoutCompat lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_faqTitleNo = itemView.findViewById(R.id.tv_faqTitleNo);
            tv_faqTitle = itemView.findViewById(R.id.tv_faqTitle);
            tv_faqDetail = itemView.findViewById(R.id.tv_faqDetail);
            iv_expandIcon = itemView.findViewById(R.id.iv_expandView);
            cardView = itemView.findViewById(R.id.cardView);
            lay = itemView.findViewById(R.id.lay);


        }
    }
}

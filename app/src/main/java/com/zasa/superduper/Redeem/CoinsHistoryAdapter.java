package com.zasa.superduper.Redeem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.CompanyListCategoryWiseS.Model;
import com.zasa.superduper.R;

import java.util.ArrayList;


public class CoinsHistoryAdapter extends RecyclerView.Adapter<CoinsHistoryAdapter.ViewHolder>{

    ArrayList<Model> models;
    Context context;

    public CoinsHistoryAdapter(ArrayList<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_coins_in_history_item, parent, false);
        return new CoinsHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String getCoinsIn = models.get(position).getCoinsIn();
        String getCoinOut = models.get(position).getCoinOut();
        String getDate = models.get(position).getDate();
        String getSerial = models.get(position).getSerial();


        if (getCoinsIn.equals("0")) {

            holder.arrowRed.setVisibility(View.VISIBLE);
            holder.pointsOut.setVisibility(View.VISIBLE);
            holder.arrowGreen.setVisibility(View.GONE);
            holder.pointsIn.setVisibility(View.GONE);

        }else {
            holder.arrowGreen.setVisibility(View.VISIBLE);
            holder.pointsIn.setVisibility(View.VISIBLE);
            holder.arrowRed.setVisibility(View.GONE);
            holder.pointsOut.setVisibility(View.GONE);
        }


        holder.date.setText(getDate);
        holder.sysId.setText(getSerial);
        holder.pointsIn.setText(getCoinsIn);
        holder.pointsOut.setText(getCoinOut);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView arrowGreen,arrowRed;
        TextView sysId,date,pointsOut,pointsIn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            arrowGreen = itemView.findViewById(R.id.arrowGreen);
            arrowRed = itemView.findViewById(R.id.arrowRed);
            sysId = itemView.findViewById(R.id.sysId);
            date = itemView.findViewById(R.id.date);
            pointsOut = itemView.findViewById(R.id.pointsOut);
            pointsIn = itemView.findViewById(R.id.pointsIn);
        }
    }
}

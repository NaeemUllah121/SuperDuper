package com.zasa.superduper.WasteMaterialRequestHistory;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.R;

import java.util.ArrayList;

public class MaterialRequestHistoryRcvAdapter extends RecyclerView.Adapter<MaterialRequestHistoryRcvAdapter.ViewHolder> {

    ArrayList<MOHListModel> mohListModelArrayList;
    Context context;

    public MaterialRequestHistoryRcvAdapter(ArrayList<MOHListModel> mohListModelArrayList, Context context) {
        this.mohListModelArrayList = mohListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_material_request_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String Oid = mohListModelArrayList.get(position).getOrder_Id();
        String OrderDate = mohListModelArrayList.get(position).getOrder_Date();
        String status = mohListModelArrayList.get(position).getOrder_Status();
        String address = mohListModelArrayList.get(position).getCollection_Address();
        String CollectDate = mohListModelArrayList.get(position).getCollection_DateTime();
        String Collected_By = mohListModelArrayList.get(position).getCollected_By();




        ///split date and time///
        String newOrderDate = holder.setDateTime(OrderDate);
        String newCollectDate = holder.setDateTime(CollectDate);

        /////set visibility if data is null////
        holder.viewVisibility(holder.lay_orderId, Oid);
        holder.viewVisibility(holder.lay_CollectionAddress, address);
        holder.viewVisibility(holder.lay_CollectedBy, Collected_By);
        holder.viewVisibility(holder.lay_CollectionDateTime, CollectDate);


        holder.tv_status.setText(status);
        holder.tv_OrderDate.setText(newOrderDate );
        holder.tv_OrderId.setText(Oid);
        holder.tv_address.setText(address);
        holder.tv_CollectDate.setText(newCollectDate);

        if (status.equals("Requested")) {
            holder.tv_status.setText(status);
            holder.tv_status.setTextColor(Color.parseColor("#FFF44336"));//red

        } else {
            holder.tv_status.setText(status);
            holder.tv_status.setTextColor(Color.parseColor("#FF4CAF50"));

        }




    }

    @Override
    public int getItemCount() {
        return mohListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View LayCollect;
        TextView tv_OrderId, tv_OrderDate, tv_address, tv_status, tv_CollectDate, tv_CollectedBy;
        View lay_orderId,lay_CollectionAddress,lay_CollectedBy,lay_CollectionDateTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_status = itemView.findViewById(R.id.mStatus);
            tv_OrderId = itemView.findViewById(R.id.oId);
            tv_OrderDate = itemView.findViewById(R.id.oDateTime);
            tv_address = itemView.findViewById(R.id.CAddress);
            tv_CollectedBy = itemView.findViewById(R.id.collectionBy);
            tv_CollectDate = itemView.findViewById(R.id.collectionDate);
            LayCollect = itemView.findViewById(R.id.layCollect);

            lay_orderId =itemView.findViewById(R.id.lay_orderId);
            lay_CollectionAddress =itemView.findViewById(R.id.lay_CollectionAddress);
            lay_CollectedBy =itemView.findViewById(R.id.lay_CollectedBy);
            lay_CollectionDateTime =itemView.findViewById(R.id.lay_CollectionDateTime);

        }




        public String setDateTime(String dateTime) {

            if (TextUtils.isEmpty(dateTime)) {
                return null;
            }

            String[] OData = dateTime.split("T");
            String date = OData[0];
            String OTime = OData[1];

            String[] OTimeData = OTime.split("\\.");//store all string before dot
            String time = OTimeData[0];

            return date + " " + time;
        }

        public void viewVisibility(View view, String viewData) {

            if (TextUtils.isEmpty(viewData)) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);

            }


        }
    }
}

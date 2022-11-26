package com.zasa.superduper.CourierService;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.CourierService.Models.DeliveryRequestHistoryModel;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class DeliveryRequestHistoryRcvAdapter extends RecyclerView.Adapter<DeliveryRequestHistoryRcvAdapter.ViewHolder> {

    ArrayList<DeliveryRequestHistoryModel> deliveryRequestHistoryModelArrayList;
    Context context;

    public DeliveryRequestHistoryRcvAdapter(ArrayList<DeliveryRequestHistoryModel> deliveryRequestHistoryModelArrayList, Context context) {
        this.deliveryRequestHistoryModelArrayList = deliveryRequestHistoryModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeliveryRequestHistoryRcvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_delivery_request_history_item, parent, false);
        return new DeliveryRequestHistoryRcvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryRequestHistoryRcvAdapter.ViewHolder holder, int position) {
        String Customer_Full_Name = deliveryRequestHistoryModelArrayList.get(position).getCustomer_Full_Name();
        String Customer_Mobile = deliveryRequestHistoryModelArrayList.get(position).getCustomer_Mobile();
        String Receiver_Full_Name = deliveryRequestHistoryModelArrayList.get(position).getReceiver_Full_Name();
        String Receiver_Mobile = deliveryRequestHistoryModelArrayList.get(position).getReceiver_Mobile();
        int Delivery_Status = deliveryRequestHistoryModelArrayList.get(position).getDelivery_Status();
        String Delivered_By = deliveryRequestHistoryModelArrayList.get(position).getDelivered_By();
        String Delivered_On = deliveryRequestHistoryModelArrayList.get(position).getDelivered_On();
        String Collection_DateTime = deliveryRequestHistoryModelArrayList.get(position).getCollection_DateTime();
        String Delivered_Chargs = deliveryRequestHistoryModelArrayList.get(position).getDelivered_Chargs();
        String Return_Reason = deliveryRequestHistoryModelArrayList.get(position).getReturn_Reason();
        String Collected_By = deliveryRequestHistoryModelArrayList.get(position).getCollected_By();
        String Collected_On = deliveryRequestHistoryModelArrayList.get(position).getCollected_On();
        String Vendor_Title = deliveryRequestHistoryModelArrayList.get(position).getVendor_Title();
        int No_Of_Parcels = deliveryRequestHistoryModelArrayList.get(position).getNo_Of_Parcels();

        ///split date and time///
        String parcelPickupDate = holder.setDateTime(Collection_DateTime);
        String Collected_OnDate = holder.setDateTime(Collected_On);
        String Delivered_OnDate = holder.setDateTime(Delivered_On);


        /////set visibility if data is null////
      /*  holder.viewVisibility(holder.lay_SenderName, Customer_Full_Name);
        holder.viewVisibility(holder.lay_SenderMobile, Customer_Mobile);*/
        holder.viewVisibility(holder.lay_ReceiverName, Receiver_Full_Name);
        holder.viewVisibility(holder.lay_ReceiverMobile, Receiver_Mobile);
   /*     holder.viewVisibility(holder.lay_DeliveredBy, Delivered_By);
        holder.viewVisibility(holder.lay_collectedBy, Collected_By);*/
        holder.viewVisibility(holder.lay_DeliveredChargs, Delivered_Chargs);
        holder.viewVisibility(holder.lay_ReturnReason, Return_Reason);
        holder.viewVisibility(holder.lay_CollectedOn, Collected_On);
        holder.viewVisibility(holder.lay_DeliveredON, Delivered_On);
        holder.viewVisibility(holder.lay_CourierCompany, Vendor_Title);
       // holder.viewVisibility(holder.lay_No_Of_Parcels, No_Of_Parcels);


        holder.tv_ParcelPickupDate.setText(parcelPickupDate);
        holder.tv_CollectedOn.setText(Collected_OnDate);
        holder.tv_DeliveredON.setText(Delivered_OnDate);
       /* holder.tv_SenderName.setText(Customer_Full_Name);
        holder.tv_SenderMobile.setText(Customer_Mobile);*/
        holder.tv_ReceiverName.setText(Receiver_Full_Name);
        holder.tv_ReceiverMobile.setText(Receiver_Mobile);
      /*  holder.tv_DeliveredBy.setText(Delivered_By);
        holder.tv_collectedBy.setText(Collected_By);*/
        holder.tv_ReturnReason.setText(Return_Reason);
        holder.tv_CourierCompany.setText(Vendor_Title);
        holder.tv_No_Of_Parcels.setText(No_Of_Parcels+"");
        holder.tv_DeliveredChargs.setText(Delivered_Chargs);

        if (Delivery_Status == 0) {
            holder.mStatus.setText("Pending");
            holder.mStatus.setTextColor(Color.parseColor("#FFF44336"));

        } else {
            holder.mStatus.setText("Delivered");
            holder.mStatus.setTextColor(Color.parseColor("#FF4CAF50"));

        }


    }

    @Override
    public int getItemCount() {
        return deliveryRequestHistoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_SenderName, tv_SenderMobile, tv_ReceiverName, tv_ReceiverMobile,
                tv_collectedBy, tv_DeliveredBy, tv_DeliveredON, tv_DeliveredChargs,
                tv_CollectedOn, mStatus, tv_ReturnReason,tv_CourierCompany,tv_No_Of_Parcels,tv_ParcelPickupDate;

        View lay_SenderName, lay_SenderMobile, lay_ReceiverName, lay_ReceiverMobile,
                lay_DeliveredChargs, lay_collectedBy, lay_DeliveredBy, lay_DeliveredON, lay_CollectedOn,lay_ReturnReason,
                lay_CourierCompany,lay_No_Of_Parcels;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           /* lay_SenderName = itemView.findViewById(R.id.lay_SenderName);
            lay_SenderMobile = itemView.findViewById(R.id.lay_SenderMobile);*/
            lay_ReceiverName = itemView.findViewById(R.id.lay_ReceiverName);
            lay_ReceiverMobile = itemView.findViewById(R.id.lay_ReceiverMobile);
            lay_DeliveredChargs = itemView.findViewById(R.id.lay_DeliveredChargs);
            /*lay_collectedBy = itemView.findViewById(R.id.lay_collectedBy);
            lay_DeliveredBy = itemView.findViewById(R.id.lay_DeliveredB);*/
            lay_CollectedOn = itemView.findViewById(R.id.lay_CollectedOn);
            lay_DeliveredON = itemView.findViewById(R.id.lay_DeliveredON);
            lay_ReturnReason = itemView.findViewById(R.id.lay_ReturnReason);
            lay_CourierCompany = itemView.findViewById(R.id.lay_CourierCompany);
            lay_No_Of_Parcels = itemView.findViewById(R.id.lay_No_Of_Parcels);

            /*tv_SenderName = itemView.findViewById(R.id.tv_SenderName);
            tv_SenderMobile = itemView.findViewById(R.id.tv_SenderMobile);*/
            tv_ReceiverName = itemView.findViewById(R.id.tv_ReceiverName);
            tv_ReceiverMobile = itemView.findViewById(R.id.tv_ReceiverMobile);
           /* tv_collectedBy = itemView.findViewById(R.id.tv_collectedBy);
            tv_DeliveredBy = itemView.findViewById(R.id.tv_DeliveredBy);*/
            tv_DeliveredON = itemView.findViewById(R.id.tv_DeliveredON);
            tv_DeliveredChargs = itemView.findViewById(R.id.tv_DeliveredChargs);
            tv_ParcelPickupDate = itemView.findViewById(R.id.tv_ParcelPickupDateTime);
            tv_CollectedOn = itemView.findViewById(R.id.tv_CollectedOn);
            mStatus = itemView.findViewById(R.id.mStatus);
            tv_ReturnReason = itemView.findViewById(R.id.tv_ReturnReason);
            tv_CourierCompany = itemView.findViewById(R.id.tv_CourierCompany);
            tv_No_Of_Parcels = itemView.findViewById(R.id.tv_No_Of_Parcels);

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

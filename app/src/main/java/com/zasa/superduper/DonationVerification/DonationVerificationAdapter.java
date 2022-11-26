package com.zasa.superduper.DonationVerification;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.zasa.superduper.R;
import com.zasa.superduper.Retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DonationVerificationAdapter extends RecyclerView.Adapter<DonationVerificationAdapter.ViewHolder> {

    ArrayList<UnVerifyFamilyOrderModel> unVerifyFamilyOrderModelArrayList;
    Context context;

    public DonationVerificationAdapter(ArrayList<UnVerifyFamilyOrderModel> unVerifyFamilyOrderModelArrayList, Context context) {
        this.unVerifyFamilyOrderModelArrayList = unVerifyFamilyOrderModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rcv_verify_donation_item, parent, false);
        return new DonationVerificationAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String Member_Verification = unVerifyFamilyOrderModelArrayList.get(position).getMember_Verification();
        String Oid = unVerifyFamilyOrderModelArrayList.get(position).getOrder_Id();
        String Member_Unique = unVerifyFamilyOrderModelArrayList.get(position).getMember_Unique().trim();
        String Order_Date = unVerifyFamilyOrderModelArrayList.get(position).getOrder_Date();
        String Member_Verification_OnDate = unVerifyFamilyOrderModelArrayList.get(position).getMember_Verification_On();
        float Order_Amount = unVerifyFamilyOrderModelArrayList.get(position).getOrder_Amount();


        if (Member_Verification.equals("Y")) {

            holder.loading_btn.setText("VERIFIED");
            holder.loading_btn.setText("VERIFIED");
            holder.loading_btn.setEnabled(false);
        }

        if(TextUtils.isEmpty(Member_Verification_OnDate)){

            holder.lay_OrderDate.setVisibility(View.VISIBLE);
            holder.lay_VerifyOnDate.setVisibility(View.GONE);
        }else {

            holder.lay_VerifyOnDate.setVisibility(View.VISIBLE);
            holder.lay_OrderDate.setVisibility(View.GONE);
        }


        String newOrderDate = holder.setDateTime(Order_Date);
        String newVerification_OnDate= holder.setDateTime(Member_Verification_OnDate);

        holder.tv_OrderDate.setText(newOrderDate);
        holder.tv_VerifyOnDate.setText(newVerification_OnDate);
        holder.tv_ReceivedAmount.setText(""+Order_Amount );


        holder.loading_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (context instanceof DonationVerificationActivity) {

                   holder.VerifyFamilyOrderApiCall(Member_Unique,Oid);

//                    ((DonationVerificationActivity)context).VerifyFamilyOrderApiCall(Member_Unique,Oid);
//                    holder.loading_btn.startLoading(); //start loading

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return unVerifyFamilyOrderModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_OrderDate,tv_VerifyOnDate,tv_ReceivedAmount;
        LoadingButton loading_btn;
        View lay_OrderDate,lay_VerifyOnDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            loading_btn = itemView.findViewById(R.id.loading_btn);
            tv_ReceivedAmount = itemView.findViewById(R.id.tv_ReceivedAmount);
            tv_OrderDate = itemView.findViewById(R.id.tv_OrderDate);
            tv_VerifyOnDate = itemView.findViewById(R.id.tv_VerifyOnDate);
            lay_OrderDate = itemView.findViewById(R.id.lay_OrderDate);
            lay_VerifyOnDate = itemView.findViewById(R.id.lay_VerifyOnDate);

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

        public void VerifyFamilyOrderApiCall(String st_sharedPref_MemberUnique, String OrderId) {

           loading_btn.startLoading();
            // progressDialog.show();
            Call<VerifyFOrderApi> call = RetrofitInstance
                    .getInstance()
                    .getApiInterface()
                    .mVerifyFOrderApi(st_sharedPref_MemberUnique, OrderId);

            call.enqueue(new Callback<VerifyFOrderApi>() {
                @Override
                public void onResponse(@NonNull Call<VerifyFOrderApi> call, @NonNull Response<VerifyFOrderApi> response) {

                    VerifyFOrderApi verifyFOrderApi = response.body();

                    if (response.isSuccessful()) {
                        if (verifyFOrderApi != null) {
                            if (verifyFOrderApi.getStatus() == 1) {

                                loading_btn.loadingSuccessful();
                                loading_btn.setEnabled(false);
                                //progressDialog.dismiss();
                                Toast.makeText(context, "" + verifyFOrderApi.getMessage(), Toast.LENGTH_LONG).show();
                               // context.finish();
                            } else {
                                loading_btn.loadingFailed();
                                //progressDialog.dismiss();
                                Toast.makeText(context, "" + verifyFOrderApi.getMessage(), Toast.LENGTH_SHORT).show();
                                loading_btn.reset();

                            }

                        } else {
                            loading_btn.loadingFailed();
                            // progressDialog.dismiss();
                            Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                            loading_btn.reset();
                        }
                    } else {
                        loading_btn.loadingFailed();
                        //progressDialog.dismiss();
                        Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                        loading_btn.reset();

                    }
                }

                @Override
                public void onFailure(@NonNull Call<VerifyFOrderApi> call, @NonNull Throwable t) {
                    loading_btn.loadingFailed();
                    //progressDialog.dismiss();
                    Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    loading_btn.reset();
                }
            });
        }




    }
}

package com.zasa.superduper.Redeem;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.zasa.superduper.R;
import com.zasa.superduper.Utils.Constant;

import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

    ArrayList<CouponListModel> couponListModelArrayList;
    Context context;

    public CouponAdapter(ArrayList<CouponListModel> couponListModelArrayList, Context context) {
        this.couponListModelArrayList = couponListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CouponAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_coupon_item, parent, false);
        return new CouponAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponAdapter.ViewHolder holder, int position) {

        String Coupon_Unique = couponListModelArrayList.get(position).getCoupon_Unique();
        String Promotion_Unique = couponListModelArrayList.get(position).getPromotion_Unique();
        String Promotion_Description = couponListModelArrayList.get(position).getPromotion_Description();

        int Promotion_Type = couponListModelArrayList.get(position).getPromotion_Type();
        int Promotion_Claim_Type = couponListModelArrayList.get(position).getPromotion_Claim_Type();
        int Promotion_Limit_Min = couponListModelArrayList.get(position).getPromotion_Limit_Min();
        int Promotion_Limit_Max = couponListModelArrayList.get(position).getPromotion_Limit_Max();
        int Promotion_Cashback_Amount = couponListModelArrayList.get(position).getPromotion_Cashback_Amount();
        int Promotion_Discount_Amount = couponListModelArrayList.get(position).getPromotion_Discount_Amount();
        float Promotion_Discount_Per = couponListModelArrayList.get(position).getPromotion_Discount_Per();

        String Company_Category_Code = couponListModelArrayList.get(position).getCompany_Category_Code();
        String Issued_Company_Code = couponListModelArrayList.get(position).getIssued_Company_Code();
        String Issued_Branch_Code = couponListModelArrayList.get(position).getIssued_Branch_Code();
        String Coupon_IssueDate = couponListModelArrayList.get(position).getCoupon_IssueDate();
        String Coupon_ExpiryDate = couponListModelArrayList.get(position).getCoupon_ExpiryDate();
        String Member_Unique = couponListModelArrayList.get(position).getMember_Unique();
        String Coupon_ClaimDate = couponListModelArrayList.get(position).getCoupon_ClaimDate();
        String Coupon_RadeemDate = couponListModelArrayList.get(position).getCoupon_RadeemDate();


        String newIssueDate = holder.setDateTime(Coupon_IssueDate);
        String newCoupon_ExpiryDate = holder.setDateTime(Coupon_ExpiryDate);
        String newCoupon_RadeemDate = holder.setDateTime(Coupon_RadeemDate);
        String newCoupon_ClaimDatee = holder.setDateTime(Coupon_ClaimDate);

        long days = Constant.getDayTillEndDate(newCoupon_ExpiryDate);

        if (days > 0) {
            //holder.lay_coupon.setBackgroundColor(ContextCompat.getColor(context,R.color.black));
            // holder.lay_main.setBackgroundColor(Color.parseColor("#f4af28"));
            holder.lay_main.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_bg_coupon_available_style));
            holder.btn_couponAction.setText("Claim Now");
        } else if (days == 0) {
            holder.lay_main.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_bg_coupon_expire_style));
            holder.btn_couponAction.setText("Expired");
        } else if (!TextUtils.isEmpty(Coupon_ClaimDate)) {
            holder.lay_main.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_bg_coupon_used_style));
            holder.btn_couponAction.setText("Used");
        }


        //Toast.makeText(context, "" + days, Toast.LENGTH_SHORT).show();


        holder.tv_IssueOn.setText(newIssueDate);
        holder.tv_expireOn.setText(newCoupon_ExpiryDate);
        holder.tv_couponDescription.setText(Promotion_Description);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.dialog();
            }
        });

    }

    @Override
    public int getItemCount() {
        return couponListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_IssueOn, tv_expireOn, tv_couponDescription;
        Button btn_couponAction;
        View lay_main, lay_coupon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_IssueOn = itemView.findViewById(R.id.tv_IssueOn);
            tv_expireOn = itemView.findViewById(R.id.tv_expireOn);
            tv_couponDescription = itemView.findViewById(R.id.tv_couponDescription);
            btn_couponAction = itemView.findViewById(R.id.btn_couponAction);
            lay_main = itemView.findViewById(R.id.lay_main);
            lay_coupon = itemView.findViewById(R.id.lay_coupon);
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

            return date /*+ " " + time*/;
        }


        public void dialog() {


            Dialog dialogView = new Dialog(context);
            dialogView.setContentView(R.layout.alertdialog_coupon_info);

            if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
                dialogView.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.style_custom_dialog_background));
            } else {
                dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            dialogView.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations; //Setting the animations to dialog


            TextView tv_flatAmount, tv_amountInPercent, tv_minAmount, tv_maxAmount, tv_ValidityOn, tv_CouponDescription,
                    tv_ClaimType, tv_PromotionType;
            ImageView closeBtn,iv_barCode;
            View lay_flatAmount, lay_amountInPercent;

            tv_flatAmount = dialogView.findViewById(R.id.tv_flatAmount);
            tv_amountInPercent = dialogView.findViewById(R.id.tv_amountInPercent);
            tv_minAmount = dialogView.findViewById(R.id.tv_minAmount);
            tv_maxAmount = dialogView.findViewById(R.id.tv_maxAmount);
            tv_ValidityOn = dialogView.findViewById(R.id.tv_ValidityOn);
            tv_CouponDescription = dialogView.findViewById(R.id.tv_CouponDescription);
            tv_ClaimType = dialogView.findViewById(R.id.tv_claimType);
            tv_PromotionType = dialogView.findViewById(R.id.tv_PromotionType);

            lay_flatAmount = dialogView.findViewById(R.id.lay_flatAmount);
            lay_amountInPercent = dialogView.findViewById(R.id.lay_amountInPercent);

            closeBtn = dialogView.findViewById(R.id.closeBtn);
            iv_barCode = dialogView.findViewById(R.id.barCode);


            int Promotion_Type = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Type();
            int Promotion_Claim_Type = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Claim_Type();
            int Promotion_Limit_Min = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Limit_Min();
            int Promotion_Limit_Max = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Limit_Max();

            int Promotion_Cashback_Amount = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Cashback_Amount();
            int Promotion_Discount_Amount = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Discount_Amount();
            float Promotion_Discount_Per = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Discount_Per();

            String Promotion_Description = couponListModelArrayList.get(getBindingAdapterPosition()).getPromotion_Description();
            String Coupon_ExpiryDate = couponListModelArrayList.get(getBindingAdapterPosition()).getCoupon_ExpiryDate();
            String Coupon_Unique = couponListModelArrayList.get(getBindingAdapterPosition()).getCoupon_Unique();

            try {
                iv_barCode.setImageBitmap(barCode(Coupon_Unique));

            } catch (Exception e) {
                e.printStackTrace();
            }


            if (Promotion_Claim_Type == 1) {
                tv_ClaimType.setText("All Vendors");
            } else if (Promotion_Claim_Type == 2) {
                tv_ClaimType.setText("Selected Vendor Category");
            } else {
                tv_ClaimType.setText("On Specific Vendor");
            }


            if (Promotion_Type == 1) {
                tv_PromotionType.setText("Discount Fix");
                tv_flatAmount.setText(Promotion_Discount_Amount + "");

                lay_flatAmount.setVisibility(View.VISIBLE);
                lay_amountInPercent.setVisibility(View.GONE);
            } else if (Promotion_Type == 3) {
                tv_PromotionType.setText("Cashback Fix");

                tv_flatAmount.setText(Promotion_Cashback_Amount + "");
                lay_flatAmount.setVisibility(View.VISIBLE);
                lay_amountInPercent.setVisibility(View.GONE);
            } else if (Promotion_Type == 2) {
                tv_PromotionType.setText("Discount Target");

                tv_amountInPercent.setText(Promotion_Discount_Per + "");
                tv_minAmount.setText(Promotion_Limit_Min + "");
                tv_maxAmount.setText(Promotion_Limit_Max + "");

                lay_flatAmount.setVisibility(View.GONE);
                lay_amountInPercent.setVisibility(View.VISIBLE);

            } else if (Promotion_Type == 4) {
                tv_PromotionType.setText("Cashback Target");

                tv_amountInPercent.setText(Promotion_Discount_Per + "");
                tv_minAmount.setText(Promotion_Limit_Min + "");
                tv_maxAmount.setText(Promotion_Limit_Max + "");

                lay_flatAmount.setVisibility(View.GONE);
                lay_amountInPercent.setVisibility(View.VISIBLE);


            } else {
                tv_PromotionType.setText("Cashback Fix (Self Created)");

                tv_flatAmount.setText(Promotion_Cashback_Amount + "");
                lay_flatAmount.setVisibility(View.VISIBLE);
                lay_amountInPercent.setVisibility(View.GONE);
            }

            String newCoupon_ExpiryDate = setDateTime(Coupon_ExpiryDate);
            tv_ValidityOn.setText(newCoupon_ExpiryDate);

            tv_CouponDescription.setText(Promotion_Description);


            // Close Button
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogView.dismiss();
                }
            });

            dialogView.show();


        }

      //  MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        public Bitmap barCode(String Coupon_Unique) throws WriterException {

            BitMatrix bitMatrix = new MultiFormatWriter().encode(Coupon_Unique, BarcodeFormat.CODE_128, 430, 150);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            return bitmap;
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

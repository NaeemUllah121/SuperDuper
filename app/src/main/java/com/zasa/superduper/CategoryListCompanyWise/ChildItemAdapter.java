package com.zasa.superduper.CategoryListCompanyWise;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.BuyItemDetails.BuyItemActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.R;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {
    float Discount_Percentage, Discount_Value, new_Item_Sale_Price;
    float Value, Price;

    private ArrayList<ChildItemList> ChildItemList;
    int Item_Category_Code;
    Context context;


    public ChildItemAdapter() {

    }

    public ChildItemAdapter(ArrayList<ChildItemList> childItemList, int item_Category_Code, Context context) {
        ChildItemList = childItemList;
        Item_Category_Code = item_Category_Code;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rcv_category_child_item, viewGroup, false);
        //context = viewGroup.getContext();
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {


        int getItem_Category_Code = ChildItemList.get(position).getItem_Category_Code();
        String ItemImage = ChildItemList.get(position).getItem_Image();
        String Company_Code = ChildItemList.get(position).getCompany_Code();
        String Client_Code = ChildItemList.get(position).getClient_Code();
        String ItemCode = ChildItemList.get(position).getItem_Code();
        String Item_Name = ChildItemList.get(position).getItem_Name();
        float Item_Sale_Price = ChildItemList.get(position).getItem_Sale_Price();
        int Item_IsActive = ChildItemList.get(position).getItem_IsActive();

        String Item_Description = ChildItemList.get(position).getItem_Description();
        String Item_Unit_Name = ChildItemList.get(position).getItem_Unit_Name();
        String LB_Discount_Percentage = ChildItemList.get(position).getLB_Discount_Percentage();
         /*String LB_Discount_Percentage = "60";
        String Item_Description = "This is description";
        String Item_Unit_Name = "25 kg";*/

        ///show image
        if (TextUtils.isEmpty(ItemImage)) {
            childViewHolder.iv_itemImg.setImageResource(R.drawable.onimage);
        } else {
            byte[] imageBytes = Base64.decode(ItemImage, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            childViewHolder.iv_itemImg.setImageBitmap(decodedImage);
        }

        childViewHolder.tvItemTitle.setText(Item_Name);

        //set item unit
        if (TextUtils.isEmpty(Item_Unit_Name)) {
            childViewHolder.tv_itemUnit.setVisibility(View.GONE);
        } else {
            childViewHolder.tv_itemUnit.setText(Item_Unit_Name);
        }


        int Member_Type;

        //Member Details shared pref
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
             Member_Type = memberDetailsApiModel.getMember_Type();
            //Member_Type = 2;

        }else{
            SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            Member_Type = sharedPreferences.getInt("Member_Type", 0);
             //Member_Type = 2;
        }

        ////show old price
        if (TextUtils.isEmpty(LB_Discount_Percentage)) {

            childViewHolder.oldPrice.setVisibility(View.GONE);
            childViewHolder.newPrice.setText("Rs. " + Item_Sale_Price);
            childViewHolder.tv_itemDiscountBadge.setVisibility(View.GONE);

        } else if (Member_Type == 1) {

            childViewHolder.oldPrice.setVisibility(View.GONE);
            childViewHolder.newPrice.setText("Rs. " + Item_Sale_Price);
            childViewHolder.tv_itemDiscountBadge.setVisibility(View.GONE);

        } else {


            Discount_Percentage = (Float.parseFloat(LB_Discount_Percentage) / 100.0f);
            Value = (Item_Sale_Price * Discount_Percentage);

            /////roundOf Number
            Discount_Value = childViewHolder.roundOneDecimals(Value);
            Price = Item_Sale_Price - Discount_Value;

            /////roundOf Number
            new_Item_Sale_Price = childViewHolder.roundOneDecimals(Price);
            childViewHolder.newPrice.setText("Rs. " + new_Item_Sale_Price);//discounted price


            if (Item_Sale_Price <= 0.0) {
                childViewHolder.oldPrice.setVisibility(View.GONE);
            } else {
                childViewHolder.oldPrice.setText("Rs. " + Item_Sale_Price); ////without discounted price
                childViewHolder.oldPrice.setPaintFlags(childViewHolder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);//(show line on old price)

            }

            if (Item_Sale_Price <= 0.0) {
                childViewHolder.tv_itemDiscountBadge.setVisibility(View.GONE);
            } else {
                childViewHolder.tv_itemDiscountBadge.setText(LB_Discount_Percentage + "% Off"); // show line on old price

            }


        }
//        if (Item_Category_Code != getItem_Category_Code) {
//            childViewHolder.itemView.setVisibility(View.GONE);
//        }else {
//            childViewHolder.itemView.setVisibility(View.VISIBLE);
//
//        }


        childViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, BuyItemActivity.class);


                String old_String = childViewHolder.newPrice.getText().toString();
                String new_String = old_String.replaceAll(".+ ", "");///remove all char before specific string

                intent.putExtra("getItem_Category_Code", getItem_Category_Code);
                intent.putExtra("ItemCode", ItemCode);
                intent.putExtra("Company_Code", Company_Code);
                intent.putExtra("Client_Code", Client_Code);
                intent.putExtra("Item_Name", Item_Name);
                intent.putExtra("itemImg", ItemImage);
                intent.putExtra("Item_Sale_Price", Item_Sale_Price);
                intent.putExtra("Item_IsActive", Item_IsActive);
                intent.putExtra("LB_Discount_Percentage", LB_Discount_Percentage);
                intent.putExtra("Item_Unit_Name", Item_Unit_Name);
                intent.putExtra("Item_Description", Item_Description);

                intent.putExtra("new_Item_Sale_Price", new_String);

                context.startActivity(intent);


            }
        });


    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        return ChildItemList.size();
    }

//    public void filteredListMethod(ArrayList<ChildItemList> filteredList) {
//        ChildItemList = filteredList;
//        notifyDataSetChanged();
//    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle, oldPrice, newPrice, tv_itemDiscountBadge, tv_itemUnit;
        ImageView iv_itemImg;
        RatingBar RatingBar;

        ChildViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.child_item_title);
            iv_itemImg = itemView.findViewById(R.id.img_child_item);
            oldPrice = itemView.findViewById(R.id.oldPrice);
            newPrice = itemView.findViewById(R.id.newPrice);
            RatingBar = itemView.findViewById(R.id.ratingBar);
            tv_itemDiscountBadge = itemView.findViewById(R.id.itemDisc);
            tv_itemUnit = itemView.findViewById(R.id.item_unit);

        }

        public float roundOneDecimals(float value) {
            DecimalFormat twoDForm = new DecimalFormat("#.#");
            return Float.parseFloat(twoDForm.format(value));
        }

        public String imgConverterBase64(Bitmap imageBitmap) {
            //encode image to base64 string
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();

            return Base64.encodeToString(imageBytes, Base64.DEFAULT);


        }

        public Bitmap imgDecoderBit(String imageBitmap) {

            //decode base64 string to image
            byte[] imageBytes = Base64.decode(imageBitmap, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            return decodedImage;
        }


    }
}


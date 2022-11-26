package com.zasa.superduper.CompanyListTypeWise;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zasa.superduper.CategoryListCompanyWise.CategoryListActivity;
import com.zasa.superduper.Profile.MemberDetailsApiModel;
import com.zasa.superduper.R;
import com.zasa.superduper.Utils.SharedPrefManager;

import java.util.ArrayList;

public class CompanyListRcvAdapter extends RecyclerView.Adapter<CompanyListRcvAdapter.ViewHolder> {

    ArrayList<CompanyListTypeWiseApiModel> companyListModelArrayListTypeWiseApi;
    Context context;


    public CompanyListRcvAdapter(ArrayList<CompanyListTypeWiseApiModel> companyListModelArrayListTypeWiseApi, Context context) {
        this.companyListModelArrayListTypeWiseApi = companyListModelArrayListTypeWiseApi;
        this.context = context;
    }


    @NonNull
    @Override
    public CompanyListRcvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_company_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyListRcvAdapter.ViewHolder holder, int position) {
        String company_code = companyListModelArrayListTypeWiseApi.get(position).getCompany_Code();
        String Title = companyListModelArrayListTypeWiseApi.get(position).getCompany_Name();
        String Company_Logo = companyListModelArrayListTypeWiseApi.get(position).getCompany_Logo();
        String Company_Address = companyListModelArrayListTypeWiseApi.get(position).getCompany_Address();
        String Company_Phone = companyListModelArrayListTypeWiseApi.get(position).getCompany_Phone();
        String Company_Fax = companyListModelArrayListTypeWiseApi.get(position).getCompany_Fax();
        String Company_Email = companyListModelArrayListTypeWiseApi.get(position).getCompany_Email();
        String Company_Contact = companyListModelArrayListTypeWiseApi.get(position).getCompany_Contact_Person();
        float Max_Discount_Limit = companyListModelArrayListTypeWiseApi.get(position).getMax_Discount_Limit();

        int maxDis = Math.round(Max_Discount_Limit);
        holder.tv_title.setText(Title);

        String imgUrl = "http://apis.loyaltybunch.com/Banners_Company/" + company_code + ".jpg";

        if (company_code != null) {

           /* // RefreshPicassoCache( imgUrl);
            Picasso.get().
                    load(imgUrl).fit().centerCrop()
                    .placeholder(R.drawable.no_image_banner)
                    .error(R.drawable.no_image_banner)

                    *//*.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)*//*
                    .into(holder.iv_company_banner);*/


            Glide.with(context)
                    .load(imgUrl)
                    .fitCenter().placeholder(R.drawable.no_image_banner)
                    /*.diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)*/
                    .into(holder.iv_company_banner);

        } else {
            //holder.iv_company_banner.setImageResource(R.drawable.onimage);
            holder.iv_company_banner.setImageResource(R.drawable.no_image_banner);
        }


        ///set user Type i.e Free Member (1), Premium (2), family member (3)
        int Member_Type;
        //Member Details shared pref
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        MemberDetailsApiModel memberDetailsApiModel = sharedPrefManager.getMemberDetails();
        if (memberDetailsApiModel != null) {
            Member_Type = memberDetailsApiModel.getMember_Type();

        } else {
            //login shared pref
            SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
            Member_Type = sharedPreferences.getInt("Member_Type", 0);
        }

        if (Max_Discount_Limit <= 0 || Member_Type == 1) {//Free Member (1) and Premium (2)
            holder.tv_discountBadge.setVisibility(View.GONE);
        } else {
            holder.tv_discountBadge.setText("UP TO " + maxDis + "% OFF");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///category list company wise
                Intent intent = new Intent(context, CategoryListActivity.class);
                intent.putExtra("Title", Title);
                intent.putExtra("company_code", company_code);
                context.startActivity(intent);

            }
        });

        holder.ib_company_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dialog(Company_Address, Company_Phone, Company_Fax, Company_Email, Company_Contact);


            }


        });


    }

    @Override
    public int getItemCount() {
        return companyListModelArrayListTypeWiseApi.size();
    }

    //search RCV item
    public void filteredListMethod(ArrayList<CompanyListTypeWiseApiModel> filteredList) {
        companyListModelArrayListTypeWiseApi = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_discountBadge;
        ImageView iv_company_banner;
        ImageButton ib_company_info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.company_name);
            iv_company_banner = itemView.findViewById(R.id.comapny_img);
            ib_company_info = itemView.findViewById(R.id.company_location);
            tv_discountBadge = itemView.findViewById(R.id.discountBadge);


        }

      /*  public void dialog(String Company_Address, String Company_Phone, String Company_Fax, String Company_Email, String Company_Contact) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_company_info, null);
            builder.setView(dialogView).setCancelable(true);

            TextView tv_Phone, tv_Fax, tv_Email, tv_Contact, tv_Address;

            tv_Phone = dialogView.findViewById(R.id.Company_Phone_dialog);
            tv_Fax = dialogView.findViewById(R.id.Company_Fax_dialog);
            tv_Email = dialogView.findViewById(R.id.Company_Email_dialog);
            tv_Contact = dialogView.findViewById(R.id.Company_Contact_dialog);
            tv_Address = dialogView.findViewById(R.id.Company_Address_dialog);

            tv_Phone.setText(Company_Phone);
            tv_Fax.setText(Company_Fax);
            tv_Email.setText(Company_Email);
            tv_Contact.setText(Company_Contact);
            tv_Address.setText(Company_Address);


        *//*    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });*//*
            builder.create();
            builder.show();

        } */

        public void dialog(String Company_Address, String Company_Phone, String Company_Fax, String Company_Email, String Company_Contact) {


           /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_company_info, null);
            builder.setView(dialogView).setCancelable(true);*/


            Dialog dialogView = new Dialog(context);
            dialogView.setContentView(R.layout.dialog_company_info);

            if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
                dialogView.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.style_custom_dialog_background));
            } else {
                dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            dialogView.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations; //Setting the animations to dialog

            TextView tv_Phone, tv_Fax, tv_Email, tv_Contact, tv_Address;
            ImageView closebtn;
            tv_Phone = dialogView.findViewById(R.id.Company_Phone_dialog);
            tv_Fax = dialogView.findViewById(R.id.Company_Fax_dialog);
            tv_Email = dialogView.findViewById(R.id.Company_Email_dialog);
            tv_Contact = dialogView.findViewById(R.id.Company_Contact_dialog);
            tv_Address = dialogView.findViewById(R.id.Company_Address_dialog);
            closebtn = dialogView.findViewById(R.id.closebtn);

            tv_Phone.setText(Company_Phone);
            tv_Fax.setText(Company_Fax);
            tv_Email.setText(Company_Email);
            tv_Contact.setText(Company_Contact);
            tv_Address.setText(Company_Address);


            // Close Button
            closebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogView.dismiss();
                }
            });
            dialogView.show();


        }
    }

    public void RefreshPicassoCache(String path) {
        Picasso.get().invalidate("file:///" + path);
    }
}

package com.zasa.superduper.CompanyListCategoryWiseS;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import com.bumptech.glide.Glide;
import com.zasa.superduper.CategoryListCompanyWise.CategoryListActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.SQLite.CartSQLiteDB;

import java.util.ArrayList;

public class CompanyListCategoryWiseAdapter extends RecyclerView.Adapter<CompanyListCategoryWiseAdapter.ViewHolder> {
    String code ;
    //ArrayList<Model> models = new ArrayList<>();
    Context context;
    ArrayList<BranchListCategoryWiseApiModel> branchListCategoryWiseApiModelArrayList = new ArrayList<>();

    /*public CompanyListCategoryWiseAdapter(ArrayList<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }*/

    public CompanyListCategoryWiseAdapter(Context context, ArrayList<BranchListCategoryWiseApiModel> branchListCategoryWiseApiModelArrayList) {
        this.context = context;
        this.branchListCategoryWiseApiModelArrayList = branchListCategoryWiseApiModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_company_list_category_wise_item, parent, false);
        return new CompanyListCategoryWiseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String Branch_Codee = branchListCategoryWiseApiModelArrayList.get(position).getBranch_Code();
        String Branch_Name = branchListCategoryWiseApiModelArrayList.get(position).getBranch_Name();
        String Branch_Address = branchListCategoryWiseApiModelArrayList.get(position).getBranch_Address();
        String Company_Code = branchListCategoryWiseApiModelArrayList.get(position).getCompany_Code();
        String Company_Category_Code = branchListCategoryWiseApiModelArrayList.get(position).getCompany_Category_Code();
        float Max_Discount_Limit = branchListCategoryWiseApiModelArrayList.get(position).getMax_Discount_Limit();


        ////String Branch_Codee = Branch_Code.substring(0,Branch_Code.length() - 2);
        ////String imgUrl = "http://apis.loyaltybunch.com/Company_Logos/"+Branch_Codee+".png";
        String imgUrl = "http://apis.loyaltybunch.com/Company_Logos/" + Company_Code + ".png";

        holder.tv_title.setText(Branch_Name);

        if (TextUtils.isEmpty("" + Max_Discount_Limit) || Max_Discount_Limit == 0) {
            holder.lay_discount.setVisibility(View.INVISIBLE);
        } else {
            holder.tv_dis.setText("" + Max_Discount_Limit);
        }


        if (Branch_Codee != null) {

            Glide.with(context)
                    .load(imgUrl)
                    .centerInside()
                    //.fitCenter()
                    .placeholder(R.drawable.noimgg)//onimage
                    //.diskCacheStrategy(DiskCacheStrategy.NONE)
                    //.skipMemoryCache(true)
                    .into(holder.imageView);

            /*Picasso.get().load(img).fit().centerInside()
                    .placeholder(R.drawable.noimgg)
                    .error(R.drawable.noimgg)
                    //.resize(120, 80)
                    //.memoryPolicy(MemoryPolicy.NO_CACHE)
                    //.networkPolicy(NetworkPolicy.NO_CACHE)
                    //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    //.networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .into(holder.imageView);*/

        } else {
            holder.imageView.setImageResource(R.drawable.noimgg);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryListActivity.class);
                intent.putExtra("Company_Category_Code", Company_Category_Code);
                intent.putExtra("company_code", Company_Code);
                intent.putExtra("Title", Branch_Name);
                intent.putExtra("imgUrl", imgUrl);
                intent.putExtra("Branch_Code", Branch_Codee);


                if (holder.checkBranchCodeExistInSQLite(Branch_Codee)) {////TRUE
                    context.startActivity(intent);
                } else {

                    holder.dialog();
                }


                //context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return branchListCategoryWiseApiModelArrayList.size();
    }


    //search RCV item
    public void filteredListMethod(ArrayList<BranchListCategoryWiseApiModel> filteredList) {
        branchListCategoryWiseApiModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_title, tv_dis;
        View lay_discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.item_title);
            tv_dis = itemView.findViewById(R.id.item_discount);
            imageView = itemView.findViewById(R.id.img_item);
            lay_discount = itemView.findViewById(R.id.lay_discount);
        }


        public void dialog() {


//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_company_info, null);
//            builder.setView(dialogView).setCancelable(true);


            Dialog dialogView = new Dialog(context);
            dialogView.setCanceledOnTouchOutside(false);
            dialogView.setContentView(R.layout.dialog_different_vendor_error);

            if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
                dialogView.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.style_custom_dialog_background));
            } else {
                dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            dialogView.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations; //Setting the animations to dialog

            TextView tv_Phone, tv_Fax, tv_Email, tv_Contact, tv_Address;
            ImageView closebtn;
            Button btn_ok;


            closebtn = dialogView.findViewById(R.id.closebtn);
            btn_ok = dialogView.findViewById(R.id.btn_ok);


            // Close Button
            closebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogView.dismiss();
                }
            });

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogView.dismiss();
                }
            });
            dialogView.show();


        }

        public boolean checkBranchCodeExistInSQLite(String Branch_Codee) {


            CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);





            //check data exist in DB//
            Cursor cursorr = cartSQLiteDB.getAllData();
            if (cursorr.getCount() == 0) {
                // Toast.makeText(this, "Nothing Found", Toast.LENGTH_LONG).show();
                return true;
            }


            Cursor cursor = cartSQLiteDB.getBranchCode(Branch_Codee);
            //get data from first row in sqlite
            if (cursor.moveToFirst())
            {
                do
                {
                    code=cursor.getString(9);
                } while (cursor.moveToNext());
            }

            if (Branch_Codee.equals(code)){
                    return true;
                }else {
                    return false;
                }

        }


    }
}

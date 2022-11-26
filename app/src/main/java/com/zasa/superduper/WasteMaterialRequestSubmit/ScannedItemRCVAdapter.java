package com.zasa.superduper.WasteMaterialRequestSubmit;

import static com.zasa.superduper.WasteMaterialRequestSubmit.SaveEarthActivity.totalPoints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.zasa.superduper.R;
import com.zasa.superduper.SQLite.ScannedItemSqliteDB;

import java.util.ArrayList;


public class ScannedItemRCVAdapter extends RecyclerView.Adapter<ScannedItemRCVAdapter.ViewHolder> {
    ArrayList<ScannedItemListModel> scannedItemListModelArrayList;
    Context context;

    public ScannedItemRCVAdapter(ArrayList<ScannedItemListModel> scannedItemListModelArrayList, Context context) {
        this.scannedItemListModelArrayList = scannedItemListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScannedItemRCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rcv_scanneditem_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScannedItemRCVAdapter.ViewHolder holder, int position) {

        String itemCode = scannedItemListModelArrayList.get(position).getMaterial_Code();
        String itemName = scannedItemListModelArrayList.get(position).getMaterial_Name();
        String itemPoints = scannedItemListModelArrayList.get(position).getOne_Material_LB_Point();
        String itemQty = scannedItemListModelArrayList.get(position).getMaterial_Qty_Request();

        holder.tv_itemName.setText(itemName);
        holder.tv_itemPoints.setText(itemPoints);
        holder.tv_item_X_qty.setText(itemQty);
        holder.tv_quantity.setText(itemQty);


        holder.minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int qtyNoo = Integer.parseInt(holder.tv_quantity.getText().toString());

                if (qtyNoo > 1) {
                    int b = qtyNoo;
                    qtyNoo = qtyNoo - 1;
                    holder.tv_quantity.setText(qtyNoo + "");
                    holder.tv_item_X_qty.setText(qtyNoo + "");

                    String stTotalPoints = totalPoints.getText().toString();

                    float newTotalPoints = (Float.parseFloat(stTotalPoints)) - (Float.parseFloat(itemPoints) * (b - qtyNoo));
                    //  Toast.makeText(context, "" + tvTotal + "\n" + pAmount + "\n" + qty + "\n" + newTotal, Toast.LENGTH_SHORT).show();
                    totalPoints.setText("" + newTotalPoints);


                    String qty = holder.tv_quantity.getText().toString();
                    float ft_LBItemPoints = (Float.parseFloat(itemPoints) * (Integer.parseInt(qty)));

                    ///update Qty And LB_points in local DB
                    holder.updateQtyAndLB_points(itemCode, Integer.parseInt(qty), ft_LBItemPoints);
                    // Toast.makeText(context, "" + productNetTotal, Toast.LENGTH_SHORT).show();


                } else {
                    holder.tv_quantity.setText("1");

                }


            }
        });

        holder.plus_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int qtyNoo = Integer.parseInt(holder.tv_quantity.getText().toString());

                if (qtyNoo < 0) {
                    holder.tv_quantity.setText("1");
                    //Toast.makeText(context, "Can't buy more than 10 items!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    int b = qtyNoo;
                    qtyNoo = qtyNoo + 1;

                    holder.tv_quantity.setText(qtyNoo + "");
                    holder.tv_item_X_qty.setText(qtyNoo + "");

                    String stTotalPoints = totalPoints.getText().toString();

                    float newTotalPoints = (Float.parseFloat(stTotalPoints)) + (Float.parseFloat(itemPoints) * (qtyNoo - b));
                    //Toast.makeText(context, "" + tvTotal + "\n" + pAmount + "\n" + qty + "\n" + newTotal, Toast.LENGTH_SHORT).show();
                    totalPoints.setText("" + newTotalPoints);


                    String qty = holder.tv_quantity.getText().toString();
                    float ft_LBItemPoints = (Float.parseFloat(itemPoints) * (Integer.parseInt(qty)));

                    //Toast.makeText(context, ""+ft_LBItemPoints, Toast.LENGTH_SHORT).show();

                    ///update Qty And LB_points in local DB
                    holder.updateQtyAndLB_points(itemCode, Integer.parseInt(qty), ft_LBItemPoints);
                    // Toast.makeText(context, "" + productNetTotal, Toast.LENGTH_SHORT).show();

                }


            }
        });

        holder.tx_delProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /////Adjust values if user remove product
                String total = totalPoints.getText().toString();
                String qty = holder.tv_quantity.getText().toString();

                float newTotal = (Float.parseFloat(total)) - (Float.parseFloat(itemPoints) * Integer.parseInt(qty));

                totalPoints.setText("" + newTotal);

                holder.DeleteProduct(itemCode);//delete item

                scannedItemListModelArrayList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();

                if (scannedItemListModelArrayList.size() == 0) {
                    totalPoints.setText("0.0");
                    return;
                }
                //  Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                holder.lay_swipeablelay.close(true);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (scannedItemListModelArrayList != null) {
            return scannedItemListModelArrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_itemName, tv_itemPoints, tv_item_X_qty, tv_quantity;
        ImageView minus_img, plus_img, img_deleteitem, iv_ItemImg;
        SwipeRevealLayout lay_swipeablelay;
        TextView tx_delProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lay_swipeablelay = itemView.findViewById(R.id.lay_swipeablelay);
            tx_delProduct = itemView.findViewById(R.id.tx_delProduct);

            minus_img = itemView.findViewById(R.id.scan_minus_img);
            plus_img = itemView.findViewById(R.id.scan_plus_img);
            tv_itemName = itemView.findViewById(R.id.itemName);
            tv_itemPoints = itemView.findViewById(R.id.itemPoints);
            tv_item_X_qty = itemView.findViewById(R.id.x_itemQty);
            tv_quantity = itemView.findViewById(R.id.tv_Scan_quantity);

        }


        public void updateQtyAndLB_points(String material_code, int newQty, float Material_LB_Points) {

           // float newMaterial_LB_Points = (Material_LB_Points * newQty);

            ScannedItemSqliteDB sqLiteDBHelper = new ScannedItemSqliteDB(context);
            boolean isUpdate = sqLiteDBHelper.updateQtyAndLB_points(material_code, newQty + "", Material_LB_Points + "");

            if (isUpdate == true) {


            //    Toast.makeText(context, "Qty And LB_points updated Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Data not Found", Toast.LENGTH_SHORT).show();
            }

        }

        public void DeleteProduct(String itemCode) {

            ScannedItemSqliteDB sqLiteDBHelper = new ScannedItemSqliteDB(context);
            //  String id = cartListModelArrayList.get(position).getId();
            boolean check = sqLiteDBHelper.deleteItemData(itemCode);
            if (check == true) {
                 Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Product not found", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

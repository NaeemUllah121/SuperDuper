package com.zasa.superduper.Cart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Base64;
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
import com.zasa.superduper.SQLite.CartSQLiteDB;

import java.util.ArrayList;

import static com.zasa.superduper.Cart.CartActivity.tv_Total;

public class CartListRCVAdapter extends RecyclerView.Adapter<CartListRCVAdapter.ViewHolder> {
    int b = 0;

    ArrayList<CartListModel> cartListModelArrayList;
    Context context;

    public CartListRCVAdapter(ArrayList<CartListModel> cartListModelArrayList, Context context) {
        this.cartListModelArrayList = cartListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartListRCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rcv_cart_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListRCVAdapter.ViewHolder holder, int position) {
        CartListModel cartListModel = cartListModelArrayList.get(position);
        String id = cartListModelArrayList.get(position).getId();
        String itemCode = cartListModelArrayList.get(position).getItemCode();
        String name = cartListModelArrayList.get(position).getItemName();
        String ItemPrice = cartListModelArrayList.get(position).getItemPrice();
        String itemOldPrice = cartListModelArrayList.get(position).getItemOldPrice();
        String itemUnit = cartListModelArrayList.get(position).getItemUnit();
        String itemImg = cartListModelArrayList.get(position).getItemImg();
        int qty = Integer.parseInt(cartListModelArrayList.get(position).getItemQty());


        holder.itemname.setText(name);
        holder.itemprice.setText("" + ItemPrice);
        holder.tv_quantity.setText(qty + "");

        if(TextUtils.isEmpty(itemUnit)){
            holder.itemUnit.setVisibility(View.GONE);
        }else {
            holder.itemUnit.setText(itemUnit);

        }

        if (Float.parseFloat(itemOldPrice) > 0) {
            holder.itemOldPrice.setText("Rs" + itemOldPrice);
            holder.itemOldPrice.setPaintFlags(holder.itemOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            holder.itemOldPrice.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(itemImg)) {
            holder.iv_ItemImg.setImageResource(R.drawable.onimage);
        } else {
            byte[] imageBytes = Base64.decode(itemImg, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.iv_ItemImg.setImageBitmap(decodedImage);

        }


        holder.cart_minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int qtyNoo = Integer.parseInt(holder.tv_quantity.getText().toString());

                if (qtyNoo > 1) {
                    int b = qtyNoo;
                    qtyNoo = qtyNoo - 1;
                    holder.tv_quantity.setText(qtyNoo + "");

                    String tvTotal = tv_Total.getText().toString();
                    //String pAmount = cartListModelArrayList.get(position).getItemPrice();
                    String qty = holder.tv_quantity.getText().toString();

                    float newTotal = (Float.parseFloat(tvTotal)) - (Float.parseFloat(ItemPrice) * (b - qtyNoo));
                    //  Toast.makeText(context, "" + tvTotal + "\n" + pAmount + "\n" + qty + "\n" + newTotal, Toast.LENGTH_SHORT).show();
                    tv_Total.setText("" + newTotal);

                    ///update item qty in local DB
                    holder.updateQty(itemCode, qty);

                    ///update item net total in local DB
                    float productTotal = (Float.parseFloat(itemOldPrice) * (Integer.parseInt(holder.tv_quantity.getText().toString())));
                    float productNetTotal = (Float.parseFloat(ItemPrice) * (Integer.parseInt(holder.tv_quantity.getText().toString())));
                    holder.updateProductNetAmountAndProductAmount(itemCode, productTotal + "", productNetTotal + "");
                   // Toast.makeText(context, "" + productNetTotal, Toast.LENGTH_SHORT).show();


                } else {
                    holder.tv_quantity.setText("1");

                }


            }
        });

        holder.cart_plus_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int qtyNoo = Integer.parseInt(holder.tv_quantity.getText().toString());

                if (qtyNoo > 9) {
                    // holder.tv_quantity.setText("1");
                    Toast.makeText(context, "Can't buy more than 10 items!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    int b = qtyNoo;
                    qtyNoo = qtyNoo + 1;

                    holder.tv_quantity.setText(qtyNoo + "");
                    String tvTotal = tv_Total.getText().toString();
                    String qty = holder.tv_quantity.getText().toString();
                    // String pAmount = cartListModelArrayList.get(position).getItemPrice();
                    float newTotal = (Float.parseFloat(tvTotal)) + (Float.parseFloat(ItemPrice) * (qtyNoo - b));
                    //Toast.makeText(context, "" + tvTotal + "\n" + pAmount + "\n" + qty + "\n" + newTotal, Toast.LENGTH_SHORT).show();
                    tv_Total.setText("" + newTotal);

                    ///update item qty in local DB
                    holder.updateQty(itemCode, qty);

                    ///update item net total in local DB
                    float productTotal = (Float.parseFloat(itemOldPrice) * (Integer.parseInt(holder.tv_quantity.getText().toString())));
                    float productNetTotal = (Float.parseFloat(ItemPrice) * (Integer.parseInt(holder.tv_quantity.getText().toString())));
                    holder.updateProductNetAmountAndProductAmount(itemCode, productTotal + "", productNetTotal + "");

                    // Toast.makeText(context, "" + productNetTotal, Toast.LENGTH_SHORT).show();

                }


            }
        });

        holder.img_deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                //alertDialog.setTitle("Alert!");
                alertDialog.setMessage("Remove this product?");

                alertDialog.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        /////Adjust values if user remove product from cart
                        String total = tv_Total.getText().toString();
                        //  String pAmount = cartListModelArrayList.get(position).getItemPrice();
                        String qty = holder.tv_quantity.getText().toString();


                        float newTotal = (Float.parseFloat(total)) - (Float.parseFloat(ItemPrice) * Integer.parseInt(qty));

                        //  Toast.makeText(context, "" + total + "\n" + ItemPrice + "\n" + qty + "\n" + newTotal, Toast.LENGTH_SHORT).show();

                        tv_Total.setText("" + newTotal);

                        holder.DeleteProduct(itemCode);//delete item

                        cartListModelArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();

                        if (cartListModelArrayList.size() == 0) {
                            tv_Total.setText("0.0");
                            return;
                        }

                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                alertDialog.create();
                alertDialog.show();

            }
        });

        holder.tx_delCartProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     /////Adjust values if user remove product from cart
                String total = tv_Total.getText().toString();
                //String pAmount = cartListModelArrayList.get(position).getItemPrice();
                String qty = holder.tv_quantity.getText().toString();

                float newTotal = (Float.parseFloat(total)) - (Float.parseFloat(ItemPrice) * Integer.parseInt(qty));

                tv_Total.setText("" + newTotal);

                holder.DeleteProduct(itemCode);//delete item

                cartListModelArrayList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();

                if (cartListModelArrayList.size() == 0) {
                    tv_Total.setText("0.0");
                    return;
                }*/

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                //alertDialog.setTitle("Alert!");
                alertDialog.setMessage("Remove this product?");

                alertDialog.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        /////Adjust values if user remove product from cart
                        String total = tv_Total.getText().toString();
                        //  String pAmount = cartListModelArrayList.get(position).getItemPrice();
                        String qty = holder.tv_quantity.getText().toString();


                        float newTotal = (Float.parseFloat(total)) - (Float.parseFloat(ItemPrice) * Integer.parseInt(qty));

                        //  Toast.makeText(context, "" + total + "\n" + ItemPrice + "\n" + qty + "\n" + newTotal, Toast.LENGTH_SHORT).show();

                        tv_Total.setText("" + newTotal);

                        holder.DeleteProduct(itemCode);//delete item

                        cartListModelArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();

                        if (cartListModelArrayList.size() == 0) {
                            tv_Total.setText("0.0");
                            return;
                        }

                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                alertDialog.create();
                alertDialog.show();
                holder.lay_swipeablelay.close(true);
                //Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                //Snackbar.make(v,"Deleted", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        if (cartListModelArrayList != null) {
            return cartListModelArrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemprice, itemOldPrice, itemname, itemUnit, tv_quantity, tx_delCartProduct;
        ImageView cart_minus_img, cart_plus_img, img_deleteitem, iv_ItemImg;
        SwipeRevealLayout lay_swipeablelay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lay_swipeablelay = itemView.findViewById(R.id.lay_swipeablelay);
            tx_delCartProduct = itemView.findViewById(R.id.tx_delCartProduct);

            cart_minus_img = itemView.findViewById(R.id.cart_minus_img);
            cart_plus_img = itemView.findViewById(R.id.cart_plus_img);
            img_deleteitem = itemView.findViewById(R.id.img_deleteitem);
            itemname = itemView.findViewById(R.id.itemname);
            itemprice = itemView.findViewById(R.id.itemprice);
            itemOldPrice = itemView.findViewById(R.id.itemOldPrice);
            itemUnit = itemView.findViewById(R.id.itemUnit);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            iv_ItemImg = itemView.findViewById(R.id.iv_ItemImg);

        }

        public void DeleteProduct(String itemCode) {

            CartSQLiteDB sqLiteDBHelper = new CartSQLiteDB(context);
            //  String id = cartListModelArrayList.get(position).getId();
            boolean check = sqLiteDBHelper.deleteData(itemCode);
            if (check == true) {
               // Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Product not found", Toast.LENGTH_SHORT).show();
            }
        }

        public void updateQty(String itemCode, String qty) {
            CartSQLiteDB sqLiteDBHelper = new CartSQLiteDB(context);
            /*         String id = cartListModelArrayList.get(position).getId();*/
            boolean isUpdate = sqLiteDBHelper.updateQty(itemCode, qty);
            if (isUpdate == true) {
                //   Toast.makeText(context, "Qty updated Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, " Data not Found", Toast.LENGTH_SHORT).show();
            }
        }

        public void updateProductNetAmountAndProductAmount(String itemCode, String ProductAmount, String ProductNetAmount) {
            CartSQLiteDB sqLiteDBHelper = new CartSQLiteDB(context);
            /*         String id = cartListModelArrayList.get(position).getId();*/
            boolean isUpdate = sqLiteDBHelper.updateProductNetAmount(itemCode, ProductAmount, ProductNetAmount);
            if (isUpdate == true) {
                //Toast.makeText(context, "ProductNetAmount updated Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Data not Found", Toast.LENGTH_SHORT).show();
            }
        }


    }
}


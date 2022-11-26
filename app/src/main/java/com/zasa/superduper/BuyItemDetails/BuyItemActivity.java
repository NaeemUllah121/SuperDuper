package com.zasa.superduper.BuyItemDetails;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.zasa.superduper.CategoryListCompanyWise.CategoryListActivity.Branch_Code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fuzzproductions.ratingbar.RatingBar;
import com.google.android.material.snackbar.Snackbar;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;
import com.zasa.superduper.Cart.CartActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.SQLite.CartSQLiteDB;
import com.zasa.superduper.SubmitGeneralOrder.CheckoutActivity;
import com.zasa.superduper.databinding.ActivityBuyItemBinding;
import com.zasa.superduper.databinding.ItemCustomFixedSizeLayout3Binding;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import kr.co.prnd.readmore.ReadMoreTextView;
import me.relex.circleindicator.CircleIndicator2;

public class BuyItemActivity extends AppCompatActivity {

    private static final String TAG = "checkkkk";
    ProgressDialog progressDialog;
    Context context;

    RatingBar ratingBar;
    ScaleRatingBar scaleRatingBar;
    float item_Net_Amount, item_Amount;

    ImageView imageView;
    TextView tv_productName, tv_productPrice, tv_productOldPrice, tv_productAvailable, /*tv_productDescription,*/
            tv_PQty, tv_buyItemUnit;
    int itemQty;

    String productImg, productName, productCode, CompanyCode, ClientCode, new_Item_Sale_Price, Item_Description, Item_Unit_Name;
    float productPrice;
    //public static int cartTotalItemQty;
    TextView tv_CartTotalItemQty;
    View parentView;

    ReadMoreTextView tv_productDescription;

    ActivityBuyItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityBuyItemBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        setContentView(R.layout.activity_buy_item);

        context = BuyItemActivity.this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");


        mRatingBar();

        imageSlider();




        ////these values are coming from childItemAdapter
        productImg = getIntent().getStringExtra("itemImg");
        CompanyCode = getIntent().getStringExtra("Company_Code");
        ClientCode = getIntent().getStringExtra("Client_Code");
        productCode = getIntent().getStringExtra("ItemCode");
        productName = getIntent().getStringExtra("Item_Name");
        new_Item_Sale_Price = getIntent().getStringExtra("new_Item_Sale_Price");
        productPrice = getIntent().getFloatExtra("Item_Sale_Price", 0);
        int Item_IsActive = getIntent().getIntExtra("Item_IsActive", 0);
        Item_Unit_Name = getIntent().getStringExtra("Item_Unit_Name");
        Item_Description = getIntent().getStringExtra("Item_Description");

        imageView = findViewById(R.id.item_img);
        tv_productName = findViewById(R.id.tv_productName);
        tv_productPrice = findViewById(R.id.tv_productPrice);
        tv_productOldPrice = findViewById(R.id.tv_productOldPrice);
        tv_productAvailable = findViewById(R.id.tv_productAvailable);
        tv_PQty = findViewById(R.id.tv_Pquantity);
        parentView = findViewById(R.id.buyItemLay);
        tv_buyItemUnit = findViewById(R.id.buyItemUnit);

        ////scrollable TextView
        tv_productDescription = findViewById(R.id.tv_productDescription);
        tv_productDescription.setMovementMethod(new ScrollingMovementMethod());
        //set item description
        tv_productDescription.setText(Item_Description);

        //set Item_Unit_Name
        if (TextUtils.isEmpty(Item_Unit_Name)) {
            tv_buyItemUnit.setVisibility(View.GONE);
        } else {
            tv_buyItemUnit.setText(Item_Unit_Name);

        }


        ///////////set image
        if (TextUtils.isEmpty(productImg)) {
            imageView.setImageResource(R.drawable.onimage);
        } else {
            byte[] imageBytes = Base64.decode(productImg, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(decodedImage);
        }
        //set availability
        if (Item_IsActive == 1) {
            tv_productAvailable.setText("Available");
            tv_productAvailable.setTextColor(Color.parseColor("#FF4CAF50"));
        } else {
            tv_productAvailable.setText("Out of Stock");
            tv_productAvailable.setTextColor(Color.parseColor("#FFF44336"));


        }


        //////////////set name
        tv_productName.setText(productName);

        /////////////set discounted price and without discount price
        if (Float.parseFloat(new_Item_Sale_Price) > 0 && !new_Item_Sale_Price.equals(productPrice + "")) {
            tv_productPrice.setText("" + new_Item_Sale_Price);
            tv_productOldPrice.setText("" + productPrice);
            tv_productOldPrice.setPaintFlags(tv_productOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            tv_productPrice.setText("" + productPrice);
            tv_productOldPrice.setText("" + productPrice);
            tv_productOldPrice.setVisibility(View.GONE);
        }


        //////Set total item qty on cart badge
        tv_CartTotalItemQty = findViewById(R.id.cartTotalItemQty);
        getItemQtyFromSQLite();


    }

    private void imageSlider() {

        // Java
        ImageCarousel carousel = findViewById(R.id.carousel4);
        CircleIndicator2 circleIndicator2 = findViewById(R.id.custom_indicator);
        // carousel.setIndicator(circleIndicator2);

        // Register lifecycle. For activity this will be lifecycle/getLifecycle() and for fragments it will be viewLifecycleOwner/getViewLifecycleOwner().
        carousel.registerLifecycle(getLifecycle());
        List<CarouselItem> list = new ArrayList<>();

        // Image URL with caption
        list.add(
                new CarouselItem(
                        "https://d1iv6qgcmtzm6l.cloudfront.net/categories/c6mgpFdxyx4CESqRWBeDe2fNPKn3SsP7KuwXSbv8.png",
                        "Photo by Aaron Wu on Unsplash"
                )
        );
        list.add(
                new CarouselItem(
                        "https://d1iv6qgcmtzm6l.cloudfront.net/products/lg_wnH2g3IBI2xASPwF3rbgF7NVpLxU7t8ZQGa6u98y.png",
                        "Photo by Aaron Wu on Unsplash"
                )
        );
        list.add(
                new CarouselItem(
                        "https://d1iv6qgcmtzm6l.cloudfront.net/products/lg_XS7KmaNDyytEBtnmWLfD6g0UNP8wDxdoyBYmBtKs.png",
                        "Photo by Aaron Wu on Unsplash"
                )
        );
        carousel.setData(list);

        carousel.setCarouselListener(new CarouselListener() {
            @Override
            public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup parent) {
                return ItemCustomFixedSizeLayout3Binding.inflate(layoutInflater, parent, false);

            }

            @Override
            public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {

                // Cast the binding to the returned view binding class of the onCreateViewHolder() method.
                ItemCustomFixedSizeLayout3Binding currentBinding = (ItemCustomFixedSizeLayout3Binding) viewBinding;
                // Do the bindings...
                currentBinding.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                // setImage() is an extension function to load image to an ImageView using CarouselItem object. We need to provide current CarouselItem data and the place holder Drawable or drawable resource id to the function. placeholder parameter is optional.
                Utils.setImage(currentBinding.imageView, carouselItem, R.drawable.noimgg);
                // Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(int i, @NonNull CarouselItem carouselItem) {
                String url = list.get(i).getImageUrl();
                Toast.makeText(context, "" + carouselItem.getImageUrl(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

            }
        });


    }


    private void mRatingBar() {
//        ratingBar = findViewById(R.id.fuzz_rating_bar);
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                Toast.makeText(BuyItemActivity.this, "rating "+ rating, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // get values and then displayed in a toast
//        String totalStars = "Total Stars:: " + RatingBar.getNumStars();
//        RatingBar.setRating(4.0f); // put the rating number you are receiving through json here.
//        String rating = "Rating :: " + RatingBar.getRating();
//        Toast.makeText(context, totalStars + "\n" + rating, Toast.LENGTH_LONG).show();
//
//        RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                ratingBar.setRating(rating);
//            }
//        });
        scaleRatingBar = findViewById(R.id.simpleRatingBar);
        scaleRatingBar.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(BaseRatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(BuyItemActivity.this, "rating "+ rating, Toast.LENGTH_SHORT).show();

            }
        });


    }

    //////////Add Cart data into SQLite
    public void btn_AddToCart(View view) {


        itemQty = Integer.parseInt(tv_CartTotalItemQty.getText().toString());
        if (itemQty >= 10) {
            Snackbar.make(parentView, "Can't buy more than 10 items!", LENGTH_LONG).show();
            return;
        }

        //  addCartItemInSQLite();
        boolean checkkk;
        checkkk = checkItemExistenceInSQLite();//check that item is already exist in Local Db or not

        if (checkkk == false) {
            String pName = tv_productName.getText().toString();
            String pUnit = tv_buyItemUnit.getText().toString();
            String pPrice = tv_productPrice.getText().toString();
            String pDescription = tv_productDescription.getText().toString();
            String pQty = tv_PQty.getText().toString();
            String productOldPrice = tv_productOldPrice.getText().toString();


            item_Net_Amount = Integer.parseInt(pQty) * Float.parseFloat(pPrice);
            item_Amount = Integer.parseInt(pQty) * Float.parseFloat(productOldPrice);


            CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
            boolean check = cartSQLiteDB.insertValue(pName, pUnit, pPrice, pQty, pDescription, productImg, productCode, CompanyCode, Branch_Code, ClientCode, productOldPrice, item_Amount + "", item_Net_Amount + "");

            if (check == true) {
                //   Toast.makeText(context, "Data is inserted", Toast.LENGTH_SHORT).show();

                //set item qty on cart badge
                getItemQtyFromSQLite();
                Log.d(TAG, " btn_AddToCart(View view)  if (checkkk == false)  body-->");
                Snackbar snackbar = Snackbar.make(view, productName + " added to cart", LENGTH_LONG)//LENGTH_INDEFINITE
                        .setDuration(7000)
                        .setAction("Open CART", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(context, CartActivity.class));
                                finish();
                            }
                        });
                snackbar.show();
            } else {
                Toast.makeText(context, "Data is not inserted", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public boolean checkItemExistenceInSQLite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getItemIdData(productCode);
        if (cursor.getCount() == 0) {   //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            // Toast.makeText(this, "Nothing Found", Toast.LENGTH_LONG).show();
            Log.d(TAG, " checkItemExistenceInSQLite() 1st if body-->return false");
            return false;
        }
        int sql_Qty, i = 0;
        while (cursor.moveToNext()) {


            Log.d(TAG, " while (cursor.moveToNext()) --> " + ++i + " -- " + " st_itemCode-->" + "  productCode-->" + productCode);
            // Toast.makeText(context, productCode+" Item already exist!", Toast.LENGTH_SHORT).show();
            sql_Qty = Integer.parseInt(tv_PQty.getText().toString()) + Integer.parseInt(cursor.getString(4));


            deleteSQLiteProduct(productCode);//delete item from sqlite
            addCartItemsInSQLite(sql_Qty + "");//add item in sqlite

            //updateQty( productCode,  sql_Qty+""); //no need this function

            // Toast.makeText(context, sql_Qty + " Item Qty already exist!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, " checkItemExistenceInSQLite() while() if body-->  end " + ++i + " -- ");

        }
        Log.d(TAG, " checkItemExistenceInSQLite() After while() body-->return true");

        return true;

    }

    public void btn_back(View view) {
        finish();
    }

    public void btn_Cart(View view) {
        startActivity(new Intent(context, CartActivity.class));
        finish();
    }

    public void btn_IncrementQty(View view) {

        itemQty = Integer.parseInt(tv_PQty.getText().toString());
        if (itemQty <= 9) {
            itemQty = itemQty + 1;
            tv_PQty.setText(itemQty + "");
            //  Toast.makeText(context, ""+qtyNoo, Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(context, "Can't buy more than 10 items!", Toast.LENGTH_LONG).show();
            Snackbar.make(parentView, "Can't buy more than 10 items!", LENGTH_LONG).show();
        }
    }

    public void btn_decrementQty(View view) {

        itemQty = Integer.parseInt(tv_PQty.getText().toString());

        if (itemQty > 1) {
            itemQty = itemQty - 1;
            tv_PQty.setText(itemQty + "");
        } else {
            tv_PQty.setText("1");
            return;
        }

    }

    public void getItemQtyFromSQLite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getAllData();
        if (cursor.getCount() == 0) {
            //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            //Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        int sql_Qty = 0;
        while (cursor.moveToNext()) {
            sql_Qty = sql_Qty + Integer.parseInt(cursor.getString(4));
        }

        //  Toast.makeText(this, "sql_Qty " + sql_Qty, Toast.LENGTH_SHORT).show();
        // tv_CartTotalItemQty = findViewById(R.id.cartTotalItemQty);

        if (sql_Qty > 0) {
            tv_CartTotalItemQty.setVisibility(View.VISIBLE);
            tv_CartTotalItemQty.setText("" + sql_Qty);
        } else {
            tv_CartTotalItemQty.setVisibility(View.GONE);
        }


    }

    public void updateQty(String itemCode, String qty) {

        CartSQLiteDB sqLiteDBHelper = new CartSQLiteDB(context);
        boolean isUpdate = sqLiteDBHelper.updateQty(itemCode, qty);
        if (isUpdate == true) {
            getItemQtyFromSQLite();

            Snackbar snackbar = Snackbar.make(parentView, productName + " added to cart", LENGTH_LONG)//LENGTH_INDEFINITE
                    //   .setAnchorView(parentView)
                    .setDuration(7000)
                    .setAction("Open CART", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(context, CartActivity.class));
                            finish();
                        }
                    });
            snackbar.show();

            // Toast.makeText(context, "Qty updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data not Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteSQLiteProduct(String productCode) {

        CartSQLiteDB sqLiteDBHelper = new CartSQLiteDB(context);
        boolean check = sqLiteDBHelper.deleteData(productCode);
        if (check == true) {
            Log.d(TAG, " checkItemExistenceInSQLite() calls-->deleteSQLiteProduct()");
            //Toast.makeText(context, productCode + " Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data is not found", Toast.LENGTH_SHORT).show();
        }


    }

    public void addCartItemsInSQLite(String newQty) {

        String pName = tv_productName.getText().toString();
        //String pUnit =  tv_productUnit.getText().toString();
        String pPrice = tv_productPrice.getText().toString();
        String pDescription = tv_productDescription.getText().toString();
        String pQty = tv_PQty.getText().toString();
        String productOldPrice = tv_productOldPrice.getText().toString();

        item_Net_Amount = Integer.parseInt(newQty) * Float.parseFloat(tv_productPrice.getText().toString());
        item_Amount = Integer.parseInt(newQty) * Float.parseFloat(tv_productOldPrice.getText().toString());

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        boolean check = cartSQLiteDB.insertValue(pName, "", pPrice, newQty, pDescription, productImg, productCode, CompanyCode, Branch_Code, ClientCode, productOldPrice, item_Amount + "", item_Net_Amount + "");

        if (check == true) {
            //Toast.makeText(context, "Data is inserted", Toast.LENGTH_SHORT).show();
            Log.d(TAG, " checkItemExistenceInSQLite() calls-->addCartItemsInSQLite()");
            //set item qty on cart badge
            getItemQtyFromSQLite();

            //Snackbar snackbar = Snackbar.make(view,productName+" added to cart",Snackbar.LENGTH_LONG)//LENGTH_INDEFINITE
            Snackbar snackbar = Snackbar.make(parentView, productName + " added to cart", LENGTH_LONG)//LENGTH_INDEFINITE
                    //   .setAnchorView(parentView)
                    .setDuration(7000)
                    .setAction("Open CART", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(context, CartActivity.class));
                            finish();
                        }
                    });
            snackbar.show();
        } else {
            Toast.makeText(context, "Data is not inserted", Toast.LENGTH_SHORT).show();
        }

    }

    public void btn_BuyNow(View view) {


        itemQty = Integer.parseInt(tv_CartTotalItemQty.getText().toString());
        if (itemQty >= 10) {
            Snackbar.make(parentView, "Can't buy more than 10 items!", LENGTH_LONG).show();
            return;
        }

        boolean checkkk;
        checkkk = checkBuyNowItemExistenceInSQLite();//check that item is already exist in Local Db or not

        if (checkkk == false) {
            String pName = tv_productName.getText().toString();
            String pUnit = tv_buyItemUnit.getText().toString();
            String pPrice = tv_productPrice.getText().toString();
            String pDescription = tv_productDescription.getText().toString();
            String pQty = tv_PQty.getText().toString();
            String productOldPrice = tv_productOldPrice.getText().toString();

//             pUnit ="KG";
//             pPrice ="1000";
//             productOldPrice = "1500";

            item_Net_Amount = Integer.parseInt(pQty) * Float.parseFloat(pPrice);
            item_Amount = Integer.parseInt(pQty) * Float.parseFloat(productOldPrice);


            CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
            boolean check = cartSQLiteDB.insertValue(pName, pUnit, pPrice, pQty, pDescription, productImg, productCode, CompanyCode, Branch_Code, ClientCode, productOldPrice, item_Amount + "", item_Net_Amount + "");

            if (check == true) {
                //   Toast.makeText(context, "Data is inserted", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(context, CheckoutActivity.class));

                Intent intent = new Intent(context, CheckoutActivity.class);
                intent.putExtra("productCode", productCode);
                intent.putExtra("item_Net_Amount", item_Net_Amount);
                intent.putExtra("BuyNowClick", "BuyNowClick");
                startActivity(intent);

            } else {
                Toast.makeText(context, "Data is not inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkBuyNowItemExistenceInSQLite() {

        CartSQLiteDB cartSQLiteDB = new CartSQLiteDB(context);
        Cursor cursor = cartSQLiteDB.getItemIdData(productCode);
        if (cursor.getCount() == 0) {   //cursor obj upny getCount() method k through check kr rha ha k table mea agr koi records ni ha to showMessage()  call kry or error msg show kery
            // Toast.makeText(this, "Nothing Found", Toast.LENGTH_LONG).show();
            Log.d(TAG, " checkItemExistenceInSQLite() 1st if body-->return false");
            return false;
        }
        int sql_Qty, i = 0;
        while (cursor.moveToNext()) {


            Log.d(TAG, " while (cursor.moveToNext()) --> " + ++i + " -- " + " st_itemCode-->" + "  productCode-->" + productCode);


            // Toast.makeText(context, productCode+" Item already exist!", Toast.LENGTH_SHORT).show();

            //  sql_Qty = Integer.parseInt(tv_PQty.getText().toString()) + Integer.parseInt(cursor.getString(4));


            deleteSQLiteProduct(productCode);//delete item from sqlite
            // addCartItemsInSQLite(sql_Qty + "");//add item in sqlite

            //updateQty( productCode,  sql_Qty+""); //no need this function

            // Toast.makeText(context, sql_Qty + " Item Qty already exist!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, " checkItemExistenceInSQLite() while() if body-->  end " + ++i + " -- ");

        }
        Log.d(TAG, " checkItemExistenceInSQLite() After while() body-->return true");

        return true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        getItemQtyFromSQLite();
    }
}
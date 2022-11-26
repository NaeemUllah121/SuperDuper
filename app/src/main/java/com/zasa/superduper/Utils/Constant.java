package com.zasa.superduper.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Base64;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.zasa.superduper.BuildConfig;
import com.zasa.superduper.R;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Constant {

    public static String senderEmail;
    public static String senderPassword;

    ////check internet connectivity////
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    ////share app/////
    public static void shareApp(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        String st_FName = sharedPreferences.getString("Member_FName", "");
        String st_LName = sharedPreferences.getString("Member_LName", "");

        final String appPackageName = BuildConfig.APPLICATION_ID;
        final String appName = context.getString(R.string.app_name);
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            // String shareMessage = "\nLet me recommend you this application\n\n";
            String shareMessage = st_FName + " " + st_LName + " recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "Share " + appName));
        } catch (Exception e) {
            //e.toString();
        }
    }


        /////generate formatted number//// like 1,300
        public static String getFormattedAmount(double amount, Context context) {
            return NumberFormat.getNumberInstance(Locale.US).format(amount);
        }

    // //method to check for permissions for image pick and ///
    public static boolean checkAllPermissionEnable(Context context) {

        return
               /* ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED &&*/
                ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                                PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    public static String encodeToBase64(Bitmap bitmap, Context context) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imagebytes = stream.toByteArray();
        return Base64.encodeToString(imagebytes, Base64.DEFAULT);
    }

    public static Bitmap decodeToBase64(String input, Context context) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static void imagePicker(int x, int y, Activity activity) {

        Dexter.withContext(activity)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        //Manifest.permission.ACCESS_COARSE_LOCATION,
                        //Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                            if (!checkAllPermissionEnable(activity)) {
                                //showSnackBar("requestForPermission", "Please enable all permission!");
                                Toast.makeText(activity, "Please enable all permissions!", Toast.LENGTH_SHORT).show();

                            } else {
                                ImagePicker.Companion.with(activity)
                                        .crop(x, y)                    //Crop image(Optional), Check Customization for more option
                                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                                        .maxResultSize(1080, 1080)//Final image resolution will be less than 1080 x 1080(Optional)
                                        .saveDir(activity.getExternalFilesDir(Environment.DIRECTORY_DCIM))
                                        .start();
                            }


                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {

                            Toast.makeText(activity, "Please enable all permissions!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                            intent.setData(uri);
                            activity.startActivity(intent);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();


    }


    /////send email/////////
    public static void sendVerificationEmail(Context context, String senderEmail, String senderPass, String receiverEmail, String MailSubject, String Message) {
        JavaMailAPI javaMailAPI = new JavaMailAPI(context, senderEmail, senderPass, receiverEmail, MailSubject, Message);
        javaMailAPI.execute();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ///generate random code/////
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length, Context context) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return builder.toString();
    }


    public static String getCurrentDate(String dateFormat) {

        String dateTime = "";
        if (dateFormat.equals("dateTime")) {
            dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        }
        if (dateFormat.equals("date")) {
            dateTime = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        }

        return dateTime;
    }

    public static long getDayTillEndDate(String st_EndDate) {


        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String st_CurrentDate = getCurrentDate("date");

        Date currentDateValue = null;
        Date endDateValue = null;
        try {
            currentDateValue = dateFormatter.parse(st_CurrentDate);
            endDateValue = dateFormatter.parse(st_EndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long diff = endDateValue.getTime() - currentDateValue.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        //   long days = hours / 24;
        long days = (hours / 24) + 1;

        long Days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if (Days > 0) {
            return Days;
        } else {
            return 0;
        }


    }

}

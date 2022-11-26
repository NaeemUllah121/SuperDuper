package com.zasa.superduper.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YTRetrofitInstance {



    private static YTRetrofitInstance ytRetrofitInstance;
    private static Retrofit retrofitt;
    private static final String BASE_URLL = "https://www.googleapis.com/youtube/v3/";


/*    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())//GsonConverterFactory k through jsonData(jo server sy ata ha) ko java object mea convert kea jata ha or phr is data ko ko App mea use kr skty hain
                    .build();
        }

        return retrofit;
    }*/



    private YTRetrofitInstance() {  //class constructure

        retrofitt = new Retrofit.Builder()
                .baseUrl(BASE_URLL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    ///check that retrofit class obj is initialized or not
    public static synchronized YTRetrofitInstance getInstance() {
        if (ytRetrofitInstance == null) {
            ytRetrofitInstance = new YTRetrofitInstance();
        }
        return ytRetrofitInstance;
    }

    ///this method is used to get api
    public YTAPIInterface getApiInterface() {
        return retrofitt.create(YTAPIInterface.class);
    }


}

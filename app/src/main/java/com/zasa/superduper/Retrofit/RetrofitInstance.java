package com.zasa.superduper.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {



    private static RetrofitInstance retrofitInstance;
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://apis.loyaltybunch.com/api/";




    private RetrofitInstance() {  //class constructure

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    ///check that retrofit class obj is initialized or not
    public static synchronized RetrofitInstance getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitInstance();
        }
        return retrofitInstance;
    }

    ///this method is used to get api
    public APIInterface getApiInterface() {
        return retrofit.create(APIInterface.class);
    }
}

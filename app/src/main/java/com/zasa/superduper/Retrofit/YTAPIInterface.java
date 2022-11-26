package com.zasa.superduper.Retrofit;

import com.zasa.superduper.eLearn.YTModels.ResponseVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YTAPIInterface {


    @GET("playlistItems?part=snippet%2CcontentDetails")
    Call<ResponseVideos> getAllVideos(@Query("maxResults") int max,
                                      @Query("playlistId") String playlistId,
                                      @Query("key") String apiKey);
}



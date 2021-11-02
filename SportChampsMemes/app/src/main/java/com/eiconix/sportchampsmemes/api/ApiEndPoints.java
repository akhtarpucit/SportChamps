package com.eiconix.sportchampsmemes.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoints {
    @GET("get_memes")
    Call<MemesApiResponse> getMemes();
}

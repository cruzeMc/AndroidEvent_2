package com.events.services;

import com.events.authentication.VerifyResponse;
import com.events.model.Category;
import com.events.model.CategoryImage;
import com.events.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Cruze on 12/10/2016.
 */

public interface APIService {
    @GET("/api/landing")
    Call<List<Category>> getCategories();

    @POST("/api/login")
    Call<VerifyResponse> postLogin(@Body Login login);
}

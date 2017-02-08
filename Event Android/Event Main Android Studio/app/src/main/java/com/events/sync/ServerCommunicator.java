package com.events.sync;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cruze on 12/13/2016.
 */

public class ServerCommunicator {
    private static final String BASE_URL = "http://192.168.100.78:9876";
    private static Retrofit retrofit = null;

    public static Retrofit getServerCommunicator() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

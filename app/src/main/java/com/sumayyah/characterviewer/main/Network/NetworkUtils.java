package com.sumayyah.characterviewer.main.Network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sumayyah.characterviewer.BuildConfig;
import com.sumayyah.characterviewer.R;

import java.io.IOException;

import com.sumayyah.characterviewer.main.Model.APIResponse;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by sumayyah on 8/20/16.
 */
public class NetworkUtils {

    private static int MAX_CACHE_SIZE = 10 * 1024 * 1024;
    private Activity activity;

    public NetworkUtils(Activity activity) {
        this.activity = activity;
    }

    public interface APIService {

        @GET(BuildConfig.API_PARAMS)
        public Call<APIResponse> getApiResponse();
    }

    public APIService getAPIService() {
        return getRetrofitBuilder().create(APIService.class);
    }

    public Retrofit getRetrofitBuilder() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(activity.getString(R.string.base_api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        return retrofit;
    }

    public OkHttpClient getClient() {

        Cache cache = new Cache(activity.getCacheDir(), MAX_CACHE_SIZE);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (isNetworkAvailable()) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();

        return client;
    }

    public boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    public boolean doesCacheExist() {
        return activity.getCacheDir() == null;
    }

    public void showFailureDialog() {
        new AlertDialog.Builder(activity)
                .setMessage(activity.getString(R.string.error_dialog_text))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .setCancelable(false)
                .show();
    }
}

package com.sumayyah.characterviewer.Views;

import android.app.Application;
import android.content.Context;

import Model.APIResponse;
import Managers.DataManager;
import Managers.NetworkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sumayyah on 8/16/16.
 */
public class App extends Application implements Callback<APIResponse>, DataManager.ListCompleteListener {

    public static Context Current;
    public static DataManager dataManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Current = this;
        dataManager = DataManager.getInstance();
        Console.log("Set up com.sumayyah.characterviewer.Views.App");
        callApi();
    }

    public void callApi() {
        //TODO check for internet
       //new NetworkManager().executeAPICall(this);
    }


    @Override
    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
        Console.log("App - Response was "+response.toString());
        Console.log("Parsing response body "+response.body().toString());
        Console.log("Hmmm "+response.body().getRelatedTopics().size()+" code "+response.code());
        APIResponse apiResponse = response.body();

        dataManager.populateList(apiResponse, this);
    }

    @Override
    public void onFailure(Call<APIResponse> call, Throwable t) {
        Console.log("App - Failure "+call.toString());
        //TODO error dialog
    }

    @Override
    public void onListPopulateComplete() {
        Console.log("Finished populating list "+App.dataManager.getList().size());
    }
}

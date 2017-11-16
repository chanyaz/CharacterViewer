package com.sumayyah.characterviewer.main.Managers;

import com.sumayyah.characterviewer.main.Console;
import com.sumayyah.characterviewer.main.Model.RelatedTopic;
import com.sumayyah.characterviewer.main.Network.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sumayyah on 8/16/16.
 */
public class NetworkManager {

    private NetworkOpsCompleteListener networkOpsCompleteListener;
    private NetworkUtils networkUtils;

    public NetworkManager(NetworkUtils networkUtils, NetworkOpsCompleteListener networkOpsCompleteListener) {
        this.networkUtils = networkUtils;
        this.networkOpsCompleteListener = networkOpsCompleteListener;
    }

    public void executeAPICall() {

        Call<List<RelatedTopic>> call = networkUtils.getAPIService().getApiResponse();
        call.enqueue(callback);
    }

    private Callback<List<RelatedTopic>> callback = new Callback<List<RelatedTopic>>() {
        @Override
        public void onResponse(Call<List<RelatedTopic>> call, Response<List<RelatedTopic>> response) {

            Console.log("NetworkManager", "Completed call, got "+response.body().size()+ " items");
            if(response.body().size() <= 0) {
                networkUtils.showFailureDialog();
                return;
            }
            DataManager.getInstance().populateList(response.body(), listCompleteListener);
        }

        @Override
        public void onFailure(Call<List<RelatedTopic>> call, Throwable t) {
            networkUtils.showFailureDialog();
        }
    };

    private DataManager.ListCompleteListener listCompleteListener = new DataManager.ListCompleteListener() {
        @Override
        public void onListPopulateComplete() {
            networkOpsCompleteListener.onNetworkOpsComplete();
        }
    };

    public interface NetworkOpsCompleteListener {
        void onNetworkOpsComplete();
    }
}

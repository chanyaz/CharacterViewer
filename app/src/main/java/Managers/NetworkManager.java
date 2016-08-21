package Managers;

import com.sumayyah.characterviewer.Views.Console;

import Model.APIResponse;
import Network.NetworkUtils;
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

        Call<APIResponse> call = networkUtils.getAPIService().getApiResponse();
        call.enqueue(callback);
    }

    private Callback<APIResponse> callback = new Callback<APIResponse>() {
        @Override
        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            APIResponse apiResponse = response.body();
            DataManager.getInstance().populateList(apiResponse, listCompleteListener);
        }

        @Override
        public void onFailure(Call<APIResponse> call, Throwable t) {
            Console.log("App - Failure "+call.toString());
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

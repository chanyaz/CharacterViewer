package Managers;
import com.sumayyah.characterviewer.Views.Console;

import Model.APIResponse;
import Network.APIService;
import Network.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sumayyah on 8/16/16.
 */
public class NetworkManager {

    private NetworkOpsCompleteListener networkOpsCompleteListener;
    private static String BASE_URL= null;

    public NetworkManager(NetworkOpsCompleteListener networkOpsCompleteListener, String baseUrl) {
        this.networkOpsCompleteListener = networkOpsCompleteListener;
        this.BASE_URL = baseUrl;
    }

    public void executeAPICall() {

        APIService apiService = new RetrofitBuilder().getRetrofitBuilder().create(APIService.class);
        Call<APIResponse> call = apiService.getApiResponse();
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
            //TODO error dialog
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

    public static String getBaseURL() {
        return BASE_URL;
    }
}

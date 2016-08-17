package Managers;

import com.sumayyah.characterviewer.Views.Console;
import com.sumayyah.characterviewer.Views.MainActivity;

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

    public NetworkManager(NetworkOpsCompleteListener networkOpsCompleteListener) {
        this.networkOpsCompleteListener = networkOpsCompleteListener;
    }

    public void executeAPICall() {

        Console.log("Calling API");
        APIService apiService = new RetrofitBuilder().getRetrofitBuilder().create(APIService.class);
        Call<APIResponse> call = apiService.getApiResponse();
        call.enqueue(callback);
    }

    private Callback<APIResponse> callback = new Callback<APIResponse>() {
        @Override
        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            Console.log("App - Response was "+response.toString());
            Console.log("Parsing response body "+response.body().toString());
            Console.log("Hmmm "+response.body().getRelatedTopics().size()+" code "+response.code());
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
            Console.log("Finished populating list "+DataManager.getInstance().getList().size());
            networkOpsCompleteListener.onNetworkOpsComplete();
        }
    };

    public interface NetworkOpsCompleteListener {
        void onNetworkOpsComplete();
    }
}

package Network;

import Model.APIResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sumayyah on 8/16/16.
 */
public interface APIService {

    @GET("?q=star+wars+characters&format=json")
    public Call<APIResponse> getApiResponse();
}

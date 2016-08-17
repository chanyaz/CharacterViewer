package Network;

import com.sumayyah.characterviewer.BuildConfig;

import Model.APIResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sumayyah on 8/16/16.
 */
public interface APIService {

    @GET(BuildConfig.API_PARAMS)
    public Call<APIResponse> getApiResponse();
}

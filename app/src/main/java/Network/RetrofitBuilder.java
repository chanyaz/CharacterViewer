package Network;

import com.sumayyah.characterviewer.Views.App;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sumayyah on 8/16/16.
 */
public class RetrofitBuilder {

    public Retrofit getRetrofitBuilder() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.dataManager.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

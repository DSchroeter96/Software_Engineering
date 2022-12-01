package ch.epfl.sweng.domain.api.retrofit;

import ch.epfl.sweng.domain.api.HackerNewsApi;
import ch.epfl.sweng.domain.api.HackerNewsApiFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHackerNewsApiFactory implements HackerNewsApiFactory {

    private final Retrofit retrofit =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(new LiveDataAdapterFactory())
                    .baseUrl(HackerNewsApi.BASE_URL)
                    .build();

    @Override
    public HackerNewsApi create() {
        return retrofit.create(HackerNewsApi.class);
    }
}

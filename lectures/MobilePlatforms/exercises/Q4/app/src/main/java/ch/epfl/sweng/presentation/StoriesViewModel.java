package ch.epfl.sweng.presentation;

import static androidx.lifecycle.Transformations.map;
import static androidx.lifecycle.Transformations.switchMap;

import static java.util.stream.Collectors.toList;

import static ch.epfl.sweng.presentation.utils.LiveDataTransformations.combineLatestNonNull;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Comparator;
import java.util.List;

import ch.epfl.sweng.domain.Story;
import ch.epfl.sweng.domain.api.ApiResponse;
import ch.epfl.sweng.domain.api.HackerNewsApi;
import ch.epfl.sweng.domain.api.HackerNewsItem;
import ch.epfl.sweng.domain.api.retrofit.RetrofitHackerNewsApiFactory;

public class StoriesViewModel extends AndroidViewModel {

    private final HackerNewsApi api;

    public StoriesViewModel(@NonNull Application application) {
        super(application);
        this.api = new RetrofitHackerNewsApiFactory().create();
    }

    private LiveData<List<Integer>> getTopItemIdsLiveData() {
        var top = api.topStories();
        return map(top, r -> r.stream().flatMap(List::stream).collect(toList()));
    }

    private LiveData<List<HackerNewsItem>> getTopItemsLiveData() {
        var responses =
                switchMap(
                        getTopItemIdsLiveData(),
                        ids -> combineLatestNonNull(ids.stream().map(api::item).collect(toList())));
        return map(responses, r -> r.stream().flatMap(ApiResponse::stream).collect(toList()));
    }

    public LiveData<List<Story>> getTopStoriesLiveData() {
        return map(
                getTopItemsLiveData(),
                list ->
                        list.stream()
                                .map(it -> new Story.Builder().id(it.id).title(it.title).url(it.url).build())
                                .sorted(Comparator.comparing(Story::getId).reversed())
                                .collect(toList()));
    }

    @NonNull
    public LiveData<List<Story>> getTopStories() {
        return getTopStoriesLiveData();
    }
}

package ch.epfl.sweng.presentation.utils;

import static androidx.lifecycle.Transformations.map;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LiveDataTransformations {
    private LiveDataTransformations() {

    }

    public static <T> LiveData<List<T>> combineLatest(List<LiveData<T>> list) {
        var result = new MediatorLiveData<List<T>>();
        var initial = new ArrayList<T>(list.size());
        result.postValue(initial);
        for (int i = 0; i < list.size(); i++) {
            final int index = i;
            initial.add(null);
            result.addSource(list.get(i), value -> {
                var current = requireNonNull(result.getValue());
                var updated = new ArrayList<>(current);
                updated.set(index, value);
                result.postValue(updated);
            });
        }
        return result;
    }

    public static <T> LiveData<List<T>> combineLatestNonNull(List<LiveData<T>> list) {
        return map(combineLatest(list), v -> v.stream().filter(Objects::nonNull).collect(toList()));
    }
}

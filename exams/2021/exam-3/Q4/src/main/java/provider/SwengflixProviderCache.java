package provider;

import model.VideoFile;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;

public class SwengflixProviderCache implements VideoFileProvider {

    private final VideoFileProvider wrapped;
    private final HashMap<Integer, VideoFile> cache;
    private final Deque<Integer> indices;

    public SwengflixProviderCache(VideoFileProvider wrapped) {
        this.wrapped = wrapped;
        this.cache = new  HashMap<>(2);
        this.indices = new LinkedList<>();
    }

    @Override
    public CompletableFuture<VideoFile> getVideo(int uniqueID) {
        if (cache.containsKey(uniqueID)) {
            this.indices.remove(uniqueID);
            this.indices.push(uniqueID);
            return CompletableFuture.completedFuture(cache.get(uniqueID));
        }

        return wrapped.getVideo(uniqueID).thenApply(videoFile -> {
            if (cache.size() >= 2 && !this.indices.isEmpty()) {
                cache.remove(this.indices.pollLast());
            }
            this.indices.push(videoFile.uniqueID);
            cache.put(videoFile.uniqueID, videoFile);
            return videoFile;
        });
        //return wrapped.getVideo(uniqueID).whenComplete(((videoFile, throwable) -> {
        //    if (videoFile != null) {
        //        if (cache.size() >= 2 && !this.indices.isEmpty()) {
        //            cache.remove(this.indices.pollLast());
        //        }
        //        this.indices.push(videoFile.uniqueID);
        //        cache.put(videoFile.uniqueID, videoFile);
        //    }
        //}));
    }
}

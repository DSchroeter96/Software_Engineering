package provider;

import data.Database;
import data.VideoDataEU;
import data.VideoDataUS;
import model.VideoFile;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of a video file provider for Swengflix.
 * !!!!!!!!!!!!!!!!!!!!!!!
 * You CAN edit this file.
 * !!!!!!!!!!!!!!!!!!!!!!!
 */
public class SwengflixProvider implements VideoFileProvider {

    private final List<Database> dbs;

    /**
     * Creates a SwengflixProvider using the given databases.
     */
    public SwengflixProvider(Database... dbs) {
        this.dbs = Arrays.asList(dbs);
    }

    /**
     * Creates a SwengflixProvider using the default databases.
     */
    public SwengflixProvider() {
        // the EU database is geographically closer so its delay is lower
        this.dbs = List.of(new Database(VideoDataEU.allVideoData(), 3_000),
                new Database(VideoDataUS.allVideoData(), 5_000));
    }

    @Override
    public CompletableFuture<VideoFile> getVideo(int uniqueID) {
        // TODO: We should enable querying of any of our databases (not just the EU DB)
        //return dbs.get(0).getVideoFile(uniqueID);
        //return dbs
        //        .stream()
        //        .map(db -> db.getVideoFile(uniqueID))
        //        .reduce((f1, f2) -> f1.applyToEither(f2, videoFile -> videoFile))
        //        .orElseThrow(() -> new RuntimeException("No database where present to fetch from"));
        return dbs
                .stream()
                .map(db -> db.getVideoFile(uniqueID))
                .reduce(SwengflixProvider::either)
                .orElseThrow(() -> new RuntimeException("No database were present to fetch from"));
    }

    private static <T> CompletableFuture<T> either(
            CompletableFuture<T> f1, CompletableFuture<T> f2) {
        CompletableFuture<T> result = new CompletableFuture<>();
        CompletableFuture.allOf(f1, f2).whenComplete((res , throwable) -> {
            if (f1.isCompletedExceptionally() && f2.isCompletedExceptionally()) {
                result.completeExceptionally(throwable);
            }
        });

        f1.thenAccept(result::complete);
        f2.thenAccept(result::complete);
        return result;
    }

    // Answer provided by the correction

    /*@Override
    public CompletableFuture<VideoFile> getVideo(int uniqueID) {
        if (dbs.isEmpty()) {
            return CompletableFuture.failedFuture(new AssertionError("There are no databases."));
        }
        var result = new CompletableFuture<VideoFile>();
        // Complete with the first successful result
        var futures = dbs.stream()
                .map(d -> d.getVideoFile(uniqueID).thenAccept(result::complete))
                .toArray(CompletableFuture[]::new);
        // Propagate errors once they're all done, will be used if there are no successful results
        CompletableFuture.allOf(futures).exceptionally(e -> { result.completeExceptionally(e); return null; });
        return result;
    }*/
}

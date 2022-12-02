import java.util.concurrent.*;

public final class Basics {

    // NOTE: The CompletableFuture API is documented at
    //       https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/CompletableFuture.html
    //       You can ignore the methods with "async" suffixes for now.

    /** Prints to `System.out` the weather given by `Weather.today` */
    public static CompletableFuture<Void> printTodaysWeather() {
        // println is synchronous, so we have to run then Accept, it will directly execute it and return Void.
        return Weather.today().thenAccept(System.out::println);
    }

    /** Uploads using `Server.upload` the weather given by `Weather.today` */
    public static CompletableFuture<Void> uploadTodaysWeather() {
        return Weather.today().thenCompose(Server::upload);
    }

    /** Prints to `System.out` the weather given by either `Weather.today` or `Weather.yesterday`,
        whichever is available first, prefixed by "Today: " and "Yesterday: " respectively */
    public static CompletableFuture<Void> printSomeWeather() {
        //var today = Weather.today();
        //var yesterday = Weather.yesterday();
        //return CompletableFuture.anyOf(today, yesterday).thenAccept(System.out::println);
        return Weather.today()
                .thenApply(w -> "Today: " + w)
                .acceptEither(
                        Weather.yesterday().thenApply(w -> "Yesterday: " + w),
                        System.out::println
                );
    }

    /** Prints to `System.out` the weather given by `Weather.all`,
        unless it takes more than 2 seconds,
        in which case it prints the weather given by `Weather.today` prefixed with "Today: " */
    public static CompletableFuture<Void> tryPrintAllWeather() {
        // TODO (hint: remember that CompletableFuture's documentation includes methods inherited from CompletionStage)
        return Weather.all()
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionallyCompose(e ->
                        Weather.today().thenApply(w -> "Today: " + w)
                ).thenAccept(System.out::println);
    }
}

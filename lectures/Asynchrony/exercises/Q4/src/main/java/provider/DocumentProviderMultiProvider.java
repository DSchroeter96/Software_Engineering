package provider;

import model.Document;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.failedFuture;

public class DocumentProviderMultiProvider implements DocumentProvider {

    private final DocumentProvider[] providers;

    public DocumentProviderMultiProvider(DocumentProvider... providers) {
        this.providers = Arrays.copyOf(providers, providers.length);
    }

    private static Throwable combineExceptions(Throwable e1, Throwable e2) {
        if (e1 instanceof DocumentNotFoundException) return e2;
        return e1;
    }
    @Override
    public CompletableFuture<Document> fetchDocument(String id) {
        if (providers.length == 0) return failedFuture(new DocumentNotFoundException(id));
        CompletableFuture<Document> future = providers[0].fetchDocument(id);
        for (DocumentProvider provider : providers) {
            future = future.exceptionallyCompose(throwable ->
                    provider.fetchDocument(id)
                            .exceptionallyCompose(throwable1 ->
                                    failedFuture(combineExceptions(throwable, throwable1))
                            )
            );
        }
        return future;
        /*return Arrays.stream(providers)
                .map(providers -> providers.fetchDocument(id))
                .reduce((val1, val2) ->
                    val1.exceptionallyCompose(throwable1 ->
                        val2.exceptionallyCompose(throwable2 ->
                            failedFuture(combineExceptions(throwable1, throwable2))
                        )
                    )
                ).orElse(failedFuture(new DocumentNotFoundException(id)));*/
    }
}

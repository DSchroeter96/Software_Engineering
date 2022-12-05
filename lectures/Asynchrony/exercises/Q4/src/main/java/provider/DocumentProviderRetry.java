package provider;

import model.Document;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.CompletableFuture.failedFuture;

public class DocumentProviderRetry implements DocumentProvider {

    private final DocumentProvider wrapped;

    public DocumentProviderRetry(DocumentProvider wrapped) {
        this.wrapped = requireNonNull(wrapped);
    }

    private boolean isDocumentNotFoundException(Throwable throwable) {
        Throwable current = throwable;
        while (current != null && current != current.getCause()) {
            if (current instanceof DocumentNotFoundException) {
                return true;
            }
            current = current.getCause();
        }
        return false;
    }

    @Override
    public CompletableFuture<Document> fetchDocument(String id) {
        return wrapped.fetchDocument(id).exceptionallyCompose(throwable -> {
            if (isDocumentNotFoundException(throwable)) {
                return failedFuture(throwable); // Works, because the reference to throwable is actually modified in the isDocumentNotFoundException method
            }
            //if (throwable instanceof CompletionException ex) {
            //    if (ex.getCause() instanceof DocumentNotFoundException ex1) {
            //        return failedFuture(ex1);
            //    }
            //}
            //if (throwable instanceof DocumentNotFoundException) {
            //    return failedFuture(throwable);
            //}
            return this.fetchDocument(id);
        });
    }
}

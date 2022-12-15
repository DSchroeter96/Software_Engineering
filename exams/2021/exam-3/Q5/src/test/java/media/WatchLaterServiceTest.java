package media;

import authentication.AuthenticationException;
import authentication.AuthenticationService;
import authentication.MockAuthenticationService;
import authentication.RemoteAuthenticationService;
import data.Database;
import data.MockDatabase;
import data.RemoteDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import session.Session;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN edit this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!
 */
public class WatchLaterServiceTest {

    private Database database;
    private AuthenticationService authenticationService;
    private WatchLaterService service;
    @BeforeEach
    public void setup() {
        database = new MockDatabase();
        authenticationService = new MockAuthenticationService();
        service = new WatchLaterService(database, authenticationService);
    }

    @Test
    public void serviceDatabaseShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new WatchLaterService(null, authenticationService));
    }

    @Test
    public void serviceAuthenticationShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new WatchLaterService(database, null));
    }

    @Test
    public void loginUsernameShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> service.login(null, "pwd"));
    }

    @Test
    public void loginPasswordShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> service.login("username", null));
    }

    @Test
    public void retrieveListThrowExceptionWhenUnauthenticated() {
        var exception = assertThrows(CompletionException.class, () -> service.retrieveList("SwengFan").join());
        assertThat(exception.getCause(), isA(AuthenticationException.class));
    }

    @Test
    public void addThrowExceptionWhenUnauthenticated() {
        var exception = assertThrows(CompletionException.class, () -> service.add("SwengFan", 1).join());
        assertThat(exception.getCause(), isA(IllegalStateException.class));
    }

    @Test
    public void removeThrowExceptionWhenUnauthenticated() {
        var exception = assertThrows(CompletionException.class, () -> service.remove("SwengFan", 1).join());
        assertThat(exception.getCause(), isA(IllegalStateException.class));
    }
    
    @Test
    public void retrieveReturnsAddedIdsAuthenticatedUser() {
        Set<Integer> retrieved =
                service
                        .login("SwengFan", "123456")
                        .thenCompose(a -> service.add("SwengFan", 1))
                        .thenCompose(a -> service.add("SwengFan", 4))
                        .thenCompose(a -> service.add("SwengFan", 16))
                        .thenCompose(a -> service.retrieveList("SwengFan"))
                        .orTimeout(5, TimeUnit.SECONDS)
                        .join();

        assertThat(retrieved, containsInAnyOrder(1, 4, 16));
    }

    @Test
    public void retrieveListThrowsOtherUserAuthenticated() {
        service.login("SwengFan", "123456").join();
        var exception = assertThrows(CompletionException.class, () -> service.retrieveList("Other").join());
        assertThat(exception.getCause(), isA(AuthenticationException.class));
    }

    @Test
    public void retrieveThrowExceptionForNullUsername() {
        service.login("SwengFan", "123456").join();
        var exception = assertThrows(CompletionException.class, () -> service.retrieveList(null).join());
        assertThat(exception.getCause(), isA(AuthenticationException.class));
    }

    @Test
    public void retrieveDoesNotReturnRemovedElemAuthenticatedUser() {
        Set<Integer> retrieved =
                service
                        .login("SwengFan", "123456")
                        .thenCompose(a -> service.add("SwengFan", 1))
                        .thenCompose(a -> service.add("SwengFan", 4))
                        .thenCompose(a -> service.add("SwengFan", 16))
                        .thenCompose(a -> service.retrieveList("SwengFan"))
                        .orTimeout(5, TimeUnit.SECONDS)
                        .join();

        assertThat(retrieved, containsInAnyOrder(1, 4, 16));

        retrieved = service
                .remove("SwengFan", 16)
                .thenCompose(a -> service.retrieveList("SwengFan"))
                .orTimeout(5, TimeUnit.SECONDS)
                .join();

        assertThat(retrieved, containsInAnyOrder(1, 4));
    }


}

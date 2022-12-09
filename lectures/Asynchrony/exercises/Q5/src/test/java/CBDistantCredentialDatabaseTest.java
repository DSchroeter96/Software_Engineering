import model.*;
import model.callback.CBDistantCredentialDatabase;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CBDistantCredentialDatabaseTest {

    @Test
    public void authenticateNonExistingUserThrows() {
        final var futureUser = new callbackWrapper();
        final var database = new CBDistantCredentialDatabase();

        database.authenticate("David", "1234", futureUser);

        var exception = assertThrows(CompletionException.class, () -> futureUser.orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(UnknownUserException.class));
    }

    @Test
    public void wrongUserNameCannotAuthenticate() {
        final var futureUser = new callbackWrapper();
        final var futureAuthenticatedUser = new callbackWrapper();
        final var database = new CBDistantCredentialDatabase();

        database.addUser("David", "12345", 1, futureUser);

        StudentUser registeredUser = futureUser.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(registeredUser.getUserName(), is("David"));
        assertThat(registeredUser.getID(), is("1"));

        database.authenticate("DAvid", "12345", futureAuthenticatedUser);

        var exception = assertThrows(CompletionException.class, () -> futureAuthenticatedUser.orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(UnknownUserException.class));
    }

    @Test
    public void newUserCorrectlyAdded() {
        final var futureUser = new callbackWrapper();
        final var database = new CBDistantCredentialDatabase();
        database.addUser("David", "1234", 1, futureUser);

        StudentUser user = futureUser.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(user.getUserName(), is("David"));
        assertThat(user.getID(), is("1"));
    }

    @Test
    public void addingSameUserTwiceThrows() {
        final var futureUser1 = new callbackWrapper();
        final var futureUser2 = new callbackWrapper();
        final var database = new CBDistantCredentialDatabase();

        database.addUser("David", "1234", 1, futureUser1);

        StudentUser user = futureUser1.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(user.getUserName(), is("David"));
        assertThat(user.getID(), is("1"));

        database.addUser("David", "1234", 15, futureUser2);
        var exception = assertThrows(CompletionException.class, () -> futureUser2.orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(AlreadyExistsUserException.class));
    }

    @Test
    public void existingUserCanAuthenticate() {
        final var futureUser = new callbackWrapper();
        final var futureAuthenticatedUser = new callbackWrapper();
        final var database = new CBDistantCredentialDatabase();

        database.addUser("David", "12345", 1, futureUser);
        StudentUser registeredUser = futureUser.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(registeredUser.getUserName(), is("David"));
        assertThat(registeredUser.getID(), is("1"));

        database.authenticate("David", "12345", futureAuthenticatedUser);
        StudentUser authenticatedUser = futureAuthenticatedUser.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(authenticatedUser.getUserName(), is("David"));
        assertThat(authenticatedUser.getID(), is("1"));
    }

    @Test
    public void wrongPasswordCannotAuthenticate() {
        final var futureUser = new callbackWrapper();
        final var futureAuthenticatedUser = new callbackWrapper();
        final var database = new CBDistantCredentialDatabase();

        database.addUser("David", "12345", 1, futureUser);
        StudentUser registeredUser = futureUser.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(registeredUser.getUserName(), is("David"));
        assertThat(registeredUser.getID(), is("1"));

        database.authenticate("David", "1234",  futureAuthenticatedUser);
        var exception = assertThrows(CompletionException.class, () -> futureAuthenticatedUser.orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(InvalidCredentialException.class));
    }

    private static class callbackWrapper extends CompletableFuture<StudentUser> implements CredentialDatabaseCallback {
        @Override
        public void onSuccess(StudentUser user) {
            complete(user);
        }

        @Override
        public void onError(DatabaseException exception) {
            completeExceptionally(exception);
        }
    }
}

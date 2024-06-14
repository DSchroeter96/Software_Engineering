package model;

import model.auth.AuthenticationService;
import model.auth.MockAuthenticationService;
import model.data.Database;
import model.data.GradedBook;
import model.data.MockDatabase;
import model.data.Reaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class for the tests of {@link ReactionService}.
 * <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! <p>
 * You MUST use this file to test {@link ReactionService}                   <p>
 * You CAN add new methods, variables and nested classes to this class.     <p>
 * You MUST NOT rename this file.                                           <p>
 * You MUST NOT delete this file.                                           <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! <p>
 */
class ReactionServiceTest {

    private Database database;
    private AuthenticationService authService;
    private ReactionService reactionService;
    
    @BeforeEach
    void setup() {
        database = new MockDatabase();
        authService = new MockAuthenticationService();
        reactionService = new ReactionService(authService, database);
    }
    
    @Test
    public void constructorThrowsIllegalArgumentExceptionNullDatabase() {
        assertThrows(IllegalArgumentException.class, () -> new ReactionService(authService, null));
    }

    @Test
    public void constructorThrowsIllegalArgumentExceptionNullAuthService() {
        assertThrows(IllegalArgumentException.class, () -> new ReactionService(null, database));
    }

    @Test
    public void likeThrowReactionServiceExceptionUnauthenticatedUser() {
        var exception = assertThrows(CompletionException.class, () -> reactionService.like("Vagabond").orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(ReactionServiceException.class));
    }

    @Test
    public void dislikeThrowReactionServiceExceptionUnauthenticatedUser() {
        var exception = assertThrows(CompletionException.class, () -> reactionService.dislike("Vagabond").orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(ReactionServiceException.class));
    }
    
    @Test
    public void likeThrowReactionServiceExceptionNonExistingBookTitle() {
        login();
        var exception = assertThrows(CompletionException.class, () -> reactionService.like("Not existing").orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(ReactionServiceException.class));
    }

    @Test
    public void dislikeThrowReactionServiceExceptionNonExistingBookTitle() {
        login();
        var exception = assertThrows(CompletionException.class, () -> reactionService.like("Not existing").orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(ReactionServiceException.class));
    }
    
    @Test
    public void likeCorrectlyModifyUserReaction() {
        login();
        GradedBook book = database.getByTitle("Berserk").orTimeout(5, TimeUnit.SECONDS).join();
        reactionService.like(book.getTitle()).orTimeout(5, TimeUnit.SECONDS).join();
        Set<Reaction> reactionSet = database.getReactionsByUser("Sweng").orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(reactionSet, contains(new Reaction(book.getTitle(), true)));
    }

    @Test
    public void dislikeCorrectlyModifyUserReaction() {
        login();
        GradedBook book = database.getByTitle("Berserk").orTimeout(5, TimeUnit.SECONDS).join();
        reactionService.dislike(book.getTitle()).orTimeout(5, TimeUnit.SECONDS).join();
        Set<Reaction> reactionSet = database.getReactionsByUser("Sweng").orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(reactionSet, contains(new Reaction(book.getTitle(), false)));
    }

    private void login() {
        authService.login("Sweng", "1").orTimeout(5, TimeUnit.SECONDS).join();
    }

}
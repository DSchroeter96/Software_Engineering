package model;

import model.data.Database;
import model.data.GradedBook;
import model.data.MockDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class for the tests of {@link TrendingService}.
 * <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! <p>
 * You MUST use this file to test {@link TrendingService}                   <p>
 * You CAN add new methods, variables and nested classes to this class.     <p>
 * You MUST NOT rename this file.                                           <p>
 * You MUST NOT delete this file.                                           <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! <p>
 */
class TrendingServiceTest {

    private Database database;
    private TrendingService service;

    @BeforeEach
    void setup() {
        database = new MockDatabase();
        service = new TrendingService(database);
    }
    @Test
    public void constructorThrowsIllegalArgumentExceptionNullDatabase() {
        assertThrows(IllegalArgumentException.class, () -> new TrendingService( null));
    }

    @Test
    public void getTrendingReturnsListCorrectlyOrdered() {
        var list = service.getTrending().orTimeout(5, TimeUnit.SECONDS).join();
        Set<GradedBook> books = database.getAllBooks().orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(list, is(books.stream().sorted(Comparator.comparingInt(GradedBook::getGrade).reversed()).toList()));
    }

    @Test
    public void getTrendingReturnsNFirstCOrrectlyOrderedElements() {
        var list = service.getTrending(4).orTimeout(5, TimeUnit.SECONDS).join();
        Set<GradedBook> books = database.getAllBooks().orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(list, is(books.stream().sorted(Comparator.comparingInt(GradedBook::getGrade).reversed()).limit(4).toList()));
    }

    @Test
    public void getTrendingThrowIllegalArgumentsExceptionNegativeNb() {
        var exception = assertThrows(CompletionException.class, () -> service.getTrending(-1).orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(IllegalArgumentException.class));
    }

    @Test
    public void getTrendingThrowIllegalArgumentsExceptionTooBigNb() {
        var exception = assertThrows(CompletionException.class, () -> service.getTrending(100).orTimeout(5, TimeUnit.SECONDS).join());
        assertThat(exception.getCause(), isA(IllegalArgumentException.class));
    }
}
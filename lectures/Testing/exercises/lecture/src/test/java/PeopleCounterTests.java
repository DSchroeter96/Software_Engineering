import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PeopleCounterTests {
    private PeopleCounter counter;

    @BeforeEach void instantiateNewPeopleBeforeEachTest() {
        counter = new PeopleCounter(0);
    }

    @Test void counterStartsAt0() {
        assertThat(counter.counter(), is(0));
    }

    @Test void counterHasConfigurableMax() {
        assertThat(counter.max(), is(0));
        counter.newMax(15);
        assertThat(counter.max(), is(15));
    }

    @Test void maxCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> counter.newMax(-1));
    }

    @Test void counterCanBeIncrementedBy1() {
        assertThat(counter.counter(), is(0));
        counter.increment();
        assertThat(counter.counter(), is(1));
        counter.increment();
        counter.increment();
        assertThat(counter.counter(), is(3));
    }

    @Test void counterCanBeResetTo0() {
        counter.increment();
        counter.increment();
        assertThat(counter.counter(), is(2));
        counter.reset();
        assertThat(counter.counter(), is(0));
    }
}

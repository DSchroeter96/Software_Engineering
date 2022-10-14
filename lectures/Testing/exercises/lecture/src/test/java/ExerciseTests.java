import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ExerciseTests {
    @Test
    void onePlusOneIsTwo() {
        assertThat(1 + 1, is(2));
    }

    // TODO: tests!
    // Remember "assertThat" with matchers (see the documentation!)
    // and "assertThrows" to assert an exception is thrown

    //===============================================================
    //Test fibonacci
    //===============================================================

    @Test
    void testFibonacci1is1() {
        assertThat(Functions.fibonacci(1), is(1));
    }

    @Test void testFibonacci10is55() {
        assertThat(Functions.fibonacci(10), is(55));
    }

    @Test void testFibonacci0is0() {
        assertThat(Functions.fibonacci(0), is(0));
    }

    @Test void testFibonacciInvalidNumberThrowCorrectError() {
        assertThrows(IllegalArgumentException.class, () -> Functions.fibonacci(-1));
    }

    //===============================================================
    //Test fibonacci
    //===============================================================

    @Test void splitWithSpaceSeparator() {
        assertThat(Functions.splitString("a b", ' '), contains("a", "b"));
    }

    @Test void splitNullStringThrowsError() {
        assertThrows(NullPointerException.class, () -> Functions.splitString(null, ' '));
    }

    @Test void splitEmptyStringReturnEmptyString() {
        assertThat(Functions.splitString("", ' '), contains(""));
    }

    //===============================================================
    //Test shuffle
    //===============================================================

    @Test void shuffleDontLoseCharacter() {
        final String[] myStr = new String[]{"a", "b", "c", "d", "e", "abo", "lsdf", "sl", "laf"};
        Functions.shuffle(myStr);
        assertThat(myStr, arrayContainingInAnyOrder("a", "b", "c", "d", "e", "abo", "lsdf", "sl", "laf"));
    }

}

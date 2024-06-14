import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Class for the Joke.java tests.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You MUST use this file to test Joke.java
 * You CAN add new constructors, methods, and nested classes to this class.
 * You MUST NOT rename this file.
 * You MUST NOT delete this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class JokeTests {
    @Test
    public void onePlusOneIsTwo() {
        assertThat(1 + 1, is(2));
    }

    @Test
    public void nullJokeIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Joke(null, JokeType.Random));
    }

    @Test
    public void emptyJokeIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Joke("", JokeType.Random));
    }

    @Test
    public void nullTypeIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Joke("My joke", null));
    }
    
    @Test
    public void jokeWithMoreThan2LinesIsNotFunny() {
        String joke = String.format("%s\n%s\n%s", "line1", "line2", "line3");
        assertThrows(IllegalArgumentException.class, () -> new Joke(joke, JokeType.Random));
    }

    @Test
    public void jokeWithMoreThan5WordsPerLineInAverageIsNotFunny() {
        String joke = String.format("%s\n%s",
                "word 1 word2 word3 word4 word5 word6",
                "word 1 word2 word3 word4 word5 word6");
        assertThrows(IllegalArgumentException.class, () -> new Joke(joke, JokeType.Random));
    }

    @Test
    public void jokeWellFormattedContainsCorrectContent() {
        String jokeContent = String.format("%s\n%s",
                "This is a",
                "funny joke");
        Joke joke = new Joke(jokeContent, JokeType.Random);
        assertThat(joke.getContent(), is(jokeContent));
    }

    @Test
    public void oneLinearJokeGetsPrintedCorrectly() {
        String jokeContent = String.format("%s\n%s",
                "This is a",
                "funny joke");
        Joke joke = new Joke(jokeContent, JokeType.OneLiner);
        assertThat(joke.toString(), is("This is a funny joke"));
    }

    @Test
    public void observationalJokeGetsPrintedCorrectly() {
        String jokeContent = String.format("%s\n%s",
                "This is a",
                "funny joke");
        Joke joke = new Joke(jokeContent, JokeType.Observational);
        assertThat(joke.toString(), is("Have you ever noticed, " + jokeContent));
    }

    @Test
    public void randomJokeGetsTheirContentPrinted() {
        String jokeContent = String.format("%s\n%s",
                "This is a",
                "funny joke");
        Joke joke = new Joke(jokeContent, JokeType.Random);
        assertThat(joke.toString(), is(joke.getContent()));
    }

    @Test
    public void jokeGetsAdaptedCorrectlyAccordingToRules() {
        String jokeContent = String.format("%s\n%s",
                "This is a",
                "funny joke");
        Joke joke = new Joke(jokeContent, JokeType.Random);
        joke = joke.adapt(Map.of("is", "isn't"));
        assertThat(joke.toString(), is("This isn't a\nfunny joke"));
    }

    @Test
    public void emptyLineAreFilteredFromJokeWhenCheckingIfFunny() {
        String jokeContent = String.format("%s\n \n%s",
                "This is a",
                "funny joke");
        Joke joke = new Joke(jokeContent, JokeType.Random);
        assertThat(joke.toString(), is("This is a\n \nfunny joke"));
    }
}

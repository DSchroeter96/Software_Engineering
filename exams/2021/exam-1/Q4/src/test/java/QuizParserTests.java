import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Class for the quiz parser tests.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You MUST use this file for quiz parser tests.
 * You CAN add new constructors, methods, and nested classes to this class.
 * You MUST NOT rename this file.
 * You MUST NOT delete this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
final class QuizParserTests {
    private final QuizParser parser;
    public QuizParserTests() {
        this.parser = new QuizParser();
    }

    @BeforeAll
    public static void initiateParser() {
        new QuizParserTests();
    }

    @Test
    public void parserWithCorrectInputShouldParseCorrectly() {
        String heading = "# question heading";
        List<String> choices = Arrays.asList("- Choices 1", "- Choices 2", "- Choices 3");
        String solution = "> Solution";

        QuizQuestion question = parser.parse(String.format("%s\n%s\n%s", heading, String.join("\n", choices), solution));

        assertThat(question.heading, is("question heading"));
        assertThat(question.choices, is(Arrays.asList("Choices 1", "Choices 2", "Choices 3")));
        assertThat(question.solution, is("Solution"));
    }

    @Test
    public void parserThrowQuizFormatExceptionWhenHeadingStartsWithWrongCharacter() {
        String heading = "question heading";
        List<String> choices = Arrays.asList("- Choices 1", "- Choices 2", "- Choices 3");
        String solution = "> Solution";

        assertThrows(QuizFormatException.class,
                () -> parser.parse(String.format("%s\n%s\n%s", heading, String.join("\n", choices), solution)));

    }

    @Test
    public void parserThrowExceptionForEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    @Test
    public void parserThrowExceptionForQuestionsWithoutChoices() {
        String heading = "question heading";
        String solution = "> Solution";

        assertThrows(QuizFormatException.class,
                () -> parser.parse(String.format("%s\n%s\n%s", heading, "", solution)));
    }

    @Test
    public void parserThrowExceptionWhenSolutionIsNotLastInfQuestion() {
        String heading = "# question heading";
        List<String> choices = Arrays.asList("- Choices 1", "- Choices 2", "- Choices 3");
        String solution = "> Solution";

        assertThrows(IllegalArgumentException.class,
                () -> parser.parse(String.format("%s\n%s\n%s\n%s", heading, String.join("\n", choices), solution, "- Choices 4")));
    }

    @Test
    public void questionIsInvalidIfHeadingIsEmpty() {
        String heading = "#";
        List<String> choices = Arrays.asList("- Choices 1", "- Choices 2", "- Choices 3");
        String solution = "> Solution";

        assertThrows(QuizFormatException.class,
                () -> parser.parse(String.format("%s \n%s\n%s",heading, String.join("\n", choices), solution)));
    }

    @Test
    public void parserThrowExceptionForQuestionsWithoutSolution() {
        String heading = "# question heading";
        List<String> choices = Arrays.asList("- Choices 1", "- Choices 2", "- Choices 3");

        assertThrows(IllegalArgumentException.class,
                () -> parser.parse(String.format("%s\n%s\n%s", heading, String.join("\n", choices), "")));
    }

    @Test
    public void parserThrowExceptionIfQuestionContainsOnlyHeader() {
        String heading = "# question heading";

        assertThrows(IllegalArgumentException.class,
                () -> parser.parse(String.format("%s", heading)));
    }

    @Test
    public void parserThrowExceptionIfSolutionDoesNotHaveRightStartingChar() {
        String heading = "# question heading";
        List<String> choices = Arrays.asList("- Choices 1", "- Choices 2", "- Choices 3");
        String solution = "Solution";

        assertThrows(QuizFormatException.class,
                () -> parser.parse(String.format("%s\n%s\n%s", heading, String.join("\n", choices), solution)));

    }

    @Test
    public void parserThrowExceptionIfChoicesDoesNotHaveRightStartingChar() {
        String heading = "# question heading";
        List<String> choices = Arrays.asList("Choices 1", "- Choices 2", "- Choices 3");
        String solution = "> Solution";

        assertThrows(QuizFormatException.class,
                () -> parser.parse(String.format("%s\n%s\n%s", heading, String.join("\n", choices), solution)));
    }

    @Test
    public void parserThrowExceptionIfNullString() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
    }
}

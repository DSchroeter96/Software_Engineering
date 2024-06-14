import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Class for the JaaS API client tests.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You MUST use this file for the users fetcher tests.
 * You CAN add new constructors, methods, and nested classes to this class.
 * You MUST NOT rename this file.
 * You MUST NOT delete this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class JaasClientTests {
    @Test
    public void onePlusOneIsTwo() {
        assertThat(1 + 1, is(2));
    }

    //TODO
    @Test
    public void nullPathIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JaasClient(null, s -> "tst", TreeSet::new));
    }

    @Test
    public void nullHttpClientIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JaasClient("url", null, TreeSet::new));
    }

    @Test
    public void nullRoleClientIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JaasClient("url", s -> "tst", null));
    }
}

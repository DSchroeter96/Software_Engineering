import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

public class JokeFetcherTests {

    @Mock
    HttpURLConnection connection;

    @Test
    public void testFetchJoke() throws IOException {
        String answer = "I am a test";
        when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(answer.getBytes(StandardCharsets.UTF_8)));

        //TODO not working

    }
}

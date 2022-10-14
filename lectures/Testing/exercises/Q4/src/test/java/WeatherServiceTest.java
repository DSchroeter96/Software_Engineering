import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class WeatherServiceTest {
    @Test
    void example() {
        assertThat(1 + 1, is(2));
    }

    // Without using mockito, need to decalre a new calss and implement its method
    @Test
    void testWeatherServiceReturnsSunny() {
        WeatherService weatherService = new WeatherService(url -> "Sunny");
        assertThat(weatherService.getWeatherToday(), is(Weather.SUNNY));
    }

    // With mockito, see better what's happening
    @Test
    void testWeatherServiceReturnsSunnyMockito() throws IOException {
        HttpClient client = mock(HttpClient.class);
        when(client.get(anyString())).thenReturn("Sunny");

        WeatherService service = new WeatherService(client);
        assertThat(service.getWeatherToday(), is(Weather.SUNNY));
    }
}

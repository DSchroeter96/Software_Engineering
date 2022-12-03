import java.util.concurrent.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

final class WeatherTests {
    // Do not use sleep-based methods here!
    // However, since JUnit expects tests to be synchronous, you will need to wait for the operations to finish

    @Test
    void todaysWeatherIsSunny() {
        var weather =
                Weather.today()
                        .orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(weather, is("Sunny"));
    }

    @Test
    void clickingButtonSetsWeatherToSunny() {
        // TODO: Test that `WeatherView.clickButton` sets `WeatherView.weather` to `"Sunny"`, without changing `WeatherView`
        var future = new CompletableFuture<Void>();
        WeatherView.setCallback(() -> future.complete(null));
        WeatherView.clickButton();
        future.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(WeatherView.weather(), is("Sunny"));
    }

    @Test
    void weathersContainsYesterdayAndToday() {
        // TODO: Test that `Weather.printWeathers` yields `"Today: Sunny"` and `"Yesterday: Cloudy"` in any order, **changing `Weather.printWeathers` as necessary**
        //       However, keep the logic of prefixing weathers inside `Weather`; make minimal changes
        List<String> list = new ArrayList<>();
        Weather.printWeathers(list::add).orTimeout(5, TimeUnit.SECONDS).join();
        System.out.println(Arrays.toString(list.toArray()));
        assertThat(list, containsInAnyOrder("Today: Sunny", "Yesterday: Cloudy"));
    }
}

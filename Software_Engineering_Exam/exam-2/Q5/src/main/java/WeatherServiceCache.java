import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeatherServiceCache implements WeatherAPI {
    private final WeatherAPI baseApi;

    private final Map<String, String> cache;

    public WeatherServiceCache(WeatherAPI baseApi) {
        this.baseApi = baseApi;
        this.cache = new HashMap<>();

    }

    @Override
    public String getWeather(String city) throws ServiceNotAvailableException {
        if (cache.containsKey(city))
            return cache.get(city);

        updateWeather(city);
        return cache.get(city);
    }

    private void updateWeather(String city) throws ServiceNotAvailableException {
        String weather = baseApi.getWeather(city);
        cache.put(city, weather);
    }
}

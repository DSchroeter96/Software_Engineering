public final class App {
    public static void main(String[] args) {
        HttpClient client = new RealHttpClient();
        WeatherService weatherService = new WeatherService(client);
        Weather weather = weatherService.getWeatherToday();
        System.out.println("Hello World!");
        System.out.println("The weather today is " + weather);
    }
}

import java.net.HttpURLConnection;

public class App {
    public static void main(String[] args) {
        JokeFetcher fetcher = new JokeFetcher();
        fetcher.initConnection("R7UfaahVfFd");
        fetcher.printJokeText();
    }
}

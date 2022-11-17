import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class App {
    // EXERCISE: Transform this basic app to use MVP instead of mixing user interface and business logic together
    //           Remember, MVP is "Model-View-Presenter", the View and Model should be completely decoupled (neither knows about the other),
    //           and the Presenter should only know about the interface of the View and Model.
    //           The View handles user input and forwards it to the Presenter, who then internally uses the Model to get data and updates the View.
    //           Your "main" method will be something like "new Presenter(new RandomModel(), new ConsoleView()).run();"
    //           You can run this app on the command line with `gradlew.bat run` on Windows or `./gradlew run` on macOS and Linux.

    public static void main(String[] args) {
        new Presenter(new Retry(new RandomModel()), new ConsoleView()).run();
    }
}

interface Model {
    Weather getForecast();
}

interface View {
    void run(Presenter presenter);
    void show(String string);
}

enum Weather {
    SUNNY("Sunny"), RAINY("Rainy"), UNKNOWN("???");

    private final String s;

    Weather(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}

class RandomModel implements Model {

    @Override
    public Weather getForecast() {
        int random = ThreadLocalRandom.current().nextInt(0, 4);
        if (random == 0) return Weather.SUNNY;
        else if (random == 1) return Weather.RAINY;
        else return Weather.UNKNOWN;
    }
}

class Retry implements Model {
    private Model model;

    public Retry(Model model) {
        this.model = model;
    }

    @Override
    public Weather getForecast() {
        Weather weather;
        do {
            weather = model.getForecast();
        } while (weather.equals(Weather.UNKNOWN));
        return weather;
    }
}

class ConsoleView implements View {

    @Override
    public void run(Presenter presenter) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            scanner.nextLine();
            presenter.OnInput();
        }
    }

    @Override
    public void show(String s) {
        System.out.println(s);
    }
}

class Presenter {
    private final Model model;
    private final View view;

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.show("Hi! Hit Enter to check the weather forecast (ctrl+d to exit)");
        view.run(this);
    }

    public void OnInput() {
        Weather w = model.getForecast();
        view.show("Weather forecast: " + w);
    }

}

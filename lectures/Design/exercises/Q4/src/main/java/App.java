import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        compute();
    }

    public static List<Double> compute() throws FileNotFoundException {
        List<Double> numbers = loadFromFile("data");
        List<Double> normalized = normalized(numbers);
        writeResults(normalized, "output");
        System.out.println(normalized);
        return normalized;
    }

    private static double mean(List<Double> numbers) {
        double sum = 0;
        for (double f : numbers) sum += f;
        return sum / numbers.size();
    }

    private static double variance(List<Double> numbers) {
        double mean = mean(numbers);
        double sum = 0;
        for (double f : numbers) {
            double deviation = f - mean;
            sum += deviation * deviation;
        }
        return sum / numbers.size();
    }

    private static List<Double> normalized(List<Double> numbers) {
        double standardDeviation = Math.sqrt(variance(numbers));
        double mean = mean(numbers);
        List<Double> normalized = new ArrayList<>();
        for (double f : numbers) normalized.add((f - mean)/standardDeviation);
        return normalized;
    }

    private static List<Double> loadFromFile(final String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        List<Double> content = new ArrayList<>();
        while (scanner.hasNext()) content.add(scanner.nextDouble());
        scanner.close();
        return content;
    }
    
    private static void writeResults(List<Double> normalized, String outName) {
        try (FileWriter writer = new FileWriter(outName)) {
            for (double f : normalized) writer.write(String.format("%f\n", f));
        } catch (IOException e) {
            System.out.println("Error writing output file");
        } finally {
            System.out.println("Wrote output file.");
        }
    }

}

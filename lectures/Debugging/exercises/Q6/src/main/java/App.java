import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            System.out.println("usage : -i <input> -o <output> [-v]");
            System.exit(1);
        }

        if (!args[0].equals("-i")) {
            System.out.println("missing input file");
            System.exit(1);
        }
        String inputFileName = args[1];

        if (!args[2].equals("-o")) {
            System.out.println("missing output file");
            System.exit(1);
        }
        String outputFileName = args[3];

        final boolean verbose = args.length == 5 && args[4].equals("-v");

        sanitizeCsv(inputFileName, outputFileName, verbose);
    }

    public static void sanitizeCsv(String inputFileName, String outputFileName, boolean verbose) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName));
            stream.forEach(line -> {
                String[] elems = line.split(",");
                if (elems.length != 4) {
                    log("The line doesn't contains 4 elements", verbose);
                    return;
                }

                StringJoiner builder = new StringJoiner(",");
                if (!elems[0].matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")) {
                    log("The datetime format doesn't match the required format", verbose);
                    return;
                }
                builder.add(elems[0]);

                if (!elems[1].matches("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {//"/^((2([0-4][0-9]|5[0-5]){0,1})|1?([0-9]{1,2}))(\\.(2([0-4][0-9]|5[0-5]){0,1})|\\.1?([0-9]{1,2})){3}$/g")) {
                    log("The ip address is not well formatted", verbose);
                    return;
                }
                builder.add(elems[1]);
                builder.add(elems[2]);
                if (!elems[3].matches("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$")) {
                    log("The http link is not well formatted", verbose);
                    return;
                }
                builder.add(elems[3]);
                try {
                    writer.write(builder.toString());
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.close();
        }
    }

    private static void log(String message, boolean verbose) {
        if (!verbose) return;
        System.out.println(message);
    }
}


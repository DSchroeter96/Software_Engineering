import java.util.*;
import java.util.stream.Collectors;

public class App {
    // EXERCISE: This code is not modular and mixes all kinds of concepts together,
    //           making it hard to maintain.
    //           Modularize it!
    //           Think about what modules you need, which in this case will be functions or classes, and how to organize them.
    //           You may want to start by writing your ideal "main" method that reads like self-describing code,
    //           and implementing each function as needed.
    //           You can run this app on the command line with `gradlew.bat run` on Windows or `./gradlew run` on macOS and Linux.

    public static void main(String[] args) {

        while (true) {

            var text = getInput();
            if (text == null) {
                break;
            }

            try {
                int result = performComputation(parsInput(text));
                System.out.printf("The result is %d\n", result);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getInput() {
        var scanner = new Scanner(System.in);
        // "Reverse Polish notation", also known as "postfix", means the operator last, e.g., "1 1 + 2 *" for "(1 + 1) * 2"
        System.out.print("Computation to perform? (in reverse Polish notation; or 'exit') ");
        var text = scanner.nextLine().trim();
        if (text.equals("exit")) {
            return null;
        }
        return text;
    }

    private static List<Token> parsInput(String input) throws NumberFormatException {
        return Arrays.stream(input.split(" ")).map(s ->  switch (s) {
            case "+" -> BinaryOperations.Plus;
            case "-" -> BinaryOperations.Minus;
            case "*" -> BinaryOperations.Times;
            case "/" -> BinaryOperations.Div;
            default -> {
                try {
                    yield new IntLitToken(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    throw new InvalidComputationException("Computation invalid");
                }
            }
        }).collect(Collectors.toList());
    }

    private static int performComputation(List<Token> tokens) throws NumberFormatException {
        var stack = new ArrayDeque<Integer>();
        for (Token t: tokens) {
            if (t instanceof BinaryOperations t1) {
                var b = stack.pop();
                var a = stack.pop();
                stack.push(executeOp(t1, a, b));
            } else if (t instanceof IntLitToken t2) {
                stack.push((t2).nb());
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new InvalidComputationException("Invalid computation");
        }
    }

    private static Integer executeOp(BinaryOperations op, int a, int b) {
        return switch (op) {
            case Plus -> a + b;
            case Minus -> a - b;
            case Times -> a * b;
            case Div -> a / b;
        };
    }
}

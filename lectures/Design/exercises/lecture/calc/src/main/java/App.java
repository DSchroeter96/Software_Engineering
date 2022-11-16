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
        return Arrays.stream(input.split(" ")).map(s -> { switch (s) {
            case "+" : return BinaryOperations.Plus;
            case "-" : return BinaryOperations.Minus;
            case "*" : return BinaryOperations.Times;
            case "/" : return BinaryOperations.Div;
            default : {
                try {
                    return new IntLitToken(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    throw new InvalidComputationException("Computation invalid");
                }
            }
        }}).collect(Collectors.toList());
    }

    private static int performComputation(List<Token> tokens) throws NumberFormatException {
        var stack = new ArrayDeque<Integer>();
        for (Token t: tokens) {
            if (t instanceof BinaryOperations) {
                var b = stack.pop();
                var a = stack.pop();
                stack.push(executeOp((BinaryOperations) t, a, b));
            } else if (t instanceof IntLitToken) {
                stack.push(((IntLitToken) t).nb());
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new InvalidComputationException("Invalid computation");
        }
    }

    private static Integer executeOp(BinaryOperations op, int a, int b) {
        int ret = 0;
        switch (op) {
            case Plus :
                ret = a + b;
                break;
            case Minus :
                ret = a - b;
                break;
            case Times :
                ret = a * b;
                break;
            case Div :
                ret = a / b;
                break;
        }
        return ret;
    }
}

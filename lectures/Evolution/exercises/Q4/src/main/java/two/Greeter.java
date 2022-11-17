package two;

import java.util.PrimitiveIterator;
import java.util.Scanner;
import java.util.StringJoiner;

public class Greeter {

    private static final String FST_NAME_REQ = "Please enter your first name:";
    private static final String LAST_NAME_REQ = "Please enter your last name:";
    private static final String AGE_REQ = "Please enter your age:";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String ageFmt = "(%s years old)";

    private static String ask(String question) {
        System.out.println(question);
        return SCANNER.next();
    }

    private static void print(String... s) {
        StringJoiner builder = new StringJoiner(" ", "Nice to meet you ", "");
        for (String s1 : s)
            builder.add(s1);
        System.out.println(builder);
    }

    // prints: Nice to meet you Willy
    public static void askForFirstName() {
        print(ask(FST_NAME_REQ));
    }

    // prints: Nice to meet you Wonka
    public static void askForLastName() {
        print(ask(LAST_NAME_REQ));
    }

    // prints: Nice to meet you Willy Wonka
    public static void askForFullName() {
        print(ask(FST_NAME_REQ), ask(LAST_NAME_REQ));
    }

    // prints: Nice to meet you Willy Wonka (23 years old)
    public static void askForFullNameAndAge() {
        print(ask(FST_NAME_REQ), ask(LAST_NAME_REQ), String.format(ageFmt, ask(AGE_REQ)));
    }
}
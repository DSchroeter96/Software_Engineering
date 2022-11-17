import example.CSVReader;
import example.Student;
import example.StudentCache;

import java.util.stream.Collectors;

public final class App {

    public static void main(String[] args) {
        var reader = new CSVReader("res/students.txt", new StudentCache());
        var emails = reader.read(10).stream()
            .map(Student::getEmail)
            .collect(Collectors.toList());
        System.out.println(emails);
    }
}

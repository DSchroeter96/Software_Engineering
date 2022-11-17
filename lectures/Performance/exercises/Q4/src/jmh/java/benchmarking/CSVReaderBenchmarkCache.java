package benchmarking;

import example.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class CSVReaderBenchmarkCache {

    //@State(Scope.Benchmark)
    //public static class BenchmarkingState {
    //    public CSVReader reader;
    //    public int nbLines;
//
    //    @Setup(Level.Trial)
    //    public void initLIne() {
    //        reader = new CSVReader("res/students.txt", new StudentCache());
    //        nbLines = 1000;
    //    }
    //}

    //@Benchmark
    public void readerCache(Blackhole blackhole) {
        CSVReader reader = new CSVReader("res/students.txt", new StudentCache());
        blackhole.consume(reader.read(1000));
    }

    //@Benchmark
    public void readerNoCache(Blackhole blackhole) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        blackhole.consume(reader.read(1000));
    }

    //@Benchmark
    public void sequentialBenchmark(Blackhole blackhole) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        List<Student> students = new ArrayList<>(reader.read(1000));
        for (int i = 0; i < 1000; ++i) {
            students.addAll(reader.read(1000));
        }
        blackhole.consume(students.stream().map(Student::getEmail).collect(Collectors.toList()));
    }

    //@Benchmark
    public void parallelBenchmark(Blackhole blackhole) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        List<Student> students = new ArrayList<>(reader.read(1000));
        for (int i = 0; i < 1000; ++i) {
            students.addAll(reader.read(1000));
        }
        blackhole.consume(students.stream().parallel().map(Student::getEmail).collect(Collectors.toList()));
    }

    //@Benchmark
    public void listBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        List<Student> students = reader.read(1000);
        for (int i = 0; i < 1000; ++i) {
            bh.consume(students.contains(new Student("Adlai", "Bodicum", "abodicumom@cpanel.net", "Konyang University")));
        }
    }

    //@Benchmark
    public void setBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        Set<Student> students = new HashSet<>(reader.read(1000));
        for (int i = 0; i < 1000; ++i) {
            bh.consume(students.contains(new Student("Adlai", "Bodicum", "abodicumom@cpanel.net", "Konyang University")));
        }
    }

    //@Benchmark
    public void iterateLinkedListGetBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        List<Student> students = new LinkedList<>(reader.read(1000));
        for (int i = 0; i < 1000; ++i) {
            bh.consume(students.get(i));
        }
    }

    //@Benchmark
    public void iterateLinkedListForeachBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        List<Student> students = new LinkedList<>(reader.read(1000));
        for (final Student s : students) {
            bh.consume(s);
        }
    }

    @Benchmark
    public void iterateArrayListGetBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        List<Student> students = reader.read(1000);
        for (int i = 0; i < 1000; ++i) {
            bh.consume(students.get(i));
        }
    }

    @Benchmark
    public void iterateArrayListForeachBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt", new UselessCache());
        List<Student> students = reader.read(1000);
        for (final Student s : students) {
            bh.consume(s);
        }
    }

}

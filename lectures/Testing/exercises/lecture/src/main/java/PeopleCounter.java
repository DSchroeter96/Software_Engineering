public class PeopleCounter {
    // EXERCISE: Design a "people counter" with TDD.
    // The counter:
    // - starts at 0
    // - has a configurable max
    // - can increment (+1)
    // - can reset (=0)

    private int counter;
    private int max;

    public PeopleCounter(int max) {
        this.max = max;
    }

    public void increment() {
        counter++;
    }

    public void reset() {
        counter = 0;
    }

    public int counter() {
        return counter;
    }

    public int max() {
        return max;
    }

    public void newMax(int max) {
        if (max < 0)
            throw new IllegalArgumentException();
        this.max = max;
    }
}

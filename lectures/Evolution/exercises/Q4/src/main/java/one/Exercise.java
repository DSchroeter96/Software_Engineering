package one;

public abstract class Exercise {
    final String name;

    public Exercise(String name) {
        this.name = name;
    }

    public abstract boolean requiresWeights();

    public void doExercise() {
        System.out.println("Wow, I totally just did a " + name);
    }
}

package one;

public abstract class GymExercise extends Exercise {

    final int weight;

    public GymExercise(String name, int weight) {
        super(name);
        this.weight = weight;
    }

    @Override
    public boolean requiresWeights() {
        return true;
    }

    public int getWeight() {
        return weight;
    }
}

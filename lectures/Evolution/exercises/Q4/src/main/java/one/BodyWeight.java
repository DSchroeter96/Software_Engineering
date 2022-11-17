package one;

public abstract class BodyWeight extends Exercise {

    public BodyWeight(String name) {
        super(name);
    }

    @Override
    public boolean requiresWeights() {
        return false;
    }
}

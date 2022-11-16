public class IntLitToken implements Token {
    private final int nb;

    public IntLitToken(int nb) {
        this.nb = nb;
    }

    public int nb() {
        return nb;
    }
}

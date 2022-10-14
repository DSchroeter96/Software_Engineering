import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Write the specification here
 */
public final class Vector3 {
    private final static double THRESHOLD_ZERO = 1e-8;
    private final double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean isZero() {
        return this.x < THRESHOLD_ZERO && this.y < THRESHOLD_ZERO && this.z < THRESHOLD_ZERO;
    }

    public double dot(Vector3 that) {
        if (that == null)
            throw new IllegalArgumentException();
        return this.x * that.x + this.y * that.y + this.z * that.z;
    }

    public double norm() {
        return Math.sqrt(this.dot(this));
    }

    public Vector3 normalized() {
        final double norm = this.norm();
        return this.scaled(1/norm);
    }

    public Vector3 scaled(double factor) {
        return apply(curVal -> factor * curVal);
        //return new Vector3(this.x * factor, this.y * factor, this.z * factor);
    }

    public Vector3 cross(Vector3 that) {
        if (that == null)
            throw new IllegalArgumentException();
        double x = this.y*that.z - this.z*that.y;
        double y = this.z*that.x - this.x*that.z;
        double z = this.x*that.y - this.y*that.x;
        return new Vector3(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof final Vector3 that))
            return false;

        return this.x - that.x < THRESHOLD_ZERO && this.y - that.y < THRESHOLD_ZERO && this.z - that.z < THRESHOLD_ZERO;
    }

    private Vector3 apply(Function<Double, Double> fn) {
        final double x = fn.apply(this.x);
        final double y = fn.apply(this.y);
        final double z = fn.apply(this.z);

        return new Vector3(x, y, z);
    }
}

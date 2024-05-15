public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        double mag = magnitude();
        return new Vector3(x / mag, y / mag, z / mag);
    }

    public double dot(Vector3 other) {
        return x * other.getX() + y * other.getY() + z * other.getZ();
    }

    public Vector3 multiply(double scalar) {
        return new Vector3(x * scalar, y * scalar, z * scalar);
    }

    public Vector3 subtract(Vector3 other) {
        return new Vector3(x - other.getX(), y - other.getY(), z - other.getZ());
    }

    public Vector3 reflect(Vector3 normal) {
        // Reflect the vector across the normal vector
        double dotProduct = this.dot(normal);
        return this.subtract(normal.multiply(2 * dotProduct));
      }
}
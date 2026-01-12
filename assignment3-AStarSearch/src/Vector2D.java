package mibi.assignment3.src;

public class Vector2D {
  public double x, y;

  public Vector2D() {
    this(0d, 0d);
  }

  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vector2D add(Vector2D other) {
    return new Vector2D(this.x + other.x, this.y + other.y);
  }

  public Vector2D subtract(Vector2D other) {
    return new Vector2D(this.x - other.x, this.y - other.y);
  }

  public Vector2D scale(double scalar) {
    return new Vector2D(this.x * scalar, this.y * scalar);
  }

  public double abs() {
    return Math.sqrt(x*x + y*y);
  }

  public boolean equals(Vector2D other){
    return this.x == other.x && this.y == other.y;
  }
}

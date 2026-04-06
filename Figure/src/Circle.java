public class Circle extends AbstractFigure {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double Area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double Perimetr() {
        return 2 * Math.PI * radius;
    }
}
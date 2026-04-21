public class Rectangle extends AbstractFigure {
    protected double width;
    private final double length;

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    @Override
    public double Area() {
        return width * length;
    }

    @Override
    public double Perimetr() {
        return 2 * (width + length);
    }
} https://github.com/hcfgtrup/alg-task2/pull/1
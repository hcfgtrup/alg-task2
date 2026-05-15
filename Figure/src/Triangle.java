public class Triangle extends AbstractFigure {
    private final double side_a;
    private final double side_b;
    private final double side_c;

    public Triangle(double side_a, double side_b, double side_c) {
        this.side_a = side_a;
        this.side_b = side_b;
        this.side_c = side_c;
    }

    @Override
    public double Area() {
        double p = Perimetr() / 2;
        return Math.sqrt(p * (p - side_a) * (p - side_b) * (p - side_c));
    }

    @Override
    public double Perimetr() {
        return side_a + side_b + side_c;
    }

    public boolean existence() {
        return (side_a + side_b > side_c) &&
                (side_b + side_c > side_a) &&
                (side_c + side_a > side_b);
    }
}
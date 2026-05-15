public abstract class AbstractFigure {

    public abstract double Area();
    public abstract double Perimetr();

    public void Output() {
        System.out.printf("Площадь: %.2f%n", Area());
        System.out.printf("Периметр: %.2f%n", Perimetr());
    }
}
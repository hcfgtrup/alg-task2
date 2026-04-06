public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    public double getSide() {
        return width;
    }

    @Override
    public void Output() {
        System.out.println("Сторона квадрата " + getSide());
        super.Output();
    }
}
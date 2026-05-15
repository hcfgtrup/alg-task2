import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите фигуру: ");
        String input = scanner.nextLine().toLowerCase();

        Kind kind = Kind.fromRussianName(input);

        if (kind != null) {
            kind.process(scanner);
        } else {
            System.out.println("Неизвестная фигура");
        }

        scanner.close();
    }
}
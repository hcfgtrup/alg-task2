import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int carCounter = 1;

        System.out.println("Узкий мост");
        System.out.println("Команды:");
        System.out.println("N <имя> - добавить машину с севера");
        System.out.println("S <имя> - добавить машину с юга");
        System.out.println("exit - завершить программу");
        System.out.println();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length >= 1) {
                String direction = parts[0].toUpperCase();
                String name = (parts.length >= 2) ? parts[1] : "Car" + carCounter;

                if (direction.equals("N") || direction.equals("S")) {
                    new Car(name, direction).start();
                    System.out.println("Добавлена машина " + name + " (" + direction + ")");
                    carCounter++;
                } else {
                    System.out.println("Ошибка. Используйте: N <имя> или S <имя>");
                }
            }
        }
        scanner.close();
    }
}
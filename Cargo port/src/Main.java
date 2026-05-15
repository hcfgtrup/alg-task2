import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int shipCounter = 1;

        System.out.println("Грузовой порт");
        System.out.println("Количество причалов: 2");
        System.out.println("Команды:");
        System.out.println("add <имя> - добавить судно");
        System.out.println("list - показать очередь ожидания");
        System.out.println("exit - завершить программу");
        System.out.println();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена");
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println("Очередь судов: " + Ship.getWaitingQueue());
                continue;
            }

            if (input.toLowerCase().startsWith("add")) {
                String[] parts = input.split(" ");
                String name = (parts.length >= 2) ? parts[1] : "Ship" + shipCounter;
                new Ship(name).start();
                System.out.println("Судно " + name + " прибыло в порт");
                shipCounter++;
            } else {
                System.out.println("Ошибка. Используйте: add <имя>, list или exit");
            }
        }
        scanner.close();
    }
}
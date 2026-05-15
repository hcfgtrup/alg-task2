import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Команды:");
        System.out.println("add - добавить элемент");
        System.out.println("remove - удалить элемент");
        System.out.println("contains - проверить наличие элемента");
        System.out.println("print - вывести все элементы (обход)");
        System.out.println("size - показать размер");
        System.out.println("clear - очистить коллекцию");
        System.out.println("help - показать команды");
        System.out.println("exit - выход");
        System.out.println();

        while (true) {
            System.out.print("> ");
            String command = scanner.next();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

            else if (command.equalsIgnoreCase("help")) {
                System.out.println("Команды:");
                System.out.println("add - добавить элемент");
                System.out.println("remove - удалить элемент");
                System.out.println("contains - проверить наличие элемента");
                System.out.println("print - вывести все элементы (обход)");
                System.out.println("size - показать размер");
                System.out.println("clear - очистить коллекцию");
                System.out.println("help - показать команды");
                System.out.println("exit - выход");
            }

            else if (command.equalsIgnoreCase("add")) {
                if (scanner.hasNextInt()) {
                    int value = scanner.nextInt();
                    boolean result = tree.add(value);
                    if (result) {
                        System.out.println("Добавлено: " + value);
                    } else {
                        System.out.println("Элемент " + value + " уже существует");
                    }
                } else {
                    System.out.println("Ошибка: Введите число");
                    scanner.next();
                }
            }

            else if (command.equalsIgnoreCase("remove")) {
                if (scanner.hasNextInt()) {
                    int value = scanner.nextInt();
                    boolean result = tree.remove(value);
                    if (result) {
                        System.out.println("Удалено: " + value);
                    } else {
                        System.out.println("Элемент " + value + " не найден");
                    }
                } else {
                    System.out.println("Ошибка: Введите число");
                    scanner.next();
                }
            }

            else if (command.equalsIgnoreCase("contains")) {
                if (scanner.hasNextInt()) {
                    int value = scanner.nextInt();
                    boolean result = tree.contains(value);
                    if (result) {
                        System.out.println("Да, элемент " + value + " есть в дереве");
                    } else {
                        System.out.println("Нет, элемент " + value + " не найден");
                    }
                } else {
                    System.out.println("Ошибка: Введите число");
                    scanner.next();
                }
            }

            else if (command.equalsIgnoreCase("print")) {
                if (tree.isEmpty()) {
                    System.out.println("Дерево пустое");
                } else {
                    System.out.print("Элементы(в отсортированном порядке): ");
                    for (int val : tree) {
                        System.out.print(val + " ");
                    }
                    System.out.println();
                }
            }

            else if (command.equalsIgnoreCase("size")) {
                System.out.println("Размер дерева: " + tree.size());
            }

            else if (command.equalsIgnoreCase("clear")) {
                tree.clear();
                System.out.println("Дерево очищено");
            }

            else {
                System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
            }
        }
        scanner.close();
    }
}
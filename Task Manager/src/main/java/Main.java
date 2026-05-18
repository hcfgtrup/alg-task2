import manager.TaskManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Запуск системы обработки заказов ===\n");

        TaskManager manager = new TaskManager(10, 2, 3, 15);

        manager.start();

        try {
            Thread.sleep(5000);
            manager.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Program interrupted: " + e.getMessage());
        }

        System.out.println("\n=== Результаты ===");
        System.out.println("Обработано заказов: " + manager.getTotalProcessed());
        System.out.println("Срочных: " + manager.getUrgentCount());
        System.out.println("Обычных: " + manager.getOrdinaryCount());
        System.out.println("Уникальных заказов в хранилище: " + manager.getProcessedOrders().size());
    }
}
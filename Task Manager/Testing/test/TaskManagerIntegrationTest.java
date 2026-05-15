package test;

import manager.TaskManager;
import model.Order;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerIntegrationTest {

    @Test
    void producerConsumerWorkCorrectly() throws InterruptedException {
        TaskManager manager = new TaskManager(5, 1, 2, 10);
        manager.start();

        Thread.sleep(3000);
        manager.shutdown();

        assertTrue(manager.getTotalProcessed() > 0);
        assertEquals(manager.getProcessedOrders().size(), manager.getTotalProcessed());
    }

    @Test
    void allOrdersAreValidAndProcessed() throws InterruptedException {
        TaskManager manager = new TaskManager(10, 2, 3, 20);
        manager.start();

        Thread.sleep(4000);
        manager.shutdown();

        ConcurrentHashMap<String, Order> processed = manager.getProcessedOrders();
        for (Order order : processed.values()) {
            assertNotNull(order.getId());
            assertNotNull(order.getDescription());
            assertNotNull(order.getType());
        }
    }

    @Test
    void urgentOrdersCountIsReasonable() throws InterruptedException {
        TaskManager manager = new TaskManager(10, 1, 2, 30);
        manager.start();

        Thread.sleep(4000);
        manager.shutdown();

        int total = manager.getTotalProcessed();
        int urgent = manager.getUrgentCount();
        assertTrue(urgent >= 0 && urgent <= total);
    }
}
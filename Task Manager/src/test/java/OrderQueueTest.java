import queue.OrderQueue;
import model.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderQueueTest {

    @Test
    void queueShouldWorkCorrectly() throws InterruptedException {
        OrderQueue queue = new OrderQueue(5);

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        Order order = new Order("Test", false);
        queue.put(order);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());

        Order taken = queue.take();
        assertEquals(order.getId(), taken.getId());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
}
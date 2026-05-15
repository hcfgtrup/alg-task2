package queue;

import model.Order;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderQueue {
    private final BlockingQueue<Order> queue;

    public OrderQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    public void put(Order order) throws InterruptedException {
        queue.put(order);
    }

    public Order take() throws InterruptedException {
        return queue.take();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
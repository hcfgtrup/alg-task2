package producer;

import model.Order;
import queue.OrderQueue;
import java.util.concurrent.atomic.AtomicLong;

public class OrderProducer implements Runnable {
    private final OrderQueue queue;
    private final AtomicLong orderCounter = new AtomicLong(0);
    private volatile boolean running = true;
    private final int ordersToProduce;
    private int produced = 0;

    public OrderProducer(OrderQueue queue, int ordersToProduce) {
        this.queue = queue;
        this.ordersToProduce = ordersToProduce;
    }

    @Override
    public void run() {
        while (running && produced < ordersToProduce) {
            try {
                boolean isUrgent = (produced % 3 == 0);
                Order order = new Order("Order #" + orderCounter.incrementAndGet(), isUrgent);
                queue.put(order);
                produced++;
                System.out.println(Thread.currentThread().getName() + " PRODUCED: " + order);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished producing.");
    }

    public void stop() {
        running = false;
    }
}
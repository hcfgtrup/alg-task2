package consumer;

import model.Order;
import queue.OrderQueue;
import validator.OrderValidator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderConsumer implements Runnable {
    private final OrderQueue queue;
    private final ConcurrentHashMap<String, Order> processedOrders;
    private final AtomicInteger urgentCount;
    private final AtomicInteger ordinaryCount;
    private volatile boolean running = true;

    public OrderConsumer(OrderQueue queue,
                         ConcurrentHashMap<String, Order> processedOrders,
                         AtomicInteger urgentCount,
                         AtomicInteger ordinaryCount) {
        this.queue = queue;
        this.processedOrders = processedOrders;
        this.urgentCount = urgentCount;
        this.ordinaryCount = ordinaryCount;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Order order = queue.take();
                if (OrderValidator.validate(order)) {
                    Thread.sleep(order.isUrgent() ? 50 : 150);
                    processedOrders.put(order.getId(), order);
                    if (order.isUrgent()) {
                        urgentCount.incrementAndGet();
                    } else {
                        ordinaryCount.incrementAndGet();
                    }
                    System.out.println(Thread.currentThread().getName() + " PROCESSED: " + order);
                } else {
                    System.err.println(Thread.currentThread().getName() + " REJECTED invalid order: " + order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " stopped.");
    }

    public void stop() {
        running = false;
    }
}
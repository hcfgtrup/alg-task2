package manager;

import consumer.OrderConsumer;
import model.Order;
import producer.OrderProducer;
import queue.OrderQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskManager {
    private final OrderQueue queue;
    private final ExecutorService producerExecutor;
    private final ExecutorService consumerExecutor;
    private final List<OrderProducer> producers;
    private final List<OrderConsumer> consumers;
    private final ConcurrentHashMap<String, Order> processedOrders;
    private final AtomicInteger urgentCount;
    private final AtomicInteger ordinaryCount;

    public TaskManager(int queueCapacity, int producerCount, int consumerCount, int ordersPerProducer) {
        this.queue = new OrderQueue(queueCapacity);
        this.processedOrders = new ConcurrentHashMap<>();
        this.urgentCount = new AtomicInteger(0);
        this.ordinaryCount = new AtomicInteger(0);

        this.producerExecutor = Executors.newFixedThreadPool(producerCount);
        this.consumerExecutor = Executors.newFixedThreadPool(consumerCount);

        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();

        for (int i = 0; i < producerCount; i++) {
            producers.add(new OrderProducer(queue, ordersPerProducer));
        }
        for (int i = 0; i < consumerCount; i++) {
            consumers.add(new OrderConsumer(queue, processedOrders, urgentCount, ordinaryCount));
        }
    }

    public void start() {
        for (OrderProducer producer : producers) {
            producerExecutor.submit(producer);
        }
        for (OrderConsumer consumer : consumers) {
            consumerExecutor.submit(consumer);
        }
    }

    public void shutdown() throws InterruptedException {
        producerExecutor.shutdown();
        consumerExecutor.shutdown();

        if (!producerExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
            producerExecutor.shutdownNow();
        }

        for (OrderConsumer consumer : consumers) {
            consumer.stop();
        }

        if (!consumerExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
            consumerExecutor.shutdownNow();
        }
    }

    public ConcurrentHashMap<String, Order> getProcessedOrders() {
        return processedOrders;
    }

    public int getUrgentCount() {
        return urgentCount.get();
    }

    public int getOrdinaryCount() {
        return ordinaryCount.get();
    }

    public int getTotalProcessed() {
        return urgentCount.get() + ordinaryCount.get();
    }
}
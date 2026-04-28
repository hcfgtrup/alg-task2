import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Ship extends Thread {
    private final String name;
    private static final int BERTHS_COUNT = 2;
    private static final Semaphore berths = new Semaphore(BERTHS_COUNT);
    private static final ConcurrentLinkedQueue<String> waitingQueue = new ConcurrentLinkedQueue<>();

    public Ship(String name) {
        this.name = name;
    }

    public static ConcurrentLinkedQueue<String> getWaitingQueue() {
        return waitingQueue;
    }

    @Override
    public void run() {
        try {
            waitingQueue.add(name);
            System.out.println(name + " в очереди. Очередь: " + waitingQueue);

            berths.acquire();
            waitingQueue.remove(name);
            System.out.println(name + " у причала. Свободно причалов: " + berths.availablePermits());

            int processingTime = 1000 + (int)(Math.random() * 1000);
            Thread.sleep(processingTime);

            System.out.println(name + " освободил причал (время обработки: " + processingTime + "мс)");
            berths.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
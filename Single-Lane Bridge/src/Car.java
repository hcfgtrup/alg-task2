public class Car extends Thread {
    private final String id;
    private final String direction;
    private static String currentDirection = null;
    private static int carsOnBridge = 0;
    private static final Object lock = new Object();

    public Car(String id, String direction) {
        this.id = id;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                while (currentDirection != null && !currentDirection.equals(direction)) {
                    lock.wait();
                }
                currentDirection = direction;
                carsOnBridge++;
            }

            System.out.println(id + " (" + direction + ") на мосту");
            Thread.sleep(500);

            synchronized (lock) {
                carsOnBridge--;
                System.out.println(id + " (" + direction + ") покинул мост");
                if (carsOnBridge == 0) {
                    currentDirection = null;
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
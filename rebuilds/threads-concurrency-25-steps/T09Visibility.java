public class T09Visibility{
    private static volatile boolean running = true;
    private static volatile int counter = 0;
    public static void main(String[] args) throws InterruptedException{
        Thread worker = new Thread(() -> {
            long spins = 0;
            while(running) spins++;
            System.out.println("stopped " + (spins > 0));
        }, "worker");
        worker.start();
        Thread.sleep(100);
        running = false;
        worker.join(1000);
        System.out.println("alive " + worker.isAlive());
        Thread[] ts = new Thread[4];
        for(int i = 0; i < 4; i++) ts[i] = new Thread(() -> {for(int j =0; j < 50_000; j++)counter++;});
        for(Thread t : ts) t.start();
        for(Thread t : ts) t.join();
        System.out.println("volatile lost " + (200_000 - counter));
        
    }
}
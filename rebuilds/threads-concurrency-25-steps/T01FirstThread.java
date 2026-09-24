public class T01FirstThread{
    public static void main(String[] args) throws InterruptedException{
        System.out.println("in " + Thread.currentThread().getName() + "Thread ");
        Runnable work = () -> System.out.println("in " + Thread.currentThread().getName());
        Thread worker = new Thread(work, "worker");
        worker.start();
        worker.join();
        System.out.println("done");

    }
}
public class T07Interrupt{
    public static void main(String[] args) throws InterruptedException{
        Thread busy = new Thread(() -> {long spins = 0;
        while(!Thread.currentThread().isInterrupted()) spins++;
        System.out.println("busy stopped " + (spins > 0));}, "busy");
        busy.start();
        Thread.sleep(60);
        busy.interrupt();
        busy.join();
        Thread parked = new Thread(() -> {
            try{
                Thread.sleep(5_000);
            }catch(InterruptedException e){
                System.out.println("flags cleared " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("flags restored " + Thread.currentThread().isInterrupted());
            }
        }"parked");
        parked.start();
        Thread.sleep(60);
        parked.interrupt();
        parked.join();
        System.out.println("done");
        
    }
}
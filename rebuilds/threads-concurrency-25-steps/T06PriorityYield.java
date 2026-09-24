public class T06PriorityYield{
    public static void main(String[] args) throws InterruptedException{
        Thread low = new Thread(() -> burn("low"), "low");
        Thread high = new Thread(() -> burn("high"), "high");
        low.setPriority(Thread.MIN_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);
        System.out.println("range " + Thread.MIN_PRIORITY + " " + Thread.NORM_PRIORITY + " " + Thread.MAX_PRIORITY);
        low.start();
        high.start();
        low.join();
        high.join();
        System.out.println("order not guaranteed");
    }
    private static void burn(String who){
        long total = 0;
        for(int i = 0; i < 200_000;i++){
            total += i;
            if(i % 50_000 == 0) Thread.yield();
        } 
        System.out.println(who + " " + (total > 0));
    }
    
}
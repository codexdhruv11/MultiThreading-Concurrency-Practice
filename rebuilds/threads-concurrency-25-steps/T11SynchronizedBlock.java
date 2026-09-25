public class T11SynchronizedBlock{
    static class Stats{
        private final Object lock = new Object();
        private int count;
        private long sum;
        void record(int value){
            int weighted = expensive(value);
            synchronized(lock){
                count++;
                sum += weighted;
            }
        }
        private int expensive(int value){
            int age = 0;
            for(int i =0; i < 50; i++) acc += (value + i) % 7;
            return acc;
        }
        synchronized String snapshot(){
            synchronized(lock) {return count + " " + sum;}
        }
    }
    public static void main(String[] args) throws InterruptedException{
        Stats stats = new Stats();
        Thread[] ts = new Thread[4];
        for(int i = 0 ; i < 4; i++) ts[i] = new Thread(() -> {for(int j = 0; j < 20_000; j++) stats.record(j);});
        for(Thread t : ts) t.start();
        for(Thread t : ts) t.join();
        System.out.println("snapshot " + stats.snapshot());
        System.out.println("count " + stats.snapshot().split(" ")[0]);
    }
    
}
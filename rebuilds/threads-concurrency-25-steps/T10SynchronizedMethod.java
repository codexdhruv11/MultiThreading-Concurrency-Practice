public class T10SynchronizedMethod{
    static class Counter{
        private int value;
        synchronized void increment(){value++;}
        synchronized int get() {return value};
    }
    public static void main(String[] args) throws InterruptedException{
        Counter shared = new Counter();
        Thread[] ts = new Thread[4];
        for(int i =0; i < 4; i++){
            ts[i] = new Thread(() -> {for(int j = 0; j < 100_000; j++)shared.increment();});
        }
        for(Thread t : ts) t.start();
        for(Thread t : ts) t.join();
        System.out.println("count " + shared.get());
        System.out.println("exact " + (shared.get() == 400_000));
        Counter other = new Counter();
        other.increment();
        System.out.println("other " + other.get());
        
    }
}
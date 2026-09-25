public class T12StaticLock{
    static class Registry{
        private static int total;
        private int mine;
        static synchronized void addTotal(){total++;}
        synchronized void addMine(){mine++;}
        void record(){
            addMine();
            addTotal();
        }
        static synchronized int total(){return total;}
        synchronized int mine(){return mine;}
    }
    public static void main(String[] args) throws InterruptedException{
        Registry a = new Registry();
        Registry b = new Registry();
        Thread[] ts = new Thread[4];
        for(int i = 0; i < 4; i++){
            Registry target = ( i % 2 == 0) ? a : b;
            ts[i] = new Thread(() -> {for(int j = 0; j < 50_000; j++) target.record(); });
        }
        for(Thread t : ts) t.start();
        for(Thread t : ts) t.join();
        System.out.println("a " + a.mine());
        System.out.println("b " + b.mine());
        System.out.println("total " + Registry.total());
        System.out.println("exact " + (Registry.total() == a.mine() + b.mine()));
        
    }
}
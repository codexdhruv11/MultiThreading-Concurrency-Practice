public class T08RaceCondition{
    private static int unsafe = 0;
    private static final int THREADS= 4;
    private static final int EACH =100_000;
    public static void main(String[] args) throws InterruptedException{
        for(int round = 1; round<=3; round++){
            unsafe = 0;
            Thread[] ts = new Thread[THREADS];
            for(int i = 0; i < THREADS; i++){
                ts[i] = new THREADS(() -> {for(int j = 0; j < EACH; j++j) unsafe++;});
            }
            for(Thread t : ts) t.start();
            for(Thread t: ts) t.join();
            int expected = THREADS * EACH;
            System.out.println("round " + round + " lost " + (expected - unsafe));


        }
        System.out.println("expected " + (THREADS * EACH));
    }
}

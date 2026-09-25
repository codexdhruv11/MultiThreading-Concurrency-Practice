public class T13Deadlock{
    private static final Object FIRST = new Object();
    private static final Object SECOND = new Object();
    private static final Object THIRD = new Object();
    private static final Object FOURTH = new Object();
    public static void main(String[] args) throws InterruptedException{
        Thread ab = new Thread(() -> takeBoth(FIRST, SECOND, "ab"), "ab");
        Thread ba = new Thread(() -> takeBoth(SECOND,FIRST, "ba"),"ba");
        ab.start();
        ba.start();
        ab.join(400);
        ba.join(400);
        System.out.println("stuck " + (ab.isAlive() || ba.isAlive()));
        Thread one = new Thread(() -> takeBoth(THIRD, FOURTH, "one"),"one");
        Thread two = new Thread(() -> takeBoth(THIRD, FOURTH, "two"), "two");
        one.start();
        two.start();
        one.join(1000);
        System.out.println("ordered ok " + (!one.isAlive() && !two.isAlive()));
        System.exit(0);
    }
    private static void takeBoth(Object a, String who){
        synchronized (a){
            try{
                Thread.sleep(60); catch (InterruptedException e){
                    return ;
                } synchronized(b){
                    System.out.println(who + " got both");
                }
            }
        }
    }
}
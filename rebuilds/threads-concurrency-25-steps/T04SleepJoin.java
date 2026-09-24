public class T04SleepJoin{
    public static void main(String[] args) throws InterruptedException{
        Thread slow = new Thread(() -> {
            try{
                Thread.sleep(300);
                System.out.println("slow done");
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            } }, "slow");
            slow.start();
            System.out.println("alive " + slow.isAlive());
            slow.join(100);
            System.out.println("after 100ms alive " + slow.isAlive());
            slow.join();
            System.out.println("after join alive " + slow.isAlive());
            long started  = System.currentTimeMillis();
            Thread.sleep(150);
            System.out.println("slept " + (System.currentTimeMillis() - started >= 150));
            
    }
}

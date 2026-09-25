public class T05DaemonThread{
    public static void main(String[] args) throws InterruptedException{
        Thread heartbeat = new Thread(() ->{
            while(true){
                try{Thread.sleep(50);} catch(InterruptedException e){return;}
            }
        }, "heartbeat");
        heartbeat.setDaemon(true);
        heartbeat.start();
        System.out.println("daemon " + heartbeat.isDaemon());
        System.out.println("inherits " + new Thread(() -> {}).isDaemon());
        Thread worker = new Thread(() -> System.out.println("worker"  ), "worker");
        worker.start();
        worker.join();
        try{
            heartbeat.setDaemon(false);
        }catch(IllegalThreadStateException e){
            System.out.println("too late " + e.getClass().getSimpleName());
        }
        System.out.println("exit");
    }
}
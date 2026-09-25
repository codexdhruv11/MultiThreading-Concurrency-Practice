public class T14WaitNotify{
    static class Handoff{
        private final Object lock = new Object();
        private String message;
        void put(String value){
            synchronized(lock){
                while(message != null) waitQuietly();
                message = value;
                lock.notifyAll();
            }
        }
        String take(){
            synchronized (lock){
                while(message == null) waitQuietly();
                String value = message;
                message = null;
                lock.notifyAll();
                return value;
            }
        }
        private void waitQuietly(){
            try{lock.wait();} catch(InterruptedException e){Thread.currentThread().interrupt();}

        }
    }
    private void waitQuietly(){
        try{lock.wait();} catch(InterruptedException e){Thread.currentThread().interrupt();}

    }
}
public static void main(String[] args) throws InterruptedException{
    Handoff handoff = new Handoff();
    Thread taker = new Thread(() -> {
        for(int i = 0; i < 3;i++)System.out.println("took " + handoff.take());
    }, "taker");
    taker.start();
    for(int i = 1; i <= 3; i++) handoff.put("m" + i);
    taker.join();
    System.out.println("done");
    
}
public class T03ThreadState{
    public static void main(String[] args) throws InterruptedException{
        Thread sleeper = new Thread(() -> {
            try{Thread.sleep(200);} catch(InterruptedException e){Thread.currentThread().interrupt();}}, "sleeper");
            System.out.println("new " + sleeper.getState());
            sleeper.start();
            System.out.println("started " + sleeper.getState());
            Thread.sleep(50);
            System.out.println("sleeping " + sleeper.getState());
            sleeper.join();
            System.out.println("finished" + sleeper.getState());
            try{
                sleeper.start();
            }catch(IllegalThreadStateException e){
                System.out.println("restart " + e.getClass().getSimpleName());
            }
    }
}

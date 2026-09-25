public class T02RunnableVsThread{
    static class Counter extends Thread{
        private final int upto;
        Counter(int upto){
            super("counter");
            this.upto = upto;
        }
        @Override
        public void run(){
            int sum = 0;
            for(int i = 1; i <= upto; i++) sum += i;
            System.out.println("sum " + sum);
        }
    }
    static class Greeter implements Runnable{
        private final String who;
        Greeter(String who) {this.who = who;}
        @Override
        public void run(){
            System.out.println("hi " + who);
        }
    }
    public static void main(String[] args) throws InterruptedException{
        Thread a = new Counter(100);
        Thread b = new Thread(new Greeter("bob"),"greeter");
        Thread c = new Thread(() -> System.out.println("lambda"), "lambda");
        a.start();
        b.start();
        c.start();
        a.join();
        b.join();
        c.join();
        System.out.println("done");
    }
}
import java.util.ArrayDeque;
import java.util.Queue;
public class T15ProducerConsumer{
    static class Buffer{
        private final Queue<Integer> items = new ArrayDeque<>();
        private final int capacity;
        private boolean closed;
        Buffer(int capacity){
            this.capacity = capacity;
        }
        synchronized void put(int value) throws InterruptedException{
            while(items.size() == capacity) wait();
            items.add(value);
            notifyAll();
        }
        synchronized int take() throws InterruptedException{
            while(items.isEmpty() && !closed) wait();
            if(items.isEmpty()) return -1;
            int value = items.remove();
            notifyAll();
            return value;
        }
        synchronized void close(){
            closed = true;
            notifyAll();
        }
        synchronized int size(){return items.size();}
    }
    public static void main(String[] args) throws InterruptedException{
        Buffer buffer = new Buffer(5);
        int perProducer = 500;
        Thread[] producers = new Thread[2];
        for(int p = 0;p<2; p++){
            final int base = p * perProducer;
            producers[p] =  new Thread(() -> {
                try{
                    for(int i = 0; i < perProducer; i++) buffer.put(base + i);
                }catch (InterruptedException e){Thread.currentThread().interrupt();}
            });
        }
        int[] taken = new int[3];
        Thread[] consumers = new Thread[3];
        for(int c = 0; c < 3; c++){
            final int idx = c;
            consumers[c] = new Thread(() -> {
                try{
                    while(buffer.take() != -1) taken[idx]++;
                }catch(InterruptedException e){Thread.currentThread().interrupt();}
            });
        }
        for (Thread t : producers) t.start();
        for (Thread t : consumers) t.start();
        for (Thread t : producers) t.join();
        buffer.close();
        for (Thread t : consumers) t.join();
        System.out.println("consumed " + (taken[0] + taken[1] + taken[2]));
        System.out.println("left " + buffer.size());
    }
}
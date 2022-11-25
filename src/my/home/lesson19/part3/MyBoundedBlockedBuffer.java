package my.home.lesson19.part3;

import java.util.ArrayList;
import java.util.List;

public class MyBoundedBlockedBuffer {
    private List<Integer> list;
    private int limit;
    private static int counter = -1;

    public MyBoundedBlockedBuffer(int limit) {
        list = new ArrayList<>(limit);
        this.limit = limit;
    }

    public synchronized void put(int element) throws InterruptedException {
        while (counter > limit || counter == limit) {
            this.wait();
        }
        list.add(element);
        counter++;
        this.notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (this.list.isEmpty()) {
            this.wait();
        }
        int result = list.get(counter);
        list.remove(counter);
        counter--;
        this.notifyAll();
        return result;
    }
}

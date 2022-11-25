package my.home.lesson19.part2;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBoundedBlockedBuffer {

    private Lock lock;
    private Condition isEmpty;
    private Condition isFull;
    private List<Integer> list;
    private int limit;
    private static int counter = -1;

    public MyBoundedBlockedBuffer(int limit) {
        list = new ArrayList<>(limit);
        this.limit = limit;
        lock = new ReentrantLock();
        isEmpty = lock.newCondition();
        isFull = lock.newCondition();
    }

    public void put(int element) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (counter > limit || counter == limit) {
                isFull.await();
            }
            list.add(element);
            counter++;
            isEmpty.signal();
        } finally {

            lock.unlock();
        }

    }

    public int get() throws InterruptedException {

        lock.lockInterruptibly();
        int result = 0;
        try {
            while (this.list.isEmpty()) {

                isEmpty.await();
            }


            result = list.get(counter);

            list.remove(counter);

            counter--;
            isFull.signal();
        } finally {

            lock.unlock();

            return result;
        }

    }

}



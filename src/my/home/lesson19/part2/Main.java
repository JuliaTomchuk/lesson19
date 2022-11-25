package my.home.lesson19.part2;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyBoundedBlockedBuffer buffer = new MyBoundedBlockedBuffer(10);
        Thread prod = new Thread(new IntProducer(buffer, 1));
        Thread consumer = new Thread(new Consumer(buffer));
        prod.start();
        consumer.start();

    }
}
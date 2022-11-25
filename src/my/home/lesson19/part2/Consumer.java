package my.home.lesson19.part2;


public class Consumer implements Runnable {

    private final MyBoundedBlockedBuffer buffer;


    public Consumer(MyBoundedBlockedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            int i;
            try {
                i = buffer.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("consume " + i);
        }
    }
}



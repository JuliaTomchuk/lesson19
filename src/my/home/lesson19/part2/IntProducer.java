package my.home.lesson19.part2;


public class IntProducer implements Runnable {
    private MyBoundedBlockedBuffer buffer;
    private int i;

    public IntProducer(MyBoundedBlockedBuffer buffer, int i) {
        this.buffer = buffer;
        this.i = i;
    }

    @Override
    public void run() {

        while (true) {


            try {
                buffer.put((i++));
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Produse " + i);
        }
    }
}

package my.home.lesson19.part1;

public class MyThread  extends Thread{
    @Override
    public void run(){
        System.out.println(" Thread " + getName()+ " is running");
    }



}

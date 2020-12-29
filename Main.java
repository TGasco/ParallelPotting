//Class Main is where the code is executed
//Thomas Gascoyne
//201419827
//T.D.Gascoyne@student.liverpool.ac.uk
public class Main {
    public final Object lock = new Object();
    public static volatile int shelf = 0;
    public static volatile boolean empty = true;

    public static void main(String[] args) {
        Main m = new Main();
        m.begin();
    }
    //Method begin starts the Threads
    public void begin() {
        Potter1 t1 = new Potter1();
        Potter2 t2 = new Potter2();
        Packer t3 = new Packer();
        t1.start();
        t2.start();
        t3.start();
    }

    //Method shelfInc is a public method which allows the Potters and Packer to increment the number of pots on the shelf
    public synchronized void shelfInc() {
        shelf++;
        notifyAll();
    }
    //Method shelfDec is a public method which allows the Potters and Packer to decrement the number of pots on the shelf
    public synchronized void shelfDec() {
        shelf--;
        notifyAll();
    }
}

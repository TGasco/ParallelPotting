//Class Potter1
public class Potter1 extends Thread {
    //Initialises variables
    public static int potsRem1 = 10;
    
    //Method makePots waits for the shelf variable to be available, sleeps Thread for 500ms and decrements the shelf
    public void makePots(int shelf, boolean empty) {
        Potter1 p = new Potter1();
        Main m = new Main();
        //Sleeps Thread for 500ms to simulate time taken to pack
        m.empty = false;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        
        p.potsRem1--;
        //Synchronized block waits if the shelf is greater than 5 (max shelf)
        synchronized(this) {
            while (m.shelf >= 5) {
                try {
                    System.out.println("Potter1 waiting on packer...\n");
                    notifyAll();
                    wait(500);
                } catch (InterruptedException e) {}
            }
        }
        m.shelfInc();
    
        System.out.println("Potter 1 has made: " + (10-potsRem1) + " pots");
        System.out.println("There are " + m.shelf + " pots on the shelf\n");
        
        m.empty = true;
        //}
    }
    //Method run is the code which is executed when Main calls start
    public void run() {
        System.out.println("Potter 1 has started");
        Main m = new Main();
        Potter1 p = new Potter1();
        do {
            p.makePots(m.shelf, m.empty);
        } while (p.potsRem1 != 0);
    }
}

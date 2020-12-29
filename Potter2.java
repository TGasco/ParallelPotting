//Class Potter2
public class Potter2 extends Thread {
    //Initialises variables
    public static int potsRem2 = 10;
    
    //Method makePots waits for the shelf variable to be available, sleeps Thread for 500ms and decrements the shelf
    public void makePots(int shelf, boolean empty) {
        Potter2 p = new Potter2();
        Main m = new Main();

        //Sleeps Thread for 600ms to simulate time taken to pack
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {}
        m.empty = false;
    
        p.potsRem2--;
        //Synchronized block waits if the shelf is greater than 5 (max shelf)
        synchronized(this) {
            while (m.shelf >= 5) {
                try {
                    System.out.println("Potter2 waiting on packer...\n");
                    notifyAll();
                    wait(500);
                } catch (InterruptedException e) {}
            }
        }
        
        m.shelfInc();

        System.out.println("Potter 2 has made: " + (10-potsRem2) + " pots");
        System.out.println("There are " + m.shelf + " pots on the shelf\n");
    
        m.empty = true;
        //}
    }
    //Method run is the code which is executed when Main calls start
    public void run() {
        System.out.println("Potter 2 has started");
        Main m = new Main();
        Potter2 p = new Potter2();
        do {
            p.makePots(m.shelf, m.empty);
        } while (p.potsRem2 != 0);
    }
}

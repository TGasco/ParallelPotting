//Class Packer removes pots from the shelf and packs the pots
public class Packer extends Thread {
    //Initialising variables
    private int totPots;
    private int count = 0;
    
    //Method run is the code which is executed when Main calls start
    public void run() {
        System.out.println("Packer has started");
        Main m = new Main();
        do {
            setTotPots();
            pack(m.shelf, m.empty);
        } while (totPots > 0 || m.shelf != 0);
    }
    //Method pack waits for the shelf variable to be available, sleeps Thread for 400ms and decrements the shelf
    public void pack(int shelf, boolean empty) {
        Main m = new Main();
        //Synchronized block waits if the shelf is 0
        synchronized(this) {
            while (m.shelf == 0 && totPots > 0) {
                try {
                    System.out.println("Packer waiting for pots... \n");
                    notifyAll();
                    wait(500);
                } catch (InterruptedException e) {}
            }
        }
        m.empty = false;
        m.shelfDec();
        System.out.println("Packer has removed a pot from the shelf");
        //Sleeps Thread for 400ms to simulate time taken to pack
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {}
        count++;
        System.out.println("Packer has packed: " + count + " pots");
        System.out.println("There are " + m.shelf + " pots on the shelf\n");
        
        m.empty = true;
    }
    //Setter method for totPots
    private synchronized void setTotPots() {
        Potter1 p1 = new Potter1();
        Potter2 p2 = new Potter2();
        totPots = p1.potsRem1 + p2.potsRem2;
    }
}


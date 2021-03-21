/**
 * This class implements Runnable interface and override run method for threads.
 * @author : Raju Shrestha, Jake Gerard
 * @version : 1.1
 */


public class Applicants implements Runnable {

    private ApplicationQueue mySharedQueue;
    private final int NUMBER_OF_APPLICATION = 11;


    /**
     * Constructor for Applicants class
     * @param mySharedQueue will assign the default value
     */
    public Applicants(ApplicationQueue mySharedQueue) {
        this.mySharedQueue = mySharedQueue;
    }


    /**
     * Overriding run method as class implement Runnable interface.
     * The run method will use the addApplication method (in the ApplicationQueue class) to place the credit
     * applications in the shared Blocking Queue.  The thread should continuously produce applications for a
     * period of three minutes.
     */
    @Override
    public void run() {

        try {

            Application[] app = new Application[NUMBER_OF_APPLICATION];
            for (int i = 1; i < app.length; i++) {

                app[i] = new Application(getRandomNumber(300, 850), getRandomNumber(5000, 50000));
                mySharedQueue.addApplication(app[i]);
                System.out.println(Thread.currentThread().getName() + " : created application #" + app[i].getApplicationId());
                //Sleeping thread for 3000 millisecond
                Thread.sleep(3000); //3000

                if (flipCoin()) {
                    Thread.sleep(getRandomNumber(100, 1200));
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
           System.out.println(Thread.currentThread().getName() + "finished!");
    }

    /**
     * This method will generate random numbers for run mthod.
     * @param min is the minimum value
     * @param max is the maximum value
     * @return random number value between min and max
     */
    public synchronized int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);

    }

    /**
     *This method will "flip a coin" to decide whether to delay the thread before creating another application.
     *  If a delay is indicated, the thread will sleep.
     *
     * @return if its head or tail, if true is head and if false its tail
     */
    public synchronized boolean flipCoin() {

        if (Math.random() < 0.5) {
            // Its a head and delay is indicated
            return true;

        } else
            //its tail, no delay indicated
            return false;
    }
}

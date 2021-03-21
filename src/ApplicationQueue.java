/**
 * The ApplicationQueue class represents a shared queue used by the Applicants and the CreditCompany threads.
 * This class was provided by our Professor
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ApplicationQueue {

    public static final int MAX = 100;

    private static BlockingQueue<Application> apps = new ArrayBlockingQueue<>(MAX);

    /**
     *  This method adds an Application object to the apps queue using the thread-safe ArrayBlockingQueue put() method.
     *  The method put() will place an Application at the end of the Blocking Queue and will wait if the queue is full.
     * @param app is the application object
     */
    public void addApplication(Application app) {
        try {
            apps.put(app);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *  This method removes an Application object from the apps queue using the thread-safe ArrayBlockingQueue take()
     *  method.  The take() method will remove an element from the front of the Blocking Queue, waiting if the queue
     *  is empty.
     * @return applcation from queue for credit company class to process
     */
    public Application removeApplication() {
        try {
            Application app = apps.take();

            return app;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}

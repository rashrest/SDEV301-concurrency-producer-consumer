/**
 * This is a driver program that will create an instance of your ApplicationQueue class, which it will
 * passed to producer (Applicants) and consumer (CreditCompany) threads as a parameter to the constructor methods.
 *
 * @author Raju Shrestha, Jake Jake Gerard
 * @version 1.1
 */

public class Driver {


    /**
     * The main method which will create and run created threads.
     @param args is system arguments
     *
     */
    public static void main(String[] args) {

        ApplicationQueue queue = new ApplicationQueue();
        final int APPLICANT_THREADS = 3;
        final int CREDIT_THREADS = 2;

        for (int i = 1; i <= APPLICANT_THREADS; i++) {
            Thread addThreadApply = new Thread(new Applicants(queue));
            addThreadApply.setName("P" + i);
            System.out.println(addThreadApply.getName() + " started!");
            addThreadApply.start();

        }


        for (int i = 1; i <= CREDIT_THREADS; i++) {
            Thread removeThread = new Thread(new CreditCompany(queue));
            removeThread.setName("C" + i);
            removeThread.start();

        }

    }
}

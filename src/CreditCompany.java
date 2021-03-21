/**
 * This class represent credit company which implements runnable interface. The class contains override run method.
 * @author Raju Shrestha, Jake Jake Gerard
 * @version 1.1
 */

public class CreditCompany implements Runnable {

    private ApplicationQueue mySharedQueue;

    /**
     * Class constructor which assigns default values.
     * @param mySharedQueue is the application obj from Application queue
     */
    public CreditCompany(ApplicationQueue mySharedQueue) {
        this.mySharedQueue = mySharedQueue;
    }

    /**
     * Overriding run method
     * The method of this class remove(take) applications from the shared Application ArrayBlockingQueue
     * and process them.  When processing an application, the company looks at the credit score and first determines
     * whether it will approve the applicant's request for credit.  Only scores of 580 or greater are approved.
     */
    @Override
    public void run() {

        //remove application from application queue until is not empty
        while (mySharedQueue.removeApplication().getApplicationId() != 0) {

            Application app = mySharedQueue.removeApplication();
            app.setApprovedLimit(approvedLimit(app.getCreditScore()));

            if (app.getApprovedLimit() != 0) {
                app.setApproved(true);
                System.out.println(Thread.currentThread().getName() + ": Application " + app.getApplicationId() + " with credit" +
                        " score " + app.getCreditScore() + " is " + "approved" + " for " + app.getApprovedLimit() + " (requested : " +
                        app.getRequestLimit() + ")");

            } else if (app.getApprovedLimit() == 0) {
                app.setApproved(false);
                System.out.println(Thread.currentThread().getName() + ": Application " + app.getApplicationId() + " with credit" +
                        " score " + app.getCreditScore() + " is " + "not approved.");

            }
        }
    }


    /**
     * This method will determine what credit score is and if it will approved or not. If approved returns credit limit.
     * @param creditScore is the credt score of applicant.
     * @return If approved returns credit limit. If its declined it will return 0.
     */
    public synchronized int approvedLimit(int creditScore) {

        if (creditScore >= 580 && creditScore <= 669) {
            return 5000;
        } else if (creditScore >= 670 && creditScore <= 739) {
            return 10000;
        } else if (creditScore >= 740 && creditScore <= 799) {
            return 25000;
        } else if (creditScore >= 800) {
            return 50000;
        } else return 0;

    }


}

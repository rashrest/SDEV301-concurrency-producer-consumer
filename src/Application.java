/**
 * This class contains a static field that holds the next id to be assigned to a new application instance.
 * An application id have at minimum 5 digits (so the lowest possible value of the application id is 10000).
 * @author Raju Shrestha, Jake Gerard
 * @version 1.1
 */

import java.util.concurrent.locks.ReentrantLock;

public class Application {

    private static int nextId = 10000;//should have minimum of 5 digits which means minimum value is 10000
    private static ReentrantLock counterLock = new ReentrantLock();
    private int applicationId; //increment value by 1
    private int creditScore;
    private int requestLimit;
    private boolean approved;
    private int approvedLimit;

    /**
     * The class constructor assigns the applicationId when the application is created. The remaining instance fields
     * are left at their default values.
     * @param creditScore is the credit score for person
     * @param requestLimit is the requested credit limit
     */
    public Application(int creditScore, int requestLimit) {
        this.creditScore = creditScore;
        this.requestLimit = requestLimit;
        applicationId=getNextId();
    }

    /**
     * The application id for credit request. This method will increment application id when application is created
     * @return next application id
     */
    public static int getNextId(){

        counterLock.lock();

        //enclosing locks in a try-finally block
        try{
           return nextId++;
        }finally{
            counterLock.unlock();
        }
    }

    /**
     * Getter for application id
     * @return application id
     */
    public int getApplicationId() {
        return applicationId;
    }

    /**
     * Getter for credit score
     * @return credit score
     */
    public int getCreditScore() {
        return creditScore;
    }

    /**
     * Getter for request limit
     * @return request limit
     */
    public int getRequestLimit() {
        return requestLimit;
    }


    /**
     * Getter for approved limit
     * @return approved limit
     */
    public int getApprovedLimit() {
        return approvedLimit;
    }


    /**
     * Sets if application is approved or not
     * @param approved as true if approved and false if declined
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Sets approved limit when application is approved
     * @param approvedLimit int value as approved limit
     */
    public void setApprovedLimit(int approvedLimit) {
        this.approvedLimit = approvedLimit;
    }
}

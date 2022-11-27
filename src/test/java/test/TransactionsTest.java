package test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import logic.LogicManagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * class for testing
 * @author Oscar.Araque
 */
public class TransactionsTest {
    /**
     * Initiate all the testing methods.
     */
    LogicManagement test = new LogicManagement();

    /**
     * The log.
     */
    public Logger log = Logger.getLogger(TransactionsTest.class);

    /**
     * Execute the first test method.
     * @author Oscar.Araque
     */
    @Test
    public void checkEmptyEndpoint(){
        log.info("Verifying the Endpoint is empty...");
        test.checkEmptyEndpoint();
    }

    /**
     * Execute the second test method.
     * @author Oscar.Araque
     */
    @Test
    public void initializePOJO(){
        log.info("Initializing the POJO with 10 random data...");
        test.generatePOJORandomData();
    }

    /**
     * Execute the third test method.
     * @author Oscar.Araque
     */
    @Test
    public void getNotDuplicated(){
        log.info("Make the get request, checking for not duplicate email accounts...");
        test.getRequestNotDuplicated();
    }

    /**
     * Execute the fourth test method.
     * @author Oscar.Araque
     */
    @Test
    public void updateAccountNumber(){
        log.info("Updating an existing account number...");
        test.updateAccountNumber(8);
    }
}

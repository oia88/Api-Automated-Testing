package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pojo.TransactionsPOJO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



/**
 * class for all the requests and testing methods.
 * @author Oscar.Araque
 */
public class LogicManagement {

    /**
     * The log.
     */
    public Logger log = Logger.getLogger(LogicManagement.class);

    /**
     *Get request and turns into a list.
     *
     * @return a list with the mockApi data
     * @author Oscar.Araque
     */
    public List<TransactionsPOJO> getTransactions(){
        RestAssured.baseURI = "https://637a987b702b9830b9f0b746.mockapi.io/api/bankTransactions";
        Response response = given().when().get(baseURI);
        response.then().statusCode(200);
        String jsonString = response.asPrettyString();
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<TransactionsPOJO> transactionList = objectMapper.
                    readValue(jsonString, new TypeReference<List<TransactionsPOJO>>() {});
            return transactionList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Post request
     *
     * @param dataTransaction : POJO's elements
     * @author Oscar.Araque
     */
    public void postTransactions(TransactionsPOJO dataTransaction){
        Response response = given().contentType("application/json")
                .body(dataTransaction)
                .when()
                .post("https://637a987b702b9830b9f0b746.mockapi.io/api/bankTransactions");
        response.then().extract().response();
        response.prettyPrint();
        response.then().statusCode(201);
    }

    /**
     * Put request
     *
     * @param account : account number to update
     * @param id : id of the transaction
     * @author Oscar.Araque
     */
    public void putTransaction(String account, int id){
        Map<String, String> putAccountNumber = new HashMap<String, String>();
        putAccountNumber.put("accountNumber", account);
        Response response = given().contentType("application/json")
                .body(putAccountNumber)
                .when()
                .put("https://637a987b702b9830b9f0b746.mockapi.io/api/bankTransactions/" + id);
        response.then().extract().response();
        response.prettyPrint();
        response.then().statusCode(200);

    }

    /**
     * Delete request
     *
     * @param id : id of the transaction
     * @author Oscar.Araque
     */
    public void deleteTransaction(int id){
            try {
                Response response = given().contentType("application/json")
                        .when()
                        .delete("https://637a987b702b9830b9f0b746.mockapi.io/api/bankTransactions/" + id);
                response.then().extract().response();
                response.prettyPrint();
                response.then().statusCode(200);
            } catch (Error error){
                log.info(error);
                deleteTransaction(id);
            }
    }


    /**
     * Verify the Endpoint is empty and f it has any data use the DELETE request to clean and
     * leave it empty.
     *
     * @author Oscar.Araque
     */
    public void checkEmptyEndpoint(){

        List<TransactionsPOJO> transactionList = getTransactions();

            if(transactionList.size() != 0){
                transactionList.forEach(transaction -> {
                    deleteTransaction(transaction.getId());
                    log.info(transaction.getAccountNumber() + " has been deleted");
                });
            }
            Assert.assertTrue(getTransactions().size() == 0 , "The endpoint is not currently empty");
    }


    /**
     * Initialize the POJO with 10 random data, Also, make
     * a code verification for avoiding duplicate email accounts. Then, perform the POST request.
     *
     * @author Oscar.Araque
     */
    public void generatePOJORandomData(){

        List<TransactionsPOJO> pojoList = new ArrayList<>();
        Faker fakeData = new Faker();
        int i = 0;
        while (i < 10){
            TransactionsPOJO transactions = new TransactionsPOJO();

            transactions.setName(fakeData.name().firstName());
            transactions.setLastName(fakeData.name().lastName());
            transactions.setAccountNumber(String.valueOf(fakeData.number().numberBetween(1000000, 9999999)));
            transactions.setAmount(fakeData.number().randomDouble(2,0,1000));
            transactions.setTransactionType(fakeData.options()
                    .option("withdrawal","payment","invoice","deposit"));
            transactions.setEmail(fakeData.internet().emailAddress());
            transactions.setActive(fakeData.bool().bool());
            transactions.setCountry(fakeData.country().name());
            transactions.setTelephone(fakeData.phoneNumber().phoneNumber());
            pojoList.add(transactions);
            i++;
        }

            if(!verifyDuplicateBeforePost(pojoList)){
                for(int k = 0; k < pojoList.size(); k++){
                    postTransactions(pojoList.get(k));
                }
            }

    }


    /**
     * Verify if the emails are duplicate before make post request
     *
     * @param pojoList : list of transactions
     * @return a boolean to confirm
     * @author Oscar.Araque
     */
    public boolean verifyDuplicateBeforePost(List<TransactionsPOJO> pojoList){

        boolean isDuplicated = false;
        List<TransactionsPOJO> getList = getTransactions();
        if(getList.size() != 0){
            for(int j = 0; j < getList.size(); j++){
                if(pojoList.contains(getList.get(j).getEmail())){
                   isDuplicated = true;
                    Assert.assertFalse(!pojoList.contains(getList.get(j).getEmail())
                            ,"There are emails repeated");
                } else {
                    log.info("There are not emails repeated.. Implementing a post request");
                }
            }
        }
        return isDuplicated;
    }


    /**
     * Make the GET request, asserting that there are not duplicate email accounts.
     *
     * @author Oscar.Araque
     */
    public void getRequestNotDuplicated() {

        List<TransactionsPOJO> getList = getTransactions();
        List<String> getListFilter = new ArrayList<>();

        for(int i = 0; i < getList.size(); i++){
            if(!getListFilter.contains(getList.get(i).getEmail())){
                getListFilter.add(getList.get(i).getEmail());
            }
        }

        if(getList.size() != getListFilter.size()){
            log.info("There are emails repeated");
        } else {
            log.info("There are not emails repeated");
        }

        Assert.assertFalse(getList.size() != getListFilter.size()
                , "There are emails repeated");
    }


    /**
     * Add a test to update an existing AccountNumber.
     *
     * @param id : id of the transaction
     * @author Oscar.Araque
     */
    public void updateAccountNumber(int id){

        List<TransactionsPOJO> updateData = getTransactions();
        TransactionsPOJO accountNumber = new TransactionsPOJO();
        Faker fakeData = new Faker();
        accountNumber.setAccountNumber(String.valueOf(fakeData.number().numberBetween(1000000, 9999999)));

        for (int i = 0; i < updateData.size(); i++){
            if(updateData.get(i).getAccountNumber() != accountNumber.getAccountNumber()){
                putTransaction(accountNumber.getAccountNumber(), id);
                Assert.assertTrue
                        (updateData.get(i).getAccountNumber() != accountNumber.getAccountNumber()
                                , "Account number updated successfully");
            }
        }
        log.info("Account number updated successfully");
    }
}


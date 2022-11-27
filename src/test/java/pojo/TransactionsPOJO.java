package pojo;

/**
 * class to deserialize the mockApi data - POJO -.
 * @author Oscar.Araque
 */
public class TransactionsPOJO {

    private int id;
    private String name;
    private String lastName;
    private String accountNumber;
    private double amount;
    private String transactionType;
    private String email;
    private Boolean active;
    private String country;
    private String telephone;


    /**
     * constructor method
     * @author Oscar.Araque
     */
    public TransactionsPOJO(){}

    /**
     * constructor method.
     *
     * @param id : id
     * @param name : name
     * @param lastName : last name
     * @param accountNumber : account number
     * @param amount : amount
     * @param transactionType : transaction type
     * @param email : email
     * @param active : active
     * @param country : country
     * @param telephone: phone
     * @author Oscar.Araque
     */
    public TransactionsPOJO(int id, String name, String lastName, String accountNumber, double amount, String transactionType
            , String email, Boolean active, String country, String telephone)
    {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
    }


    /**
     * get the id.
     * @return transaction id
     */
    public int getId() {
        return id;
    }

    /**
     * get the account number.
     * @return transaction account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * get the email
     * @return transaction email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set name to initialize the POJO
     * @param name : create name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set lastname to initialize the POJO
     * @param lastName : create lastname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * set account number to initialize the POJO
     * @param accountNumber : create account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * set amount to initialize the POJO
     * @param amount : create amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * set transaction type to initialize the POJO
     * @param transactionType : create transaction type
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * set email to initialize the POJO
     * @param email : create email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set active to initialize the POJO
     * @param active : create active status
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * set counry to initialize the POJO
     * @param country : create current transaction country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * set phone to initialize the POJO
     * @param telephone : create phone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

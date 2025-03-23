//Account Manager
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Callable;


public class AccountManager implements ManagerInterface<CustomerAccount>{
    HashMap<String, CustomerAccount> accountsMap;
    private final int CREATION_LIMIT = 100;

    ////constructors
    // no argument
    public AccountManager() {
        accountsMap = new HashMap<>();
    }
    //interface methods
    @Override
    public String generateID() {
        UUID uniqueID = UUID.randomUUID();
        return "account".concat(uniqueID.toString());
    }

    @Override
    public CustomerAccount getMember(String customerID) {
        return runWithChecking( (() -> {
            return accountsMap.get(customerID);
        }), customerID);
    }

    public void generateAccount(String name, String phone) {
        if(accountsMap.size() >= CREATION_LIMIT){
            System.out.println("Account creation limit exceeded");
            return;
        }
        String customerID = generateID();
        //while loop to keep generate a new ID until there's no duplicate
        while(accountsMap.containsKey(customerID)){
            customerID = generateID();
        }

        CustomerAccount account = new CustomerAccount(customerID, name, phone);
        System.out.println("Greetings, " + name + "! Your ID is " + customerID);
        accountsMap.put(account.getCustomerID(), account);
    }

    //this method let the customer fill out the full information of their profile
    // phone and name are already exist

    public void completeProfile(String customerID, String email, String address, String interest,
                                String birthday, String paymentMethod, boolean textMsgAd ){
        if(!accountsMap.containsKey(customerID)){ System.out.println("CustomerID not found in Manager"); return; }
        CustomerAccount account = accountsMap.get(customerID);
        account.setEmail(email);
        account.setAddress(address);

        try {
            account.setDateOfBirth(LocalDate.parse(birthday));
        }catch (DateTimeParseException e){
            System.out.println("Error parsing date: " + e.getMessage());
        }

        account.setInterestedCategory(interest);
        account.setPaymentMethod(paymentMethod);
        account.setAcceptTextMessage(textMsgAd);

    }

    public void setPassWord(String customerID, String password){
        CustomerAccount account = accountsMap.get(customerID);
        account.setPassword(hashPwd(password));
    }

    public boolean logIn (String phone, String password){
        CustomerAccount account = getAccountByPhone(phone);
        if (account != null){
            return (account.getPassword()).contentEquals(hashPwd(password));
        }

        System.out.println("CustomerID not found in Manager");
        return false;
    }

    //helper method to handle possible exception
    private CustomerAccount runWithChecking(Callable<CustomerAccount> callable, String customerID) throws InvalidArgumentException{
        try{
            if(!accountsMap.containsKey(customerID) ){
                throw new InvalidArgumentException(
                    "CustomerID not found in Manager", new Throwable());
            }
            return callable.call();
        }catch(Exception e){
            System.out.println("Error performing the task: " + e.getMessage());
            return null;
        }

    }

    public CustomerAccount getAccountByPhone(String phone){
        for(CustomerAccount account: accountsMap.values()){
            if(account.getPhone().equals(phone)){
                return account;
            }
        }
        return null;
    }

    private String hashPwd (String password){
        MessageDigest md = null;
        BigInteger number = BigInteger.ZERO;

        try {
            md = MessageDigest.getInstance("SHA-256");
            number = new BigInteger(1, md.digest(password.getBytes(StandardCharsets.UTF_8)));
        }catch(NoSuchAlgorithmException e){
            System.out.println("Error hashing pwd");
            System.out.println(e.getMessage());
        }catch(NullPointerException e ){
            System.out.println("Error dereferencing pwd object");
        }

        // Convert message digest into hex
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64){
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

}

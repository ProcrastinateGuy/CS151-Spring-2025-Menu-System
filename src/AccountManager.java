//Account Manager
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class AccountManager implements ManagerInterface<CustomerAccount>{

    //member list
    private String customerID = "default ID";
    private String customerName = "default name";
    private String email = "default email";
    private String phone = "default phone";
    private String address = "default address";
    private String paymentMethod = "default payment";
    private String InterestedCategory = "default interest";
    private LocalDate dateOfBirth = LocalDate.of(1500, 1, 1);


    private String versionNumber = "default versionNumber";


    //status of an account
    private boolean suspended = false;
    private boolean premiumUser = false;
    private boolean acceptTextMessage = false;
    private boolean hasProblemWithLastOrder = false;

    //a list of item ID for storing the customers favorites
    private List<Integer> favorites;



    private int rewardPoints = 0;
    private double totalSavings = 0.0;


    // member class
    private DealManager dealManager;
    private OrderManager orderManager;
    private ReviewManager reviewManager;

    //interface methods
    @Override
    public String generateID() {
        UUID uniqueID = UUID.randomUUID();
        return "account".concat(uniqueID.toString());
    }

    @Override
    public CustomerAccount getMember(String memberID) {
        return null;
    }

    //setter

    // return value: true: set successful
    // false: set operation failed
    public boolean setCustomerName(String customerName) {
        if(customerName.matches(".*[^A-z0-9 ].*")) { // this regex allows white spaces
            System.out.println("names should only contain alphabet, spaces, or numbers");
            return false;
        }

        this.customerName = customerName;
        return true;
    }

    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }

    public boolean setPhone(String phone) {
        if(phone.matches("[^0-9]")) {
            System.out.println("phones should only contain numbers");
            return false;
        }
        if(phone.length() != 10) {
            System.out.println("phones should be exactly 10 digits," +
                "don't use (), -, or white spaces");
            return false;
        }
        this.phone = phone;
        return true;
    }

}

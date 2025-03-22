//Account Manager
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;



public class AccountManager implements ManagerInterface<CustomerAccount>{
    HashMap<UUID, CustomerAccount> accounts;


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



}

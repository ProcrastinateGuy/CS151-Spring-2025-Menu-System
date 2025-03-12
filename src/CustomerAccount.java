import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CustomerAccount {

    //member list
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


    private int customerID = 0;

    private int rewardPoints = 0;

    private double totalSavings = 0.0;


    // member class
    private DealManager dealManager;
    private OrderManager orderManager;
    private ReviewManager reviewManager;


    //no argument Constructor
    public CustomerAccount() {
        customerID = -1;  // -1 to distinguish invalid user
        totalSavings = -1;
        rewardPoints = -1;

        favorites = new ArrayList<>();
    }

    //initialize with only name and phone number
    public CustomerAccount(String customerName, String phone){

    }



    //setter

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setInterestedCategory(String interestedCategory) {
        InterestedCategory = interestedCategory;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public void setPremiumUser(boolean premiumUser) {
        this.premiumUser = premiumUser;
    }

    public void setAcceptTextMessage(boolean acceptTextMessage) {
        this.acceptTextMessage = acceptTextMessage;
    }

    public void setHasProblemWithLastOrder(boolean hasProblemWithLastOrder) {
        this.hasProblemWithLastOrder = hasProblemWithLastOrder;
    }

    public void setFavorites(List<Integer> favorites) {
        this.favorites = favorites;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void setTotalSavings(int totalSavings) {
        this.totalSavings = totalSavings;
    }


    public void setDealManager(DealManager dealManager) {
        this.dealManager = dealManager;
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public void setReviewManager(ReviewManager reviewManager) {
        this.reviewManager = reviewManager;
    }

    //getter

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInterestedCategory() {
        return InterestedCategory;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isPremiumUser() {
        return premiumUser;
    }

    public boolean isAcceptTextMessage() {
        return acceptTextMessage;
    }

    public boolean isHasProblemWithLastOrder() {
        return hasProblemWithLastOrder;
    }

    public List<Integer> getFavorites() {
        return favorites;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public double getTotalSavings() {
        return totalSavings;
    }



    public DealManager getDealManager() {
        return dealManager;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public ReviewManager getReviewManager() {
        return reviewManager;
    }
}

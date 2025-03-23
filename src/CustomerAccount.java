//Customer account

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccount {

    //member list
    private String customerID = "default ID";
    private String customerName = "default name";
    private String email = "default email";
    private String phone = "default phone";
    private String address = "default address";
    private String paymentMethod = "default payment";
    private String InterestedCategory = "default interest";
    private LocalDate dateOfBirth = LocalDate.of(1500, 1, 1);
    private String password = "default password";

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
    private DealManager dealManager = new DealManager();
    private OrderManager orderManager = new OrderManager();
    private ReviewManager reviewManager = new ReviewManager();

    //no argument Constructor
    //important values are set to -1 to distinguish invalid user
    public CustomerAccount() {
        customerID = "-1";
        totalSavings = -1;
        rewardPoints = -1;

        favorites = new ArrayList<>();
    }

    //name and phone
    //initialize with only name and phone number
    public CustomerAccount(String customerID, String customerName, String phone) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        setPhone(phone);

        // all other values remain as default
    }

    public void printAccountInfo() {
        System.out.println("CustomerID: " + customerID);
        System.out.println("CustomerName: " + customerName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("PaymentMethod: " + paymentMethod);
        System.out.println("InterestedCategory: " + InterestedCategory);
        System.out.println("DateOfBirth: " + dateOfBirth);
        System.out.println("Password: " + password);
        System.out.println("Suspended: " + suspended);
        System.out.println("PremiumUser: " + premiumUser);
        System.out.println("AcceptTextMessage: " + acceptTextMessage);
        System.out.println("HasProblemWithLastOrder: " + hasProblemWithLastOrder);
        System.out.println("favorites: " + favorites);
        System.out.println("rewardPoints: " + rewardPoints);
        System.out.println("totalSavings: " + totalSavings);

    }

    //setter
    // return value: true: set successful
    // false: set operation failed
    public void setCustomerName(String customerName) {
        if (customerName.matches(".*[^A-z0-9 ].*")) { // this regex allows white spaces
            System.out.println("names should only contain alphabet, spaces, or numbers");
            return;
        }

        this.customerName = customerName;
    }

    public void setEmail(String email) {
        //we only check for the characters that is not allowed in an email address
        if (email.matches(".*[^A-z0-9.@].*") || email.matches("^((?!@).)*$")) {
            System.out.println("email addresses should only contain alphabet, ., ,numbers"
                    + "and at least one @");
            return;
        }
        this.email = email;

    }

    public void setPhone(String phone) {
        if (phone.matches("[^0-9]")) {
            System.out.println("phones should only contain numbers");
            return;
        }
        if (phone.length() != 10) {
            System.out.println("phones should be exactly 10 digits,"
                    + "don't use (), -, or white spaces");
            return;
        }
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

    public void setCustomerID(String customerID) {
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

    public void setPassword(String password) {
        this.password = password;

    }

    //getter
    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerID() {
        return customerID;
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

    public int getRewardPoints() {
        return rewardPoints;
    }

    public double getTotalSavings() {
        return totalSavings;
    }

    public String getPassword() {
        return password;
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

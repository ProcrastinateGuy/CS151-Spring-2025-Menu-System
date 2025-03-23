
import java.util.HashMap;

public class Order {

    private ItemShelf itemShelf;
    private HashMap <String, Item> itemMap;
    private String customerID;
    private String orderID;
    private double subtotal;

    ////constructors
    //no argument
    public Order(){
        setCustomerID("default ID");
        setOrderID("default orderID");
        setSubtotal(0);
    }

    //IOException handles here
    private void loadAllItem() {
        try{
            //I hate windoze
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                itemShelf = new ItemShelf(".\\itemDB.txt");
            } else {
                itemShelf = new ItemShelf("./itemDB.txt");
            }
        }
        catch(InvalidArgumentException e){
            System.out.println("Error loading the items DB");
            System.err.println(e.getMessage());
        }
    }

    //constructor
    public Order(String customerID, String orderID) {
        loadAllItem();
        setCustomerID(customerID);
        setOrderID(orderID);
        setSubtotal(0);
        itemMap = new HashMap<>();
    }

    public boolean containsItem(String itemID) {
        return itemMap.containsKey(itemID);
    }
    public void addItem(String itemID, int quantity) {

        //Early return, item DNE
        if(!itemShelf.contains(itemID)){
            System.out.println("Item does not exist");
            return;
        }
        //early return, item already in the map
        //for duplicated item, increment the quantity instead
        if( itemMap.containsKey(itemID) ){
            itemMap.get(itemID).incrementQuantity(quantity);
            return;
        }

        itemMap.put(itemID, itemShelf.getItem(itemID));
    }

    public void printOrderItems() {
        if (itemMap.isEmpty()) {
            System.out.println("No items in the order.");
        } else {
            for (Item item : itemMap.values()) {
                System.out.println(item);
            }
        }
    }

    //setters
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    //setCustomerID is set to be private because it's only needed in instantiation
    private void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    //set subtotal is set to be private because it's only needed in instantiation
    private void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    //getters
    public double getSubtotal() {
        for (Item item : itemMap.values()) {

            subtotal += item.getDiscountedPriceAfterTax();
        }

        //deep copy
        return Double.valueOf(subtotal);
    }

    public int getSpecificItemCount(String itemID){
        return itemMap.get(itemID).getQuantity();
    }

    //this method the total number of item in this order
    public int getTotalItemCount(){
        int itemCount= 0;
        for (Item item : itemMap.values()) {
            itemCount += item.getQuantity();
        }
        return Integer.valueOf(itemCount);
    }

    //this method returns how many kinds of item we have, not the total count
    public int getUniqueItemCount(){
        return itemMap.size();
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public String toString() {
        return "Order Details: \n"
                + "Order Number: " + orderID + "\n"
                + "Customer ID: " + customerID + "\n"
                + "Subtotal: $" + String.format("%.2f", subtotal) + "\n";
    }
}

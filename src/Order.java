
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> itemList;
    private int customerID;
    private int orderNumber;
    private double subtotal;

    public Order(int customerID, int orderNumber) {
        this.customerID = customerID;
        this.orderNumber = orderNumber;
        this.itemList = new ArrayList<>();
        this.subtotal = 0;
    }

    public void addItem(Item item) {
        itemList.add(item);
        subtotal += item.applyDeal() * item.getQuantity();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void viewItemList() {
        if (itemList.isEmpty()) {
            System.out.println("No items in the order.");
        } else {
            for (Item item : itemList) {
                System.out.println(item);
            }
        }
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String toString() {
        return "Order Details: \n"
                + "Order Number: " + orderNumber + "\n"
                + "Customer ID: " + customerID + "\n"
                + "Subtotal: $" + String.format("%.2f", subtotal) + "\n";
    }
}

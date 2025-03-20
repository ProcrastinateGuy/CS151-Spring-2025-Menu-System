

public class Item {
    private int itemID;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private int discount;

    public Item(int itemID, String name, double price, int quantity, String category, int discount) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.discount = discount;
    }
    public int getItemID() {
        return itemID;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public double applyDiscount() {
        return price - (price * discount / 100.0);
    }

    @Override
    public String toString(){
        return  "Item Info: \n" +
                "Item ID: " + itemID + "\n" +
                ", Name: " + name + "\n" +
                ", Price: $" + price + "\n" +
                ", Quantity: " + quantity + "\n" +
                ", Category: " + category + "\n" +
                ", Discount: " + discount + "%" + "\n" +
                ", Final Price: $ " + applyDiscount() + "\n";
    }
}
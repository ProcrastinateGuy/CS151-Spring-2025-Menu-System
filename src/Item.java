public class Item {
    private String itemID;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private int discount;
    private boolean taxable;

    public Item() {
        setItemID("-1");
        setName("default name");
        setPrice(0.0);
        setQuantity(0);
        setCategory("default category");
        setDiscount(0);

    }

    public Item(String itemID, String name, double price, int quantity, String category, int discount) {
        setItemID(itemID);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
        setDiscount(discount);

    }

    public void incrementQuantity(int quantityToAdd) {
        setQuantity(getQuantity() + quantityToAdd);
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0 ){
            System.out.println("Invalid Quantity");
        }
        else{
            this.quantity = quantity;
        }
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            System.out.println("Invalid discount");
        }else{
            this.discount = discount;
        }
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }



    public String getItemID() {
        return itemID;
    }
    public String getName() {
        return name;
    }
    public double getFullPrice() {
        return price;
    }

    public double getDiscountedPrice() {
        //deep copy
        return Double.valueOf( (price *= (1 - (discount/100.0)))) ;
    }
    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getDiscount() {
        return Integer.valueOf(discount);
    }

    public boolean isTaxable() {
        return Boolean.valueOf(taxable);
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
                ", Final Price: $ " + getDiscountedPrice() + "\n";
    }
}
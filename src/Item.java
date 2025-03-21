import java.math.*;

public class Item {
    private String itemID;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private int discount;
    private boolean taxable;
    private double taxRate = 1.09375;

    public Item() {
        setItemID("-1");
        setName("default name");
        setPrice(0.0);
        setQuantity(0);
        setCategory("default category");
        setDiscount(0);
        setTaxable(false);

    }

    public Item(String itemID, String name, double price, int quantity,
                String category, int discount, boolean taxable) {
        setItemID(itemID);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
        setDiscount(discount);
        setTaxable(taxable);
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
        if(price < 0) {
            System.out.println("Price cannot be negative");
            return;
        }

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

    public void setTaxRate(double taxRate) {
        if(taxRate < 0 || taxRate > 1) {
            System.out.println("Invalid tax Rate");
        }else{
            this.taxRate = taxRate;
        }
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
        return Double.valueOf( (price * (1 - (discount/100.0))) ) ;
    }

    public double getFullPriceAfterTax() {
        double fullPriceAfterTax = 0.0;
        if (taxable) {
            fullPriceAfterTax = price * taxRate;
            return fullPriceAfterTax;
        }
        return getFullPrice();
    }

    public double getDiscountedPriceAfterTax() {
        double DiscountedPriceAfterTax = 0.0;
        if (taxable) {
            DiscountedPriceAfterTax = getDiscountedPrice() * taxRate;
            return DiscountedPriceAfterTax;
        }
        return getDiscountedPrice();
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

    public double getTaxRate() {
        return Double.valueOf( (taxRate) );
    }

    @Override
    public String toString(){
        return  "Item Info: \n" +
                "Item ID: " + itemID +
                ", Name: " + name +
                ", Price: $" + price +
                ", Quantity: " + quantity +
                ", Category: " + category +
                ", Discount: " + discount + "%" +
                ", Discounted Price: $ " + new BigDecimal(getDiscountedPrice()).setScale(2, RoundingMode.FLOOR)+
                ", Discounted Price After Tax: $ " + new BigDecimal(getDiscountedPriceAfterTax()).setScale(2, RoundingMode.FLOOR);
    }
}
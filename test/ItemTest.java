import org.junit.Test;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertAll;
//    @Test
//    public void itemListCreationTest() throws IOException {
//        ItemShelf itemShelf = new ItemShelf(".\\src\\itemDB.txt");
//        Item burger = new Item("1","Burger",5.99,2,"FastFood",10);
////        assertAll(() ->{
//                assertEquals("Deli_Burger", itemShelf.getItemName("Deli_Burger").getName());

  //          }
  //      );

        /*
Item ID: 1, Name: Deli_Burger,   Price: 5.99$,  Quantity: 2,   Category: Fast Food,    Discount: 10%, Taxable: Y
Item ID: 2, Name: Pizza,         Price: 8.99$,  Quantity: 1,   Category: Fast Food,    Discount: 15%, Taxable: N
Item ID: 3, Name: Pasta,         Price: 7.49$,  Quantity: 5,   Category: Italian,      Discount: 5%,  Taxable: N
Item ID: 4, Name: Salad,         Price: 4.99$,  Quantity: 3,   Category: Healthy,      Discount: 0%,  Taxable: N
Item ID: 5, Name: Coffee,        Price: 2.99$,  Quantity: 10,  Category: Beverage,     Discount: 10%, Taxable: N
Item ID: 6, Name: Ice Cream,     Price: 3.99$,  Quantity: 4,   Category: Dessert,      Discount: 20%, Taxable: N
Item ID: 7, Name: Deli_Sandwich, Price: 6.49$,  Quantity: 2,   Category: Fast Food,    Discount: 8%,  Taxable: Y
Item ID: 8, Name: Juice,         Price: 3.49$,  Quantity: 6,   Category: Beverage,     Discount: 12%, Taxable: N
Item ID: 9, Name: Deli_Sushi,    Price: 12.99$, Quantity: 1,   Category: Japanese,     Discount: 0%,  Taxable: Y
Item ID: 10,Name: Steak,         Price: 15.99$, Quantity: 2,   Category: Fine Dining,  Discount: 5%,  Taxable: N
Item ID: 11,Name: Coke,          Price: 3.49$,  Quantity: 100, Category: Co2_Beverage, Discount: 30%, Taxable: Y
*/

public class ItemTest {
    @Test
    public void testDiscount() {
        Item item = new Item("1", "Deli_Burger", 5.99, 1, "FastFood", 10, true);
        assertEquals(5.391, item.getDiscountedPrice(), 0.001);
    }
    @Test
    public void testDiscountAfterTax() {
        Item item = new Item("1", "Burger", 5.99, 1, "FastFood", 10, true);
        double expectedPrice = 5.99 * 0.9 * 1.09375;
        assertEquals(expectedPrice, item.getDiscountedPriceAfterTax(), 0.001);
    }
    @Test
    public void testDiscountPriceAfterTax_NoTax() {
        Item item = new Item("2", "Pizza", 8.99, 1, "FastFood", 15, false);
        double expectedPrice = 8.99 * 0.85;
        assertEquals(expectedPrice, item.getDiscountedPriceAfterTax(), 0.001);
    }
    @Test
    public void testIncreaseQuantity() {
        Item item = new Item("3", "Pasta", 7.49, 5, "Italian", 5, false);
        item.incrementQuantity(2);
        assertEquals(7, item.getQuantity());
    }
}
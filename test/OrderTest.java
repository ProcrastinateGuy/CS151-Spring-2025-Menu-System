import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;

public class OrderTest {

    @Test
    public void testAddItem () {
        Order order = new Order("testCustomer", "testOrder");
        order.addItem("1",2); // 1 is Deli_Burger

        assertEquals(1, order.getUniqeItemCount()); // Just 1 kind of item
        assertEquals(2, order.getSpecificItemCount("1")); // quantity 2
        assertEquals(2, order.getTotalItemCount()); // 2 is the total item
    }
    @Test
    public void testDuplicateItemIncreaseQuantity() {
        Order order = new Order("testCustomer", "testOrder");
        order.addItem("1",1);
        order.addItem("1",4);
        assertEquals(5, order.getSpecificItemCount("1")); // 4+1 = 5
        assertEquals(1, order.getUniqeItemCount()); // just 1 specific item
    }
    @Test
    public void testNotExistItem() {
        Order order = new Order ("testCustomer","testorder");
        order.addItem("234",4); // ItemID 234 doesnt exist
        assertEquals(0, order.getUniqeItemCount());
        assertEquals(0, order.getTotalItemCount());
    }
    @Test
    public void testCustomerOrderID() {
        Order order = new Order ("testCustomer","testorder");
        assertEquals("testcustomer123", order.getCustomerID());
        assertEquals("5", order.getOrderID());
    }
    @Test
    public void testSubtotal() {
        Order order = new Order ("testCustomer","testorder");
        order.addItem("1",1); // 1,Deli_Burger,5.99,2,FastFood,10,Y
        order.addItem("5",1); //5,Coffee,2.99,10,Beverage,10,N

        double deliBurger = 5.99 * 0.9 * 1.09375;
        double coffee = 2.99 * 0.9 * 1.09375;
        double subtotal = deliBurger + coffee;
        double actualSubtotal = order.getSubTotal();
        assertEquals(subtotal, actualSubtotal);
    }
}

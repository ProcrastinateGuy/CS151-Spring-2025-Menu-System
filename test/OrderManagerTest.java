import org.junit.Test;
import static org.junit.Assert.*;

public class OrderManagerTest {

    @Test
    public void testGenerateID() {
        OrderManager orderManager = new OrderManager();
        String id = orderManager.generateID();

    }
    @Test
    public void testCreateOrder() throws InvalidArgumentException {
        OrderManager orderManager = new OrderManager();
        orderManager.createOrder("customer123", "2", 2);

        String IDofOrder = null;
        for (String key : orderManager.ordersMap.keySet()) {
            if (key.equals("customer123")) {
                IDofOrder = key;
            }
        }
        assertEquals("customer123", orderManager.getcustomerID());
    }





}

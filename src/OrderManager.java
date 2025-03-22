//order manager
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Callable;


public class OrderManager implements ManagerInterface <Order>{
    HashMap<String, Order> ordersMap = new HashMap<>();
    // interface methods
    @Override
    public String generateID( ) {
        UUID uniqueID = UUID.randomUUID();
        return "order".concat(uniqueID.toString());
    }

    @Override
    public Order getMember(String memberID) {
        if(ordersMap.containsKey(memberID)){ return ordersMap.get(memberID);}

        System.out.println("OrderID: " + memberID +" not found.");
        return null;
    }

    private double runWithCheckingDouble(Callable<Double> callable, String orderID) throws InvalidArgumentException{
        try{
            if(!ordersMap.containsKey(orderID)){
                throw new InvalidArgumentException(
                    "OrderID not found in Manager", new Throwable());
            }
            return callable.call();
        }catch(Exception e){
            System.out.println("Error performing the task: " + e.getMessage());
            return -1;
        }
    }

    private int runWithCheckingInteger(Callable<Integer> callable, String orderID, String itemID) throws InvalidArgumentException{
        try{
            if(!ordersMap.containsKey(orderID) || !ordersMap.get(orderID).containsItem(itemID)){
                throw new InvalidArgumentException(
                    "OrderID not found in Manager", new Throwable());
            }
            return callable.call();
        }catch(Exception e){
            System.out.println("Error performing the task: " + e.getMessage());
            return -1;
        }

    }


    // it doesn't make sense to create an Order with no items,
    // so creating an order without an initial Item is not considered
    public void createOrder(String customerID, String initialItemID, int quantity) throws InvalidArgumentException {
        if(quantity < 0){ quantity = 1;} //default the quantity to 1 if invalid

        Order order  = new Order(customerID, generateID());
        order.addItem(initialItemID, quantity); //default to 1 item when input is invalid
        ordersMap.put(order.getOrderID(), order);
    }

    public int getIndividualItemCount(String orderID, String itemID) {
        return runWithCheckingInteger(
            () -> {
                return ordersMap.get(orderID).getSpecificItemCount(itemID);
            }, orderID, itemID);
    }
    public int getOrderItemCount(String orderID) throws InvalidArgumentException {
        return runWithCheckingInteger(
            () -> {return ordersMap.get(orderID).getTotalItemCount();
                }, orderID, "");
            // the empty string is for the helper method's parameter list
    }

    public int getUniqueItemCount(String orderID) throws InvalidArgumentException {
        return runWithCheckingInteger(
            () -> {return ordersMap.get(orderID).getUniqueItemCount();
            }, orderID, "");
            // the empty string is for the helper method's parameter list
    }

    public double getOrderTotal(String orderID) throws InvalidArgumentException {
        return runWithCheckingDouble(
            () -> {return Double.valueOf(ordersMap.get(orderID).getSubtotal());
            }, orderID);
    }

    public void printOrder(String orderID) throws InvalidArgumentException {
        if(ordersMap.containsKey(orderID)){
            System.out.println(ordersMap.get(orderID).toString());
            System.out.println("Items: ");
            ordersMap.get(orderID).printOrderItems();
        }else{
            System.out.println("OrderID: " + orderID + " not found.");
            throw new InvalidArgumentException("OrderID not found in Manager", new Throwable());
        }


    }
}

//order manager
import java.util.UUID;

public class OrderManager implements ManagerInterface <Order>{

    // interface methods
    @Override
    public String generateID( ) {
        UUID uniqueID = UUID.randomUUID();
        return "order".concat(uniqueID.toString());
    }

    @Override
    public String getID(Order orderToCheck) {

        return "";
    }

    @Override
    public Order getMember(String memberID) {
        return null;
    }
}

// deal manager
import java.util.UUID;


public class DealManager implements ManagerInterface <Deal> {
    @Override
    public String generateID( ) {
        UUID uniqueID = UUID.randomUUID();
        return "deal".concat(uniqueID.toString());
    }

    @Override
    public String getID(Deal orderToCheck) {

        return "";
    }

    @Override
    public Deal getMember(String memberID) {
        return null;
    }
}

}

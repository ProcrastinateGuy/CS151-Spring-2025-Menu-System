//Review Manager
import java.util.UUID;

public class ReviewManager implements ManagerInterface <Review> {
    @Override
    public String generateID() {
        UUID uniqueID = UUID.randomUUID();
        return "deal".concat(uniqueID.toString());
    }

    @Override
    public String getID(Review reviewToCheck) {

        return "";
    }

    @Override
    public Review getMember(String reviewID) {

        return null;
    }
}

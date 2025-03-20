//Review Manager
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class ReviewManager implements ManagerInterface <Review> {

    // a map that maintains the order of keys
    // implemented with a RB Tree
    SortedMap<String, Review> incomingReviewsMap = new TreeMap<>();
    SortedMap<String, Review> outgoingReviewsMap = new TreeMap<>();



    // interface methods
    @Override
    public String generateID() {
        UUID uniqueID = UUID.randomUUID();
        return "review".concat(uniqueID.toString());
    }

    @Override
    public Review getMember(String reviewID) {
        // check which map has the key
        if(incomingReviewsMap.containsKey(reviewID)){
            return incomingReviewsMap.get(reviewID);
        }
        else if(outgoingReviewsMap.containsKey(reviewID)){
            return outgoingReviewsMap.get(reviewID);
        }
        else{
            // if neither map has the key, output error message
            System.out.println("Member not found");
            return null;
        }

    }

    public void printReview(String reviewID) {

    }
    //Review()
    public void writeReview(String writerID, String targetID,
                            double rating, String reviewText){

        Review outgoingReview = new OutgoingReview(generateID(), writerID, targetID, rating, reviewText);
    }

    public SortedMap <String, Review> sortReview(boolean isIncoming){

        SortedMap <String, Review> sortedReviewMap = new TreeMap<>();

        return sortedReviewMap ;
    }
}

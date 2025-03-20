
import java.time.LocalDateTime;

public class IncomingReview extends Review {

    private boolean flagged = false;

    ////constructors
    //no argument constructor
    public IncomingReview() {
        super();
        flagged = false;
    }

    //constructor
    public IncomingReview(String reviewId, CustomerAccount writer, CustomerAccount target,
            double rating, String reviewText, LocalDateTime reviewDate) {
        super(reviewId, writer, target, rating, reviewText, reviewDate);
        flagged = false;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void flagReview(CustomerAccount caller) {
        this.flagged = true;
        System.out.println("Incoming review " + reviewId + " has been flagged for moderation by "
                + caller.getCustomerName());
    }

    @Override
    public void printReview() {
        System.out.println("----- Incoming Review -----");
        System.out.println("By: " + getWriterID() + " on " + getReviewDate());
        System.out.println("Rating: " + getRating() + " (Flagged: " + flagged + ")");
        System.out.println("Comment: " + getReviewText());
        System.out.println("----------------------------");
    }
}

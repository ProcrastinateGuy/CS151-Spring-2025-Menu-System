
import java.time.LocalDateTime;

public class IncomingReview extends Review {

    protected boolean isFlaggedForModeration;

    protected IncomingReview(String reviewId, CustomerAccount customerAccount, double rating, String reviewText, LocalDateTime reviewDate, boolean isFlaggedForModeration) {
        super(reviewId, customerAccount, rating, reviewText, reviewDate);
        this.isFlaggedForModeration = isFlaggedForModeration;
    }

    public boolean isFlaggedForModeration() {
        return isFlaggedForModeration;
    }

    public void setFlaggedForModeration(boolean flaggedForModeration) {
        isFlaggedForModeration = flaggedForModeration;
    }

    public void flagReviewForModeration() {
        this.isFlaggedForModeration = true;
        System.out.println("Incoming review " + reviewId + " has been flagged for moderation by " + customerAccount.getCustomerName());
    }

    @Override
    public void writeReview() {
        if (isDeleted) {
            System.out.println("This review has been deleted.");
            return;
        }
        System.out.println("----- Incoming Review -----");
        System.out.println("By: " + customerAccount.getCustomerName() + " on " + reviewDate);
        System.out.println("Rating: " + rating + " (Flagged: " + isFlaggedForModeration + ")");
        System.out.println("Comment: " + reviewText);
        System.out.println("----------------------------");
    }

}

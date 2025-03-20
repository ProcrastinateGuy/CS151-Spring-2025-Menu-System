
import java.time.LocalDateTime;

public class OutgoingReview extends Review {

    protected boolean isVerifiedPurchase;

    public OutgoingReview(String reviewId, String customerAccountID, double rating, String reviewText, LocalDateTime reviewDate, boolean isVerifiedPurchase) {
        super(reviewId, customerAccountID, rating, reviewText, reviewDate);
        this.isVerifiedPurchase = isVerifiedPurchase;
    }

    public boolean isVerifiedPurchase() {
        return isVerifiedPurchase;
    }

    public void setVerifiedPurchase(boolean verifiedPurchase) {
        isVerifiedPurchase = verifiedPurchase;
    }

    public void markAsVerified() {
        this.isVerifiedPurchase = true;
        System.out.println("Review approved by " + customerAccount.getCustomerName());
    }

    public void markAsUnverified() {
        this.isVerifiedPurchase = false;
        System.out.println("Review rejected by " + customerAccount.getCustomerName());
    }

    @Override
    public void writeReview() {
        System.out.println("----- Outcoming Review -----");
        System.out.println("Outgoing Review by " + customerAccount.getCustomerName() + " on " + reviewDate);
        System.out.println("Rating: " + rating + " (Verified: " + isVerifiedPurchase + ")");
        System.out.println("Comment:" + reviewText);
        System.out.println("----------------------------");
    }
}

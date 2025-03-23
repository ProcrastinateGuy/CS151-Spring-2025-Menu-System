
import java.time.LocalDateTime;

public class OutgoingReview extends Review {

    protected boolean isVerifiedPurchase;

    ////constructors
    //no argument constructor
    public OutgoingReview() {
        super();
        isVerifiedPurchase = false;
    }

    //constructor
    public OutgoingReview(String reviewId, CustomerAccount writer, CustomerAccount target,
            double rating, String reviewText, LocalDateTime reviewDate,
            boolean isVerifiedPurchase) {
        super(reviewId, writer, target, rating, reviewText, reviewDate);
        this.isVerifiedPurchase = isVerifiedPurchase;
    }

    public boolean isVerifiedPurchase() {
        return isVerifiedPurchase;
    }

    public void setVerifiedPurchase(boolean verifiedPurchase) {
        isVerifiedPurchase = verifiedPurchase;
    }

    public void markAsVerified(CustomerAccount reviewer) {
        this.isVerifiedPurchase = true;
        System.out.println("Review approved by " + reviewer.getCustomerName());
    }

    public void markAsUnverified(CustomerAccount reviewer) {
        this.isVerifiedPurchase = false;
        System.out.println("Review rejected by " + reviewer.getCustomerName());
    }

    public void editReview(String newReviewText, double newRating) {
        super.setReviewText(newReviewText);
        super.setRating(newRating);
        super.setReviewDate(LocalDateTime.now());
        super.setEdited(true);
    }

    public void deleteReview() {
        setWriter(null);
        setTarget(null);
        setRating(-1.0);
        setReviewText(null);
        setReviewDate(null);
    }

    @Override
    public void printReview() {
        super.printReview();
    }
}

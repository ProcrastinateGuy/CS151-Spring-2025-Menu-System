
import java.time.LocalDateTime;

public class IncomingReview extends Review {

    public Review(String reviewId, CustomerAccount customerAccount, double rating, String reviewText, LocalDateTime reviewDate) {
        this.reviewId = reviewId;
        this.customerAccount = customerAccount;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

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
        System.out.println("----- Incoming Review -----");
        System.out.println("By: " + customerAccount.getCustomerName() + " on " + reviewDate);
        System.out.println("Rating: " + rating + " (Flagged: " + isFlaggedForModeration + ")");
        System.out.println("Comment: " + reviewText);
        System.out.println("----------------------------");
    }

    public void writeReview() {
        System.out.println("Review written by " + customerAccount.getCustomerName() + " on " + reviewDate);
        System.out.println("Rating: " + rating);
        System.out.println(reviewText);
    }

    public void editReview(String newReviewText, double newRating) {
        this.rating = newRating;
        this.reviewText = newReviewText;
        this.reviewDate = LocalDateTime.now();
        System.out.println("Review edited by " + customerAccount.getCustomerName() + " on " + reviewDate);
        System.out.println("Rating: " + rating);
        System.out.println(reviewText);
    }

    public void deleteReview() {
        this.reviewId = null;
        this.customerAccount = null;
        this.rating = 0;
        this.reviewText = null;
        this.reviewDate = null;
        System.out.println("Review deleted by " + customerAccount.getCustomerName());
    }

    public String getReviewId() {
        return reviewId;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public double getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

}


import java.time.LocalDateTime;

public abstract class Review {

    protected String reviewId;
    protected CustomerAccount customerAccount;
    protected double rating;
    protected String reviewText;
    protected LocalDateTime reviewDate;
    protected boolean isDeleted = false; // Flag to indicate if this review is deleted or not

    public Review(String reviewId, CustomerAccount customerAccount, double rating, String reviewText, LocalDateTime reviewDate) {
        this.reviewId = reviewId;
        this.customerAccount = customerAccount;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    public abstract void writeReview();

    public void editReview(String newReviewText, double newRating) {
        if (isDeleted) {
            System.out.println("Cannot edit a deleted review.");
            return;
        }
        this.rating = newRating;
        this.reviewText = newReviewText;
        this.reviewDate = LocalDateTime.now();
        System.out.println("Review edited by " + customerAccount.getCustomerName() + " on " + reviewDate);
        System.out.println("Rating: " + rating);
        System.out.println(reviewText);
    }

    public void deleteReview() {
        if (isDeleted) {
            System.out.println("Review already deleted.");
            return;
        }
        System.out.println("Review deleted by " + customerAccount.getCustomerName());
        this.isDeleted = true;
    }

    // ----*Getters*----
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

    public boolean isDeleted() {
        return isDeleted;
    }

    // ----*Setters*----
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

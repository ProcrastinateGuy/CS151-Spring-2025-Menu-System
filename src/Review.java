
import java.time.LocalDateTime;

public class Review {

    protected String reviewId;
    protected CustomerAccount customerAccount;
    protected double rating;
    protected String reviewText;
    protected LocalDateTime reviewDate;

    public Review(String reviewId, CustomerAccount customerAccount, double rating, String reviewText, LocalDateTime reviewDate) {
        this.reviewId = reviewId;
        this.customerAccount = customerAccount;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
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

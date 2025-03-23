
import java.time.LocalDateTime;

public abstract class Review {

    protected String reviewId;
    protected CustomerAccount writer;
    protected CustomerAccount target;

    protected double rating;
    protected String reviewText;
    protected LocalDateTime reviewDate;

    protected boolean edited;
    ////constructors
    // no argument constructor
    public Review(){
        CustomerAccount defaultCustomer = new CustomerAccount();
        setReviewId("-1");
        setWriter(defaultCustomer);
        setTarget(defaultCustomer);
        setRating(-1.0);
        setReviewText("default review");
        setReviewDate(LocalDateTime.of(1500, 1, 1, 0, 0, 0));
    }

    // constructor
    public Review(String reviewId, CustomerAccount writer, CustomerAccount target,
                  double rating, String reviewText, LocalDateTime reviewDate) {
        setReviewId(reviewId);
        setWriter(writer);
        setTarget(target);
        setRating(rating);
        setReviewText(reviewText);
        setReviewDate(LocalDateTime.of(1500, 1, 1, 0, 0, 0));
    }
    protected void printReview(){
        System.out.println("Reviewed by " + getWriterName() + " on "
                            + getReviewDate().toString());
        System.out.println("Rating: " + getRating());
        System.out.println("Review:" + reviewText);
        }
    //getter and setter
    //getters
    public String getReviewID() {
        return reviewId;
    }

    public String getWriterID() {
        return writer.getCustomerID();
    }

    public String getTargetID() {
        return target.getCustomerID();
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
    public String getWriterName() { return writer.getCustomerName(); }
    public String getTargetName() { return target.getCustomerName(); }

    public boolean isEdited() {
        return edited;
    }

    //setters
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setWriter(CustomerAccount writer) {
        this.writer = writer;
    }

    public void setTarget(CustomerAccount target) {
        this.target = target;
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

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}

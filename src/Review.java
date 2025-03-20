
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Review {

    protected String reviewId;
    protected String writerID;
    protected String targetID;
    protected double rating;
    protected String reviewText;
    protected LocalDateTime reviewDate;

    ////constructors
    // no argument constructor
    public Review(){
        setWriterID("default ID");
        setTargetID("default ID");
        setRating(-1.0);
        setReviewText("default review");
        setReviewDate(LocalDateTime.of(1500, 1, 1, 0, 0, 0));
    }

    // constructor
    public Review(String reviewId, String writerID, String targetID, double rating, String reviewText) {
        setReviewId(reviewId);
        setWriterID(writerID);
        setTargetID(targetID);
        setRating(rating);
        setReviewText(reviewText);
    }

    //getter and setter
    //getters
    public String getReviewId() {
        return reviewId;
    }

    public String getWriterID() {
        return writerID;
    }

    public String getTargetID() {
        return targetID;
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

    //setters
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public void setTargetID(String targetID) {
        this.targetID = targetID;
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

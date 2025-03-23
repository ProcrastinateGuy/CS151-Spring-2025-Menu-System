
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IncomingReview extends Review {

    private boolean flagged = false;

    ////constructors
    //no argument constructor
    public IncomingReview(){
        super();
        flagged = false;
    }

    //constructor
    public IncomingReview(String reviewId, CustomerAccount writer, CustomerAccount target,
                          double rating, String reviewText, LocalDateTime reviewDate) {
        super(reviewId, writer, target, rating, reviewText, reviewDate );
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        System.out.println("----- Incoming Review -----");
        System.out.println(
            (isFlagged()) ? " -** This Review was flagged for investigation  **- " : "");
        super.printReview();
        System.out.println("----------------------------");
    }
}

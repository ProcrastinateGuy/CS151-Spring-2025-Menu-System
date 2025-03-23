import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
public class ReviewTest {

    public class TestOfReview extends Review {

        public TestOfReview(String reviewId, CustomerAccount writer, CustomerAccount target,
                      double rating, String reviewText, LocalDateTime reviewDate) {
            super();
        }
    }
    @Test
    public void testConstructorValues() {
        Review review = new TestOfReview("123", new CustomerAccount(), new CustomerAccount(), 4.2, "Amazing", LocalDateTime.now());
        assertEquals("123", review.getReviewID());
        assertEquals("Name", review.getReviewID());
        assertEquals("Name", review.getReviewID());
        assertEquals(4.2, review.getRating());
        assertEquals("Amazing", review.getReviewText());
    }
    @Test
    public void testEdited() {
        Review review = new TestOfReview("123", new CustomerAccount(), new CustomerAccount(), 4.2, "Amazing", LocalDateTime.now());
        assertFalse(review.isEdited());
        review.setEdited(true);
        assertTrue(review.isEdited());
    }
    @Test
    public void testGetSet() {
        CustomerAccount writer = new CustomerAccount("sdlf", "Writer", "123456789");
        CustomerAccount target = new CustomerAccount("wert", "Target", "123456789");
        LocalDateTime now = LocalDateTime.now();
        Review review = new TestOfReview("456", writer, target, 3.9, "Awesome", now);
        assertEquals("Writer", review.getWriterName());
        assertEquals("Target", review.getTargetName());
        assertEquals("sdlf", review.getWriterID());
        assertEquals("wert", review.getTargetID());
        assertEquals(3.9, review.getRating());
        assertEquals("Awesome", review.getReviewText());
        assertEquals(now, review.getReviewDate());
    }



}


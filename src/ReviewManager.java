//Review Manager
import com.sun.source.tree.Tree;

import java.time.LocalDateTime;
import java.util.*;

public class ReviewManager implements ManagerInterface <Review> {

    // a map that maintains the order of keys
    // implemented with a RB Tree
    SortedMap<String, IncomingReview> incomingReviewsMap = new TreeMap<>();
    SortedMap<String, OutgoingReview> outgoingReviewsMap = new TreeMap<>();



    // interface methods
    @Override
    public String generateID() {
        UUID uniqueID = UUID.randomUUID();
        return "review".concat(uniqueID.toString());
    }

    @Override
    public Review getMember(String reviewID) {
        // check which map has the key
        if(incomingReviewsMap.containsKey(reviewID)){
            return incomingReviewsMap.get(reviewID);
        }
        else if(outgoingReviewsMap.containsKey(reviewID)){
            return outgoingReviewsMap.get(reviewID);
        }
        else{
            // if neither map has the key, output error message
            System.out.println("Member not found");
            return null;
        }

    }

    public void printReview(String reviewID) {
        if(incomingReviewsMap.containsKey(reviewID)){
            incomingReviewsMap.get(reviewID).printReview();
        }
        else if(outgoingReviewsMap.containsKey(reviewID)){
            outgoingReviewsMap.get(reviewID).printReview();
        }
        else{
            System.err.println(
            "Review with ID: " + reviewID + "does not exist.");
        }
    }

    public void printReview(PriorityQueue<Review> heap) {
        while(!heap.isEmpty()){
            heap.poll().printReview();
        }
    }

    //methods for outgoing Reviews
    public void editReview(String reviewID, String newReviewText, double newRating) {
        outgoingReviewsMap.get(reviewID).editReview(newReviewText, newRating);
    }

    public void deleteReview(String reviewID) {
        outgoingReviewsMap.get(reviewID).deleteReview();
    }

    public void generateReview(CustomerAccount writer, CustomerAccount target,
                               double rating, String reviewText){
        String reviewID = generateID();
        outgoingReviewsMap.put( reviewID,
            new OutgoingReview(reviewID, writer, target,
                    rating, reviewText, LocalDateTime.now(), false));
    }

    //methods for incoming Reviews
    public void receiveReview(IncomingReview review){
        incomingReviewsMap.put(review.getReviewID(), review);
    }

    public void flagReview(String reviewID, CustomerAccount caller){
        incomingReviewsMap.get(reviewID).flagReview(caller);
    }


    //methods for both types of reviews
    public PriorityQueue<Review> sortReview(TreeMap<String,Review> map, String sortMethod){

        PriorityQueue <Review> sortedReviewHeap;

        switch(sortMethod){

            case "date" -> {
                Comparator<Review> comparator = Comparator.comparing(Review::getReviewDate);
                sortedReviewHeap = new PriorityQueue<>(comparator);
                sortedReviewHeap.addAll(map.values());
            }
            case "highest" -> {
                sortedReviewHeap = new PriorityQueue<>(Comparator.comparingDouble(Review::getRating));
                sortedReviewHeap.addAll(map.values());
            }
            case "lowest" -> {
                PriorityQueue<Review> tmpHeap = new PriorityQueue<>(Comparator.comparingDouble(Review::getRating));
                tmpHeap.addAll(map.values());
                sortedReviewHeap = new PriorityQueue<>(Collections.reverseOrder());
                sortedReviewHeap.addAll(tmpHeap);
            }
            default -> {
                sortedReviewHeap = new PriorityQueue<>();
                sortedReviewHeap.addAll(map.values());

            }
        }
        return sortedReviewHeap ;
    }

}

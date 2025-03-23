//Review Manager

import java.time.LocalDateTime;
import java.util.*;

public class ReviewManager implements ManagerInterface<Review> {

    // a map that maintains the order of keys
    // implemented with a RB Tree
    SortedMap<String, IncomingReview> incomingReviewsMap = new TreeMap<>();
    SortedMap<String, OutgoingReview> outgoingReviewsMap = new TreeMap<>();

    public void writeReview(String writerID, String targetID, double rating, String reviewText, boolean isVerifiedPurchase) {
        String reviewID = generateID();
        // Create new CustomerAccount objects using the provided IDs.
        CustomerAccount writerAcc = new CustomerAccount();
        writerAcc.setCustomerID(writerID);
        CustomerAccount targetAcc = new CustomerAccount();
        targetAcc.setCustomerID(targetID);

        OutgoingReview outgoingReview = new OutgoingReview(
                reviewID, writerAcc, targetAcc, rating, reviewText, LocalDateTime.now(), isVerifiedPurchase
        );
        outgoingReviewsMap.put(reviewID, outgoingReview);
        System.out.println("Review successfully written:");
        outgoingReview.printReview();
    }

    // interface methods
    @Override
    public String generateID() {
        UUID uniqueID = UUID.randomUUID();
        return "review".concat(uniqueID.toString());
    }

    @Override
    public Review getMember(String reviewID) {
        // check which map has the key
        if (incomingReviewsMap.containsKey(reviewID)) {
            return incomingReviewsMap.get(reviewID);
        } else if (outgoingReviewsMap.containsKey(reviewID)) {
            return outgoingReviewsMap.get(reviewID);
        } else {
            // if neither map has the key, output error message
            System.out.println("Member not found");
            return null;
        }

    }

    public void printReview(String reviewID) {
        if (incomingReviewsMap.containsKey(reviewID)) {
            incomingReviewsMap.get(reviewID).printReview();
        } else if (outgoingReviewsMap.containsKey(reviewID)) {
            outgoingReviewsMap.get(reviewID).printReview();
        } else {
            System.err.println(
                    "Review with ID: " + reviewID + "does not exist.");
        }
    }

    public void printReview(PriorityQueue<Review> heap) {
        while (!heap.isEmpty()) {
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
            double rating, String reviewText) {
        String reviewID = generateID();
        outgoingReviewsMap.put(reviewID,
                new OutgoingReview(reviewID, writer, target,
                        rating, reviewText, LocalDateTime.now(), false));
    }

    //methods for incoming Reviews
    public void receiveReview(IncomingReview review) {
        incomingReviewsMap.put(review.getReviewID(), review);
    }

    public void flagReview(String reviewID, CustomerAccount caller) {
        incomingReviewsMap.get(reviewID).flagReview(caller);
    }

    //methods for both types of reviews
    public PriorityQueue<Review> sortReview(TreeMap<String, Review> map, String sortMethod) {

        PriorityQueue<Review> sortedReviewHeap;

        switch (sortMethod) {

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
        return sortedReviewHeap;
    }

    public void viewAllReviews(boolean isIncoming) {
        SortedMap<String, Review> sortedReviews = new TreeMap<>();

        if (isIncoming) {
            // Copying  inComing reviews to sorted map
            for (IncomingReview review : incomingReviewsMap.values()) {
                sortedReviews.put(review.getReviewID(), review);
            }
        } else {
            // Copy all outgoing reviews to the sorted map
            for (OutgoingReview review : outgoingReviewsMap.values()) {
                sortedReviews.put(review.getReviewID(), review);
            }
        }

        if (sortedReviews.isEmpty()) {
            System.out.println("No reviews available.");
        } else {
            for (Review review : sortedReviews.values()) {
                review.printReview();
            }
        }
    }

    // Converting PriorityQueue to ArrayList
    public ArrayList<Review> heapToList(PriorityQueue<Review> heap) {
        ArrayList<Review> list = new ArrayList<>();
        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }
        return list;
    }

    public void viewFilteredReviews(boolean isIncoming, String filter) {
        SortedMap<String, Review> sorted = new TreeMap<>();
        if (isIncoming) {
            for (IncomingReview review : incomingReviewsMap.values()) {
                sorted.put(review.getReviewID(), review);
            }
        } else if (!isIncoming) {
            for (OutgoingReview review : outgoingReviewsMap.values()) {
                sorted.put(review.getReviewID(), review);
            }
        } else {
            if (sorted.isEmpty()) {
                System.out.println("No reviews available.");
                return;
            } else {
                System.out.println("Reviews neihter inComing nor outGoing.");
                return;
            }
        }

        PriorityQueue<Review> reviewedHeap;
        switch (filter) {
            case "newest":
                // Newest to oldest
                reviewedHeap = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparing(Review::getReviewDate)));
                break;
            case "oldest":
                // Oldest to newest
                reviewedHeap = new PriorityQueue<>(Comparator.comparing(Review::getReviewDate));
                break;
            case "highest":
                // Highest rating to lowest
                reviewedHeap = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingDouble(Review::getRating)));
                break;
            case "lowest":
                // Lowest rating to highest
                reviewedHeap = new PriorityQueue<>(Comparator.comparingDouble(Review::getRating));
                break;
            default:
                System.out.println("Invalid filter option provided.");
                return;
        }
        reviewedHeap.addAll(sorted.values());

        // Using converting method from heap to list
        ArrayList<Review> filteredReviews = heapToList(reviewedHeap);

        for (Review review : filteredReviews) {
            review.printReview();
        }
    }

}

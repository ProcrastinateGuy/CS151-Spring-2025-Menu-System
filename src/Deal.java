import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *  Deal Object that should work on both individual items and orders
*/
public class Deal {

    private float dealModifier; // Self explanatory, affects price of item/order
    private LocalDateTime startDate; // Date when deal starts
    private LocalDateTime endDate; // Date when deal ends
    private String dealCode; //Deal code to check if user inputs correct deal code

    ////Constructors
    //no argument constructor
    public Deal() {
        this.dealModifier = 1;
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusDays(7);
        this.dealCode = "deal";
    }

    //constructor
    public Deal(float dealModifier, String startDate, String endDate, String dealCode) {
        if (dealModifier > 1) {
            throw new IllegalArgumentException("Deal Modifier cannot be greater than 1");
        }

        if (convertDateFormat(startDate).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date cannot be before current date");
        }

        if (convertDateFormat(endDate).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("End date cannot be before current date");
        }
        if (convertDateFormat(endDate).isBefore(convertDateFormat(startDate))) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        this.dealModifier = dealModifier;
        this.startDate = convertDateFormat(startDate);
        this.endDate = convertDateFormat(endDate);
        this.dealCode = dealCode;
    }

    //Setters and Getters
    public float getDealModifier() {
        return dealModifier;
    }

    public void setDealModifier(float dealModifier) {
        if (dealModifier > 1) {
            throw new IllegalArgumentException("Deal Modifier cannot be greater than 1");
        }
        this.dealModifier = dealModifier;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    //Dates should be 
    public void setStartDate(String startDate) {

        if (convertDateFormat(startDate).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date cannot be before current date");
        }
        this.startDate = convertDateFormat(startDate);

    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    
    public void setEndDate(String endDate) {
        if (convertDateFormat(endDate).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("End date cannot be before current date");
        }
        if (convertDateFormat(endDate).isBefore(this.startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.endDate = convertDateFormat(endDate);
    }

    public String getDealCode() {
        return dealCode;
    }

    public void setDealCode(String dealCode) {
        if (dealModifier > 1) {
            throw new IllegalArgumentException("Deal Modifier cannot be greater than 1");
        }
        this.dealCode = dealCode;
    }

    //Helper Method
    public final LocalDateTime convertDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy H:m");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }




    //Debugging
    public void print() {
        System.out.printf("Deal Modifier: %.2f\n", this.dealModifier);
        System.out.println("Test Date: " + LocalDateTime.now());
        System.out.printf("Start Date: %s\n", this.startDate);
        System.out.printf("End Date: %s\n", this.endDate);
        System.out.printf("Deal Code: %s\n", this.dealCode);
    }

    public static void main(String[] args) {

        
        System.out.println("Valid Dates");
        Deal deal = new Deal(0.7f, "10-01-2026 12:00", "10-08-2027 12:00", "deal");
        deal.print();

        //Write a deal object with invalid dates
        System.out.println("Invalid End Date");
        Deal deal2 = new Deal(0.7f, "03-18-2025 12:00", "10-08-2020 12:00", "deal");
        deal2.print();

        System.out.println("Invalid Start Date");
        Deal deal3 = new Deal(0.7f, "10-08-2020 12:00", "10-01-2026 12:00", "deal");
        deal3.print();

        System.out.println("Invalid Deal Modifier");
        Deal deal4 = new Deal(1.5f, "10-01-2020 12:00", "10-08-2020 12:00", "deal");
        deal4.print();
    }
}

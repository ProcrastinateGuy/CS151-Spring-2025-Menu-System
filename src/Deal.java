import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *  Deal Object that should work on both individual items and orders
 * 
 */
public class Deal {
    
    private float dealModifier; // Self explanatory, affects price of item/order
    private LocalDateTime startDate; // Date when deal starts
    private LocalDateTime endDate; // Date when deal ends
    private String dealCode; //Deal code to check if user inputs correct deal code

    //Constructors
    public Deal() {
        this.dealModifier = 1;
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusDays(7);
        this.dealCode = "deal";
    }

    public Deal(float dealModifier, String startDate, String endDate, String dealCode) {
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
        this.dealModifier = dealModifier;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    //Dates should be 
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDealCode() {
        return dealCode;
    }

    public void setDealCode(String dealCode) {
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
        System.out.printf("Start Date: %s\n", this.startDate);
        System.out.printf("End Date: %s\n", this.endDate);
        System.out.printf("Deal Code: %s\n", this.dealCode);
    }

    public static void main(String[] args) {
        Deal deal = new Deal();
        deal.print();

        System.out.println();
        Deal deal2 = new Deal(1.5f, "10-01-2020 12:00", "10-08-2020 12:00", "deal");
        deal2.print();
    }
}

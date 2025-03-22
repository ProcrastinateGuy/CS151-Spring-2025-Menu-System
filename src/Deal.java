import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  Deal Object that should work on both individual items and orders
*/
public class Deal {

    private float dealModifier; // a float value multiplies with the price of item/order
    private LocalDateTime startDate; // Date when deal starts
    private LocalDateTime endDate; // Date when deal ends
    private String dealCode; //Deal code to check if user inputs correct deal code

    ////Constructors
    //no argument constructor
    public Deal() {
        setDealModifier(1);
        setStartDate(LocalDateTime.now().toString());
        setEndDate(LocalDateTime.now().toString());
        setDealCode("default deal");
    }

    //constructor
    public Deal(float dealModifier, String startDate, String endDate, String dealCode) {
        setDealModifier(dealModifier);
        setStartDate(startDate);
        setEndDate(endDate);
        setDealCode(dealCode);
    }

    //Setters and Getters
    //setters
    public void setDealModifier(float dealModifier) {
        if (dealModifier > 1) {
            throw new IllegalArgumentException("Deal Modifier cannot be greater than 1");
        }
        this.dealModifier = dealModifier;
    }

    public void setStartDate(String startDate) {

        if (convertDateFormat(startDate).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date cannot be before current date");
        }
        this.startDate = convertDateFormat(startDate);

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


    public void setDealCode(String dealCode) {
        if (dealModifier > 1) {
            throw new IllegalArgumentException("Deal Modifier cannot be greater than 1");
        }
        this.dealCode = dealCode;
    }

    //getters
    public float getDealModifier() { return dealModifier; }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public String getDealCode() { return dealCode; }


    //Helper Method
    public final LocalDateTime convertDateFormat(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy H:m");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            return dateTime;
        }catch(DateTimeParseException e){
            System.err.println("Error parsing date: " + date +", \n"
                    + "Actual date attribute is set to NULL");
            System.out.println("expected : MM-dd-yyyy H:m");
            return null;
        }
    }

}

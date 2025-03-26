import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class DealTest {
    /*
    //Debugging
    public void print() {
        System.out.printf("Deal Modifier: %.2f\n", this.dealModifier);
        System.out.printf("Start Date: %s\n", this.startDate);
        System.out.printf("End Date: %s\n", this.endDate);
        System.out.printf("Deal Code: %s\n", this.dealCode);
    }*/

    @Test
    public void testDealCreation() {

        String start = "12-25-2026 10.00";
        String end = "12-31-2026 23:59";

        Deal deal = new Deal(0.9f, start, end, "deal" );
        assertEquals(0.9f, deal.getDealModifier());
        assertEquals("deal", deal.getDealCode());
        assertNotNull(deal.getStartDate());
        assertNotNull(deal.getEndDate());
        assertTrue(deal.getEndDate().isAfter(deal.getStartDate()));
    }
    @Test
    public void testInvalidDealModifier(){
        new Deal();

    }

    @Test
    public void DealTest() {
        Deal deal = new Deal();
        deal.print();

        System.out.println();
        Deal deal2 = new Deal(1.5f, "10-01-2020 12:00", "10-08-2020 12:00", "deal");
        deal2.print();
    }

    //Debugging
//    public void print() {
//        System.out.printf("Deal Modifier: %.2f\n", this.dealModifier);
//        System.out.println("Test Date: " + LocalDateTime.now());
//        System.out.printf("Start Date: %s\n", this.startDate);
//        System.out.printf("End Date: %s\n", this.endDate);
//        System.out.printf("Deal Code: %s\n", this.dealCode);
//    }

    // The following Test method and test cases are contributed by
    // WangRyan408( Ryan Wang )
    // https://github.com/WangRyan408

    public static void mainMethod(String[] args) {


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

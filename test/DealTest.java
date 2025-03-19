import org.junit.Test;
import java.time.LocalDate;

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
    public void DealTest() {
        Deal deal = new Deal();
        deal.print();

        System.out.println();
        Deal deal2 = new Deal(1.5f, "10-01-2020 12:00", "10-08-2020 12:00", "deal");
        deal2.print();
    }
}


import java.io.InputStream;
import java.util.Scanner;

public class MenuItemsReader {

    public static void readMenuItems() {
        // Loads Readable_Item_DB.txt from the classpath instead of using an absolute path
        InputStream in = MenuItemsReader.class.getClassLoader().getResourceAsStream("Readable_Item_DB.txt");
        if (in == null) {
            System.err.println("Readable_Item_DB.txt not found on the classpath!");
            return;
        }
        try (Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        readMenuItems();
    }
}


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomFileReader {

    public CustomFileReader(String phoneTestFilePath) {
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/itemDB.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }
}

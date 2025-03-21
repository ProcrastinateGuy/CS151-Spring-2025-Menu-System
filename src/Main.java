
import java.util.Scanner;

public class Main {

    public static void clearScreen() {
        // Clear screen function
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReviewManager reviewManager = new ReviewManager();

        boolean exit = false;
        while (!exit) {
            clearScreen(); // Clear screen at the beginning of each iteration
            System.out.println("*************** Welcome To Our Cafe ***************");
            System.out.println("1. View Menu");
            System.out.println("2. Place an Order");
            System.out.println("3. View Deals/Promos");
            System.out.println("4. Write a Review");
            System.out.println("5. View Reviews");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            System.out.println();
            int option = 0;

            if (input.toLowerCase().equals("exit")) {
                System.out.println("Thank you for using the Food Order System. Goodbye!");
                exit = true;
                break;
            }

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println();
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
                continue;
            }
            System.out.println();

            switch (option) {
                case 1:
                    MenuItemsReader.readMenuItems();
                    System.out.print("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Order placement functionality is not implemented yet.");
                    System.out.print("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Deals are not fully implemented yet.");
                    System.out.print("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 4:
                    // Write a review feature
                    System.out.print("Enter your writer ID: ");
                    String writerID = scanner.nextLine();
                    System.out.print("Enter target ID (restaurant ID): ");
                    String targetID = scanner.nextLine();
                    System.out.print("Enter rating (e.g., 4.5): ");
                    double rating = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter your review: ");
                    String reviewText = scanner.nextLine();
                    System.out.print("Is this a verified purchase? (true/false): ");
                    boolean isVerifiedPurchase = Boolean.parseBoolean(scanner.nextLine());
                    System.out.println();

                    reviewManager.writeReview(writerID, targetID, rating, reviewText, isVerifiedPurchase);
                    System.out.print("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 5:
                    // View reviews feature
                    System.out.print("View (1) Incoming reviews or (2) Outgoing reviews? ");
                    String reviewType = scanner.nextLine();
                    System.out.println();
                    if (reviewType.equals("1")) {
                        reviewManager.viewAllReviews(true);
                    } else if (reviewType.equals("2")) {
                        reviewManager.viewAllReviews(false);
                    } else {
                        System.out.println("Invalid choice for review type.");
                    }
                    System.out.print("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("Thank you for using the Food Order System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid , please choose again.");
                    System.out.print("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
            }
        }
        scanner.close();
    }
}

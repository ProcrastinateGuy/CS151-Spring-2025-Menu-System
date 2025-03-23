
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void clearScreen() {
        // Clear screen function
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void exitProgram(String input) {
        // Exit program function
        if (input.toLowerCase().equals("exit")) {
            System.out.println("Thank you for using the Food Order System. Goodbye!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
       
        ItemShelf shelf = null;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            shelf = new ItemShelf(".\\src\\itemDB.txt");
        } else {
            shelf = new ItemShelf("./src/itemDB.txt");
        }

        AccountManager accountManager = new AccountManager();

        ReviewManager reviewManager = new ReviewManager();
        boolean exit = false;
        do {
            clearScreen(); // Clear screen at the beginning of each iteration
            System.out.println();
            System.out.println(System.getProperty("os.name"));
            System.out.println("*************** Welcome To Our Cafe ***************");
            System.out.println("1. View Menu");
            System.out.println("2. Place an Order");
            System.out.println("3. View Deals/Promos");
            System.out.println("4. Write a Review");
            System.out.println("5. View Reviews");
            System.out.println("6. Exit");
            System.out.println("Note: You can type 'exit' to any prompt to close the app.");
            System.out.print("Enter your choice: ");

            String inputOption = scanner.nextLine();
            exitProgram(inputOption);
            System.out.println();

            switch (inputOption) {
                case "1" -> {
                    shelf.printAllItems();
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
                    String input_1 = scanner.nextLine();
                    exitProgram(input_1);
                }

                case "2" -> {
                    String phoneInput;
                    Runnable[] tasksBeforeinput = {
                        () -> System.out.println("Please enter your phone number"),
                        () -> System.out.println("Please enter your phone number"),
                        () -> System.out.println("Please enter your phone number"),
                        () -> System.out.println("Please enter your phone number"),
                        () -> System.out.println("Please enter your phone number"),
                        () -> System.out.println("Please enter your phone number")
                    };
/*
                    Consumer<String> [] taskAfterInput = new Consumer [] {
                        input ->
                    };
*/
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                    String input_2 = scanner.nextLine();
                    exitProgram(input_2);
                }

                case "3" -> {
                    System.out.println("Deals are not fully implemented yet.");
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");

                    String input_3 = scanner.nextLine();
                    exitProgram(input_3);
                }

                case "4" -> {

                    String userInput;

                    String[] reviewInfo = new String[5];
                    double rating;
                    Runnable[] tasksBeforeinput = {
                        () -> System.out.print("Enter your writer ID: "),
                        () -> System.out.print("Enter target ID (restaurant ID): "),
                        () -> System.out.print("Enter rating (e.g., 4.5): "),
                        () -> System.out.print("Enter your review: "),
                        () -> System.out.print("Is this a verified purchase? (true/false): ")
                    };

                    Consumer<String>[] tasksAfterInput = new Consumer[]{
                        input -> reviewInfo[0] = (String) input,
                        input -> reviewInfo[1] = (String) input,
                        input -> reviewInfo[2] = (String) input,
                        input -> reviewInfo[3] = (String) input,
                        input -> reviewInfo[4] = (String) input
                    };

                    for (int i = 0; i < 5; i++) {
                        tasksBeforeinput[i].run();
                        userInput = scanner.nextLine();
                        exitProgram(userInput);
                        tasksAfterInput[i].accept(userInput);
                    }

                    //check the input
                    try {
                        rating = Double.parseDouble(reviewInfo[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("You have entered an invalid rating, setting to 0.0");
                        rating = 0.0;
                    }

                    reviewManager.writeReview(reviewInfo[0], reviewInfo[1], rating, reviewInfo[3], Boolean.parseBoolean(reviewInfo[4]));
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                    String input_4 = scanner.nextLine();
                    exitProgram(input_4);
                }

                case "5" -> {
                    // View reviews feature
                    System.out.print("View (1) Incoming reviews or (2) Outgoing reviews? ");
                    String reviewType = scanner.nextLine();
                    boolean isIncoming;
                    exitProgram(reviewType);
                    System.out.println();

                    if (reviewType.equals("1")) {
                        isIncoming = true;
                    } else if (reviewType.equals("2")) {
                        isIncoming = false;
                    } else {
                        System.out.println("Invalid choice for review type.");
                        System.out.print("\nPress Enter to return to the main menu...");
                        scanner.nextLine();
                        break;
                    }


                    System.out.print("Do you want to apply a filter? (y/n): ");
                    String applyFilter = scanner.nextLine();
                    if (applyFilter.equalsIgnoreCase("y")) {
                        System.out.println("Select filter option:");
                        System.out.println("1. Newest to Oldest");
                        System.out.println("2. Oldest to Newest");
                        System.out.println("3. Highest Rating to Lowest Rating");
                        System.out.println("4. Lowest Rating to Highest Rating");
                        System.out.print("Enter your choice: ");
                        String filterChoice = scanner.nextLine();
                        String filterOption = "";
                        switch (filterChoice) {
                            case "1":
                                filterOption = "newest";
                                break;
                            case "2":
                                filterOption = "oldest";
                                break;
                            case "3":
                                filterOption = "highest";
                                break;
                            case "4":
                                filterOption = "lowest";
                                break;
                            default:
                                System.out.println("Invalid filter option.");
                                System.out.print("\nPress Enter to return to the main menu...");
                                scanner.nextLine();
                                continue;
                        }
                        reviewManager.viewFilteredReviews(isIncoming, filterOption);
                    } else {
                        // View all reviews without filtering
                        reviewManager.viewAllReviews(isIncoming);
                    }

                    System.out.print("\nPress Enter to return to the main menu...");


                    exitProgram(scanner.nextLine());
                }
                //   case "6" -> {
                case "6" -> {
                    System.out.println("Thank you for using the Food Order System. Goodbye!");
                    exitProgram("exit");
                }


                //default case
                //for invalid input
                default -> System.out.println("Invalid , please choose again.");
            }

        }while(!exit);

        scanner.close();
    }
}

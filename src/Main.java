
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Clear the terminal screen (works on many Unix and Windows terminals)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // If the user types "exit" at any prompt, the app immediately terminates.
    public static void exitProgram(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Thank you for using the Food Order System. Goodbye!");
            System.exit(0);
        }
    }

    // ---------------------------
    // LOGIN / REGISTRATION FLOW
    // ---------------------------
    public static void loginPrompt(AccountManager accountManager) {
        System.out.println("*************** Login ***************");
        System.out.println("Need an account? Type R or r to move to the registration menu. Type anything else to proceed with login.");
        String register = scanner.nextLine();
        exitProgram(register);
        if (register.equalsIgnoreCase("R")) {
            registerPrompt(accountManager);
            return;
        }
        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();
        exitProgram(phone);
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        exitProgram(password);

        if (accountManager.logIn(phone, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please check your credentials.");
            loginPrompt(accountManager);
        }
    }

    public static void registerPrompt(AccountManager accountManager) {
        System.out.println("*************** Registration ***************");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        exitProgram(name);

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        exitProgram(phone);

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        exitProgram(email);

        System.out.print("Enter your Shopping Interests: ");
        String interests = scanner.nextLine();
        exitProgram(interests);

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        exitProgram(address);

        System.out.print("Enter your payment method: ");
        String paymentMethod = scanner.nextLine();
        exitProgram(paymentMethod);

        System.out.print("Enter your birthday (YYYY-MM-DD): ");
        String birthday = scanner.nextLine();
        exitProgram(birthday);

        System.out.print("Do you want text notifications? (Yes/No): ");
        String textMsg = scanner.nextLine();
        exitProgram(textMsg);
        if (textMsg.equalsIgnoreCase("Yes")) {
            textMsg = "true";
        } else if (textMsg.equalsIgnoreCase("No")) {
            textMsg = "false";
        }

        System.out.print("Enter a new password: ");
        String password = scanner.nextLine();
        exitProgram(password);

        // Generate the account and complete the profile
        String customerID = accountManager.generateAccount(name, phone, password);
        accountManager.completeProfile(customerID, email, address, interests, birthday, paymentMethod, Boolean.parseBoolean(textMsg));

        loginPrompt(accountManager);
    }

    // ---------------------------
    // DEALS / PROMOS SUB-MENU
    // ---------------------------
    private static void dealsMenu(DealManager dealManager, ItemShelf shelf) {
        boolean dealsExit = false;
        while (!dealsExit) {
            clearScreen();
            System.out.println("********** Deals / Promos Menu **********");
            System.out.println("1. Create a New Deal (Not Available Yet)");
            System.out.println("2. View All Discounts from Menu");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter your choice: ");

            String dealsInput = scanner.nextLine();
            exitProgram(dealsInput);
            int dealsOption = 0;
            try {
                dealsOption = Integer.parseInt(dealsInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Press Enter to continue or type exit to close the app: ");
                String skip = scanner.nextLine();
                exitProgram(skip);
                continue;
            }
            switch (dealsOption) {
                case 1:
                    System.out.println("Create a New Deal: Feature not available yet.");
                    System.out.print("Press Enter to continue or type exit to close the app: ");
                    String skip1 = scanner.nextLine();
                    exitProgram(skip1);
                    break;
                case 2:
                    printDiscountsFromMenu(shelf);
                    System.out.print("\nPress Enter to continue or type exit to close the app: ");
                    String skip2 = scanner.nextLine();
                    exitProgram(skip2);
                    break;
                case 3:
                    dealsExit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    System.out.print("Press Enter to continue or type exit to close the app: ");
                    String skip3 = scanner.nextLine();
                    exitProgram(skip3);
            }
        }
    }

    // New helper method to print all discount info from the menu
    private static void printDiscountsFromMenu(ItemShelf shelf) {
        System.out.println("----- Discounts from Menu -----");
        // Assume ItemShelf has a method getItems() that returns a List<Item>
        for (Item item : shelf.getItems()) {
            System.out.println("Item: " + item.getName() + " | Discount: " + item.getDiscount() + "%");
        }
    }

    private static void createDeal(DealManager dealManager) {
        System.out.println("Enter deal modifier (<= 1, e.g. 0.8 for 20% off): ");
        String modInput = scanner.nextLine();
        exitProgram(modInput);
        float modifier = 1.0f;
        try {
            modifier = Float.parseFloat(modInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid float input. Setting deal modifier to 1.0");
        }
        System.out.println("Enter start date (MM-dd-yyyy H:m): ");
        String startDate = scanner.nextLine();
        exitProgram(startDate);
        System.out.println("Enter end date (MM-dd-yyyy H:m): ");
        String endDate = scanner.nextLine();
        exitProgram(endDate);
        System.out.println("Enter a custom deal code (or leave blank for auto-generated): ");
        String customID = scanner.nextLine();
        exitProgram(customID);
        try {
            if (customID.isEmpty()) {
                dealManager.generateDeal(modifier, startDate, endDate);
            } else {
                dealManager.generateDeal(modifier, startDate, endDate, customID);
            }
        } catch (Exception e) {
            System.out.println("Failed to create deal: " + e.getMessage());
        }
        System.out.print("\nPress Enter to continue or type exit to close the app: ");
        String skip = scanner.nextLine();
        exitProgram(skip);
    }

    // ---------------------------
    // REVIEWS FLOW
    // ---------------------------
    private static void writeReviewFlow(ReviewManager reviewManager) {
        String[] reviewInfo = new String[5];
        double rating;
        // Runnables to prompt the user
        Runnable[] tasksBeforeInput = {
            () -> System.out.print("Enter your writer ID: "),
            () -> System.out.print("Enter target ID (restaurant ID): "),
            () -> System.out.print("Enter rating (e.g., 4.5): "),
            () -> System.out.print("Enter your review: "),
            () -> System.out.print("Is this a verified purchase? (true/false): ")
        };

        // Use a list of Consumers instead of an array of generic Consumer<String>
        // to avoid generic array issues.
        java.util.List<Consumer<String>> tasksAfterInput = java.util.Arrays.asList(
                input -> reviewInfo[0] = input,
                input -> reviewInfo[1] = input,
                input -> reviewInfo[2] = input,
                input -> reviewInfo[3] = input,
                input -> reviewInfo[4] = input
        );

        for (int i = 0; i < tasksBeforeInput.length; i++) {
            tasksBeforeInput[i].run();
            String userInput = scanner.nextLine();
            exitProgram(userInput);
            tasksAfterInput.get(i).accept(userInput);
        }
        try {
            rating = Double.parseDouble(reviewInfo[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating entered. Setting rating to 0.0");
            rating = 0.0;
        }
        reviewManager.writeReview(reviewInfo[0], reviewInfo[1], rating, reviewInfo[3], Boolean.parseBoolean(reviewInfo[4]));
        System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
        String input_4 = scanner.nextLine();
        exitProgram(input_4);
    }

    private static void viewReviewsFlow(ReviewManager reviewManager) {
        System.out.print("View (1) Incoming reviews or (2) Outgoing reviews? ");
        String reviewType = scanner.nextLine();
        exitProgram(reviewType);
        System.out.println();
        if (reviewType.equals("1")) {
            reviewManager.viewAllReviews(true);
        } else if (reviewType.equals("2")) {
            reviewManager.viewAllReviews(false);
        } else {
            System.out.println("Invalid choice for review type.");
        }
        System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
        String input_5 = scanner.nextLine();
        exitProgram(input_5);
    }

    // ---------------------------
    // MAIN MENU
    // ---------------------------
    public static void main(String[] args) {
        // Load items from file (adjust path as needed)
        ItemShelf shelf;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            shelf = new ItemShelf(".\\src\\itemDB.txt");
        } else {
            shelf = new ItemShelf("./src/itemDB.txt");
        }

        // Instantiate our managers
        AccountManager accountManager = new AccountManager();
        ReviewManager reviewManager = new ReviewManager();
        DealManager dealManager = new DealManager();

        // Login or register first
        loginPrompt(accountManager);

        boolean exit = false;
        while (!exit) {
            clearScreen();
            System.out.println();
            System.out.println(System.getProperty("os.name"));
            System.out.println("*************** Welcome To Our Cafe ***************");
            System.out.println("1. View Menu");
            System.out.println("2. Place an Order");
            System.out.println("3. View Deals/Promos");
            System.out.println("4. Write a Review");
            System.out.println("5. View Reviews");
            System.out.println("6. Exit");
            System.out.println("Note: You can type 'exit' at any prompt to close the app.");
            System.out.print("Enter your choice: ");

            String inputOption = scanner.nextLine();
            exitProgram(inputOption);
            System.out.println();
            int option = 0;
            try {
                option = Integer.parseInt(inputOption);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
                continue;
            }
            System.out.println();
            switch (option) {
                case 1:
                    // View Menu
                    shelf.printAllItems();
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
                    String input_1 = scanner.nextLine();
                    exitProgram(input_1);
                    break;
                case 2:
                    // Place an Order (Not implemented yet)
                    System.out.println("Order placement functionality is not implemented yet.");
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
                    String input_2 = scanner.nextLine();
                    exitProgram(input_2);
                    break;
                case 3:
                    // Deals / Promos sub-menu
                    dealsMenu(dealManager, shelf);
                    break;
                case 4:
                    // Write a Review
                    writeReviewFlow(reviewManager);
                    break;
                case 5:
                    // View Reviews (with filtering option)
                    System.out.print("View (1) Incoming reviews or (2) Outgoing reviews? ");
                    String revType = scanner.nextLine();
                    exitProgram(revType);
                    boolean isIncoming;
                    if (revType.equals("1")) {
                        isIncoming = true;
                    } else if (revType.equals("2")) {
                        isIncoming = false;
                    } else {
                        System.out.println("Invalid review type choice.");
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
                        reviewManager.viewAllReviews(isIncoming);
                    }
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
                    String input_5 = scanner.nextLine();
                    exitProgram(input_5);
                    break;
                case 6:
                    System.out.println("Thank you for using the Food Order System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
                    String fallback = scanner.nextLine();
                    exitProgram(fallback);
                    break;
            }
        }
        scanner.close();
    }
}

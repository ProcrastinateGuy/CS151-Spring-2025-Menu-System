
//import java.lang.classfile.Attribute;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    private static ItemShelf shelf;
    private static Scanner scanner = new Scanner(System.in);
    private static String currentPhoneLoggedIn = null;

    private static void clearScreen() {
        // Clear screen function
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void exitProgram(String input) {
        // Exit program function
        if (input.toLowerCase().equals("exit")) {
            System.out.println("\nThank you for using the Food Order System. Goodbye!");
            System.exit(0);
        }
    }

    private static void loginPrompt(AccountManager accountManager) {
        // Login prompt function
        System.out.println("****************** Login *******************");
        System.out.println("Need an account? Type R to register a new account.");

        System.out.println("Enter your phone number: ");
        String phoneEntered = scanner.nextLine();
        exitProgram(phoneEntered);

        //invoke registration method if input is R/r
        if (phoneEntered.equalsIgnoreCase("R")) {
            registerPrompt(accountManager);
            return;
        }

        currentPhoneLoggedIn = phoneEntered;
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        exitProgram(password);

        if (accountManager.logIn(currentPhoneLoggedIn, password)) {
            System.out.println("Login successful!");
            // Proceed to the next step
        } else {
            System.out.println("Login failed. Please check your credentials.");
            loginPrompt(accountManager);
        }
    }
    private static void printMenu () {
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
    }

    private static void registerPrompt(AccountManager accountManager) {
        // Registration prompt function
        System.out.println("*************** Registration ***************");
        System.out.println("Create a new account");

        System.out.print("Name: ");
        String name = scanner.nextLine();
        exitProgram(name);
        System.out.println();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        exitProgram(phone);
        System.out.println();

        System.out.print("Create a new password: ");
        String password = scanner.nextLine();
        exitProgram(password);
        System.out.println();

        String customerID = accountManager.generateAccount(name, phone, password);

        System.out.println("Account created!");
        System.out.println("We'd love to know you more, do you want to complete your full profile for customized deals? (Y/N)");
        String choice = scanner.nextLine();
        exitProgram(choice);
        if (!choice.equalsIgnoreCase("Y")) {
            System.out.print("It's ok, you can always come back to complete it later.\n");
        }else {

            int numberOfTasks = 6;
            Runnable[] taskBeforeInput = {
                () -> System.out.print("Enter your email: "),
                () -> System.out.print("Enter your address: "),
                () -> System.out.print("Enter your birthday (YYYY-MM-DD): "),
                () -> System.out.print("Enter your payment method: "),
                () -> System.out.print("Enter your Shopping Interests: "),
                () -> System.out.print("Do you want text notifications? (Y/N): ")
            };

            HashMap<String, String> profileInfo = new HashMap<>();
            Consumer<String>[] taskAfterInput = new Consumer []{
                input -> profileInfo.put( "email",(String) input),
                input -> profileInfo.put( "address",(String) input),
                input -> profileInfo.put( "birthday",(String) input),
                input -> profileInfo.put( "payment",(String) input),
                input -> profileInfo.put( "interest",(String) input),
                input -> profileInfo.put( "textNotification",(String) input),
            };

            System.out.println("----------------Edit Profile----------------");


            for(int i = 0; i < numberOfTasks; i++) {
                taskBeforeInput[i].run(); //show prompt
                String input = scanner.nextLine(); //get input
                exitProgram(input); //check input
                taskAfterInput[i].accept(input); //store the value
            }
            System.out.println();

            boolean textNotification = profileInfo.get("textNotification").equalsIgnoreCase("Y");
            accountManager.completeProfile(customerID, profileInfo.get("email"), profileInfo.get("address"),
                profileInfo.get("interest"), profileInfo.get("birthday"), profileInfo.get("payment"), textNotification);

        }
        //call another login session
        loginPrompt(accountManager);
    }

    public static void main (String[]args){


        ItemShelf shelf = new ItemShelf();

        AccountManager accountManager = new AccountManager();

        //test account for dev
        accountManager.generateAccount("123", "1234567890", "123");
        //CustomerAccount currentAccount =  accountManager.getAccountByPhone("1234567890");
        System.out.println("Test Account: Phone: 1234567890, pwd: 123");

        loginPrompt(accountManager);

        CustomerAccount currentAccount =  accountManager.getAccountByPhone(currentPhoneLoggedIn);
        currentAccount.printAccountInfo();

        boolean exit = false;
        int previousOption = 0;

        while (!exit) {
            do {
                // Clear screen before oprn unless the previous oprn is printing the item menu
                if(previousOption != 1){clearScreen();}
                printMenu();
                String inputOption = scanner.nextLine();
                exitProgram(inputOption);
                System.out.println();

                switch (inputOption) {
                    case "1" -> {
                        previousOption = 1;
                        shelf.printAllItems();
                        System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
                        String input_1 = scanner.nextLine();
                        exitProgram(input_1);
                    }

                    case "2" -> {
                        previousOption = 2;
                        boolean stillAdding = true;
                        int loopCounter = 0;
                        String orderID = null;
                        while (stillAdding) {


                            System.out.print("Enter Item ID: ");
                            String itemID = scanner.nextLine();
                            exitProgram(itemID);

                            System.out.print("Enter Quantity: ");
                            String quantity = scanner.nextLine();
                            exitProgram(quantity);

                            if (shelf.getItem(itemID) == null) {
                                System.out.println("Item " + itemID + " does not exist");
                                continue;
                            }

                            int quantityInt = 0;
                            try {
                                quantityInt = Integer.parseInt(quantity);
                            } catch (NumberFormatException e) {
                                System.out.println("Quantity entered is invalid");
                                continue;
                            }
                            if (loopCounter == 0) {
                                orderID = currentAccount.getOrderManager()
                                    .createOrder(currentAccount.getCustomerID(), itemID, quantityInt);
                                loopCounter++;
                            } else {
                                currentAccount.getOrderManager().addItemToOrder(orderID, itemID, quantityInt);
                            }

                            System.out.print("Still Adding? (Y/N): ");
                            String userStillAdding = scanner.nextLine();
                            exitProgram(userStillAdding);
                            if (userStillAdding.toLowerCase().equals("n")) {
                                stillAdding = false;
                            }
                        }

                        System.out.println("Order placed, invoice as below: ");
                        System.out.print(currentAccount.getOrderManager().getMember(orderID).toString());
                        currentAccount.getOrderManager().getMember(orderID).printOrderItems();
                        System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                        String input_2 = scanner.nextLine();
                        exitProgram(input_2);
                    }

                    case "3" -> {
                        previousOption = 3;
                        shelf.printDeal();
                        System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");

                        String input_3 = scanner.nextLine();
                        exitProgram(input_3);
                    }

                    case "4" -> {
                        previousOption = 4;
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

                        currentAccount.getReviewManager().writeReview(reviewInfo[0], reviewInfo[1], rating, reviewInfo[3], Boolean.parseBoolean(reviewInfo[4]));
                        System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                        String input_4 = scanner.nextLine();
                        exitProgram(input_4);
                    }




                    case "5" -> {
                        previousOption = 5;
                        boolean isIncoming = true;
                        // View reviews feature
                        System.out.print("View (1) Incoming reviews or (2) Outgoing reviews? ");
                        String reviewType = scanner.nextLine();
                        exitProgram(reviewType);
                        System.out.println();
                        if (reviewType.equals("1")) {
                            currentAccount.getReviewManager().viewAllReviews(true);
                        } else if (reviewType.equals("2")) {
                            currentAccount.getReviewManager().viewAllReviews(false);
                        } else {
                            System.out.println("Invalid choice for review type.");
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
                            currentAccount.getReviewManager().viewFilteredReviews(isIncoming, filterOption);
                        } else {
                            // View all reviews without filtering
                            currentAccount.getReviewManager().viewAllReviews(isIncoming);
                        }

                        System.out.print("\nPress Enter to return to the main menu...");


                        exitProgram(scanner.nextLine());
                    }
                    //   case "6" -> {
                    case "6" -> {
                        previousOption = 6;
                        exitProgram("exit");
                    }


                    //default case
                    //for invalid input
                    default -> System.out.println("Invalid , please choose again.");
                }

            } while (!exit);

            scanner.close();
        }
    }
}

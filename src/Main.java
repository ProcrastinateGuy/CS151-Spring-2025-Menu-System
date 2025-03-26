
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    private static ItemShelf shelf;
    private static Scanner scanner = new Scanner(System.in);
    private static String currentPhoneLoggedIn = null;

    public static void clearScreen() {
        // Clear screen function
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void exitProgram(String input) {
        // Exit program function
        if (input.toLowerCase().equals("exit")) {
            System.out.println("\nThank you for using the Food Order System. Goodbye!");
            System.exit(0);
        }
    }

    public static void loginPrompt(AccountManager accountManager) {
        // Login prompt function
        System.out.println("*************** Login ***************");
        System.out.println("Need an account? Type R or r to move to the registration menu. Type anything else to proceed with login.");
        String register = scanner.nextLine();
        exitProgram(register);
        if (register.equalsIgnoreCase("R")) {
            registerPrompt(accountManager);
            return;
        }
        System.out.println("Enter your phone number: ");
        currentPhoneLoggedIn = scanner.nextLine();
        exitProgram(currentPhoneLoggedIn);
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
    public static void printMenu () {
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

    public static void registerPrompt(AccountManager accountManager) {
        // Registration prompt function
        System.out.println("*************** Registration ***************");

        System.out.print("Name: ");
        String name = scanner.nextLine();
        exitProgram(name);
        System.out.println();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        exitProgram(phone);
        System.out.println();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        exitProgram(email);
        System.out.println();

        System.out.print("Enter your Shopping Interests: ");
        String interests = scanner.nextLine();
        exitProgram(interests);
        System.out.println();

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        exitProgram(address);
        System.out.println();

        System.out.print("Enter your payment method: ");
        String paymentMethod = scanner.nextLine();
        exitProgram(paymentMethod);
        System.out.println();

        System.out.print("Enter your birthday (YYYY-MM-DD): ");
        String birthday = scanner.nextLine();
        exitProgram(birthday);
        System.out.println();

        System.out.print("Do you want text notifications? (Yes/No): ");
        String textMsg = scanner.nextLine();

        if (textMsg.equalsIgnoreCase("Yes")) {
            textMsg = "true";
        } else if (textMsg.equalsIgnoreCase("No")) {
            textMsg = "false";
        }
        exitProgram(textMsg);
        System.out.println();

        System.out.print("Enter a new password: ");
        String password = scanner.nextLine();
        exitProgram(password);
        System.out.println();

        String customerID = accountManager.generateAccount(name, phone, password);
        accountManager.completeProfile(customerID, email, address, interests, birthday, paymentMethod, Boolean.parseBoolean(textMsg));

        loginPrompt(accountManager);
    }

        public static void main (String[]args){


            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                try{
                shelf = new ItemShelf(".\\src\\itemDB.txt");}
                catch (Exception e){
                    shelf = new ItemShelf(".\\itemDB.txt");
                }
            } else {
                try{
                shelf = new ItemShelf("./src/itemDB.txt");}
                catch (Exception e){
                    shelf = new ItemShelf("./itemDB.txt");
                }
            }

            AccountManager accountManager = new AccountManager();

            loginPrompt(accountManager);
            //this line will be replaced by getting the return from the log in session
            CustomerAccount currentAccount =  accountManager.getAccountByPhone(currentPhoneLoggedIn);

            //ReviewManager reviewManager = new ReviewManager();
            //ReviewManager reviewManager = new ReviewManager();
            boolean exit = false;
            int previousOption = 0;

            while (!exit) {
                do {
                    if(previousOption != 1){clearScreen();} // Clear screen at the beginning of each iteration

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

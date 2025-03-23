
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

    public static void loginPrompt(AccountManager accountManager) {
        // Login prompt function
        System.out.println("*************** Login ***************");
        System.out.println("Need an account? Type R or r to move to the registration menu. Type anything else to proceed with login.");
        String register = scanner.nextLine();
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
            // Proceed to the next step
        } else {
            System.out.println("Login failed. Please check your credentials.");
            loginPrompt(accountManager);
        }
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

        loginPrompt(accountManager);

        while (!exit) {
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


            /*
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println();
                System.out.print("Press Enter to continue or type exit to close the app: ");
                String err = scanner.nextLine();
                exitProgram(err);
                continue;
            }
            System.out.println();
            */
            switch (inputOption) {
                case "1":
                    shelf.printAllItems();
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app: ");
                    String input_1 = scanner.nextLine();
                    exitProgram(input_1);
                    break;
                case "2":
                    System.out.println("Order placement functionality is not implemented yet.");
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                    String input_2 = scanner.nextLine();
                    exitProgram(input_2);
                    break;
                case "3":
                    System.out.println("Deals are not fully implemented yet.");
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                    String input_3 = scanner.nextLine();
                    exitProgram(input_3);
                    break;
                case "4":

                    String userInput;
                    String [] reviewInfo = new String[5];
                    double rating;
                    Runnable[] tasksBeforeinput = {
                        () -> System.out.print("Enter your writer ID: "),
                        () -> System.out.print("Enter target ID (restaurant ID): "),
                        () -> System.out.print("Enter rating (e.g., 4.5): "),
                        () -> System.out.print("Enter your review: "),
                        () -> System.out.print("Is this a verified purchase? (true/false): ")
                    };

                    Consumer<String>[] tasksAfterinput = new Consumer[]{
                            input -> reviewInfo[0] = (String)input,
                            input -> reviewInfo[1] = (String)input,
                            input -> reviewInfo[2] = (String)input,
                            input -> reviewInfo[3] = (String)input,
                            input -> reviewInfo[4] = (String)input
                    };

                    for(int  i=0; i<5; i++){
                        tasksBeforeinput[i].run();
                        userInput = scanner.nextLine();
                        exitProgram(userInput);
                        tasksAfterinput[i].accept(userInput);
                    }

                    //check the input
                    try {
                        rating = Double.parseDouble(reviewInfo[2]);
                    }catch (NumberFormatException e){
                        System.out.println("You have entered an invalid rating, setting to 0.0");
                        rating = 0.0;
                    }

                        reviewManager.writeReview(reviewInfo[0], reviewInfo[1], rating, reviewInfo[3], Boolean.parseBoolean(reviewInfo[4]));
                        System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                        String input_4 = scanner.nextLine();
                        exitProgram(input_4);


                    break;

                case "5":
                    // View reviews feature
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
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                    String input_5 = scanner.nextLine();
                    exitProgram(input_5);
                    break;
                case "6":
                    System.out.println("Thank you for using the Food Order System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid , please choose again.");
                    System.out.print("\nPress Enter to return to the main menu or type exit to close the app:");
                    String fallback = scanner.nextLine();
                    exitProgram(fallback);
                    break;
            }
        }
        scanner.close();
    }
}

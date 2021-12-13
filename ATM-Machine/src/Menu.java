import jdk.jshell.spi.ExecutionControlProvider;
import jdk.swing.interop.SwingInterOpUtils;

import javax.xml.crypto.Data;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    DataBase db;
    Scanner input = new Scanner(System.in);
    Client user;
    Account account;
    int accountId;
    boolean logged = false;

    Menu(DataBase db) {
        this.db = db;
    }

    public void mainMenu() {
        String fName, lName;

        System.out.println("----------USER INFO----------");
        System.out.print("Account Id: ");
        accountId = input.nextInt();
        System.out.print("First Name: ");
        fName = input.next();
        System.out.print("Last Name: ");
        lName = input.next();

        try {
            this.user = db.findClient(this.accountId, fName, lName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            mainMenu();
        }

        boolean running = true;
        while(running) {
            try {

                System.out.println("\n  1: Login" +
                        "\n  2: Create Account" +
                        "\n  3: Reenter User info" +
                        "\n  4: Exit");
                System.out.print("\nChoice: ");
                int choice = input.nextInt();
                
                switch (choice) {
                    case 1 -> {
                        this.logged = this.login(fName, lName);
                        if(this.logged)
                            this.accountOperations();
                    }
                    case 2 -> {
                        // TODO: 12/12/2021  
                    }
                    case 3 -> mainMenu();
                    case 4 -> running = false;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private boolean login(String fName, String lName) {

        System.out.println("----------LOGIN----------");
        System.out.print("PIN: ");
        int pin = input.nextInt();

        try {
            this.account = this.user.loginAccount(accountId, pin);
            System.out.println("Successfully Logged in!");
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("To retry, type 0:" +
                    "\nTo return back, type 1:");
            int choice = input.nextInt();
            if(choice == 1)
                return false;
            return login(fName, lName);
        }
    }

    private void accountOperations() {
        if(!this.logged)
            return;

        System.out.println("----------WELCOME----------");

        while(true) {
            System.out.println("""
                    Operations List:
                    --- 0: Check Balance
                    --- 1: Deposit
                    --- 2: Withdraw
                    --- 3: Deactivate Account
                    --- 4: Remove Account
                    --- 5: Logout""");
            int choice = input.nextInt();

            switch (choice) {
                case 0 -> System.out.println("Your Balance is: " + this.account.getBalance());

                case 1 -> {
                    System.out.print("Enter the amount to deposit: ");
                    // normally the atm detects the amount entered
                    double amount = input.nextDouble();
                    try {
                        account.deposit(amount);
                        successMsg();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                case 2 -> {
                    while(true) {
                        System.out.print("Enter the amount to withdraw: ");
                        double amount = input.nextDouble();
                        try {
                            account.withdraw(amount);
                            successMsg();
                            break;
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                            System.out.println("To retry, type 0:" +
                    "\nTo return back, type 1:");
                            if(input.nextInt() == 1)
                                break;
                        }
                    }
                }

                case 3 -> {
                    account.setActive(false);
                    successMsg();
                }

                case 4 -> {
                    System.out.print("Are You Sure? (y/n) ");
                    if(input.next().equalsIgnoreCase("n"))
                        break;
                    if(account.getBalance() != 0)
                        System.out.println("You should Withdraw all the balance in your account first");
                    else
                        try {
                            this.user.deleteAccount(accountId);
                            successMsg();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                }

                case 5 -> {
                    return;
                }

                default -> System.out.println("Enter a valid choice!");

            }

        }
    }

    private void successMsg() {
        System.out.println("Operation Successfully Ended");
    }
}

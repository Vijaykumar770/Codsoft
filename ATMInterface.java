import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: ₹" + amount);
        } else {
            System.out.println(" Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println(" Insufficient balance!");
        } else if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount!");
        } else {
            balance -= amount;
            System.out.println(" Withdrawal successful: ₹" + amount);
        }
    }

    public void checkBalance() {
        System.out.println(" Current balance: ₹" + balance);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount(5000.00);

        int choice;
        do {
            System.out.println("\n====== ATM MENU ======");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> account.checkBalance();
                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                }
                case 3 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                }
                case 4 -> System.out.println("Thank you for using the ATM!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();

        while (true) {
            System.out.println("\n--- BANK MANAGEMENT SYSTEM ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bankService.createAccount(scanner);
                case 2 -> bankService.deposit(scanner);
                case 3 -> bankService.withdraw(scanner);
                case 4 -> bankService.checkBalance(scanner);
                case 5 -> {
                    System.out.println("Exiting Bank System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Choose between 1 and 5.");
            }
        }
    }
}
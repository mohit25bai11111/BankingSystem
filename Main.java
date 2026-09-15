import java.util.List;
import java.util.Scanner;

public class Main {
    // ANSI Terminal Formatting Palette
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBanner();
            System.out.println(CYAN + "┌────────────────────────────────────────────────────────┐" + RESET);
            System.out.println(CYAN + "│ " + BOLD + "[1]" + RESET + " Open New Account                                   │");
            System.out.println(CYAN + "│ " + BOLD + "[2]" + RESET + " Deposit Funds                                     │");
            System.out.println(CYAN + "│ " + BOLD + "[3]" + RESET + " Withdraw Cash (PIN Required)                      │");
            System.out.println(CYAN + "│ " + BOLD + "[4]" + RESET + " Inter-Account Fund Transfer (PIN Required)        │");
            System.out.println(CYAN + "│ " + BOLD + "[5]" + RESET + " Account Summary & Balance Inquiry (PIN Required)  │");
            System.out.println(CYAN + "│ " + BOLD + "[6]" + RESET + " View Passbook / Audit Trail (PIN Required)        │");
            System.out.println(CYAN + "│ " + BOLD + "[7]" + RESET + " Bank Manager Portal (Admin Mode)                  │");
            System.out.println(CYAN + "│ " + BOLD + "[8]" + RESET + " Exit System                                       │");
            System.out.println(CYAN + "└────────────────────────────────────────────────────────┘" + RESET);
            System.out.print(YELLOW + BOLD + "Select Operation [1-8]: " + RESET);

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println(PURPLE + BOLD + "\n--- ACCOUNT CREATION MODULE ---" + RESET);
                    System.out.print("Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Account Holder Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Account Type (SAVINGS/CHECKING): ");
                    String type = scanner.nextLine();
                    System.out.print("Initial Deposit Amount: ");
                    double bal = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Set 4-Digit Security PIN: ");
                    String pin = scanner.nextLine();

                    if (bankService.createAccount(accNum, name, bal, pin, type)) {
                        System.out.println(GREEN + "✔ Account created successfully with SHA-256 PIN protection!" + RESET);
                    } else {
                        System.out.println(RED + "✖ Error: Account number already exists." + RESET);
                    }
                }
                case 2 -> {
                    System.out.println(PURPLE + BOLD + "\n--- DEPOSIT MODULE ---" + RESET);
                    System.out.print("Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Deposit Amount: ");
                    double amt = scanner.nextDouble();

                    if (bankService.deposit(accNum, amt)) {
                        System.out.println(GREEN + "✔ Deposit processed successfully." + RESET);
                    } else {
                        System.out.println(RED + "✖ Deposit failed. Invalid account or locked status." + RESET);
                    }
                }
                case 3 -> {
                    System.out.println(PURPLE + BOLD + "\n--- WITHDRAWAL MODULE ---" + RESET);
                    System.out.print("Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String pin = scanner.nextLine();
                    System.out.print("Withdrawal Amount: ");
                    double amt = scanner.nextDouble();

                    if (bankService.withdraw(accNum, amt, pin)) {
                        System.out.println(GREEN + "✔ Cash disbursed successfully." + RESET);
                    } else {
                        System.out.println(RED + "✖ Transaction denied! Incorrect PIN, locked account, or insufficient funds." + RESET);
                    }
                }
                case 4 -> {
                    System.out.println(PURPLE + BOLD + "\n--- FUND TRANSFER MODULE ---" + RESET);
                    System.out.print("Sender Account Number: ");
                    String sender = scanner.nextLine();
                    System.out.print("Sender Security PIN: ");
                    String pin = scanner.nextLine();
                    System.out.print("Receiver Account Number: ");
                    String receiver = scanner.nextLine();
                    System.out.print("Transfer Amount: ");
                    double amt = scanner.nextDouble();

                    if (bankService.transfer(sender, receiver, amt, pin)) {
                        System.out.println(GREEN + "✔ Fund transfer completed successfully!" + RESET);
                    } else {
                        System.out.println(RED + "✖ Transfer failed. Verify PIN, receiver status, and available balance." + RESET);
                    }
                }
                case 5 -> {
                    System.out.println(PURPLE + BOLD + "\n--- BALANCE INQUIRY ---" + RESET);
                    System.out.print("Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String pin = scanner.nextLine();

                    if (bankService.authenticate(accNum, pin)) {
                        Account acc = bankService.getAccount(accNum);
                        System.out.println(BLUE + "┌──────────────────────────────────────────────┐" + RESET);
                        System.out.println(BLUE + "│ " + BOLD + "Holder:   " + RESET + String.format("%-34s", acc.getAccountHolder()) + BLUE + "│" + RESET);
                        System.out.println(BLUE + "│ " + BOLD + "Type:     " + RESET + String.format("%-34s", acc.getAccountType()) + BLUE + "│" + RESET);
                        System.out.println(BLUE + "│ " + BOLD + "Balance:  " + RESET + GREEN + String.format("$%-33.2f", acc.getBalance()) + BLUE + "│" + RESET);
                        System.out.println(BLUE + "└──────────────────────────────────────────────┘" + RESET);
                    } else {
                        System.out.println(RED + "✖ Authentication failed! Access denied." + RESET);
                    }
                }
                case 6 -> {
                    System.out.println(PURPLE + BOLD + "\n--- PASSBOOK & AUDIT TRAIL ---" + RESET);
                    System.out.print("Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String pin = scanner.nextLine();

                    if (bankService.authenticate(accNum, pin)) {
                        List<Transaction> txns = bankService.getPassbook(accNum);
                        System.out.println(BOLD + "\nID         | TYPE                | AMOUNT      | TIMESTAMP" + RESET);
                        System.out.println("-------------------------------------------------------------------");
                        for (Transaction t : txns) {
                            System.out.printf("%-10s | %-19s | $%-10.2f | %s\n", t.getTransactionId(), t.getType(), t.getAmount(), t.getTimestamp());
                        }
                    } else {
                        System.out.println(RED + "✖ Authentication failed! Access denied." + RESET);
                    }
                }
                case 7 -> {
                    System.out.println(PURPLE + BOLD + "\n--- ADMIN MANAGER PORTAL ---" + RESET);
                    System.out.print("Enter Admin Access Key (Default: admin123): ");
                    String key = scanner.nextLine();

                    if ("admin123".equals(key)) {
                        System.out.println(GREEN + "✔ Admin authentication granted." + RESET);
                        System.out.println("[1] View Total Bank Liquidity\n[2] Unlock Account");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (adminChoice == 1) {
                            double total = bankService.getAllAccounts().values().stream().mapToDouble(Account::getBalance).sum();
                            System.out.println(BOLD + "Total Liquidity Managed: " + GREEN + "$" + total + RESET);
                        } else if (adminChoice == 2) {
                            System.out.print("Enter Account Number to Unlock: ");
                            String target = scanner.nextLine();
                            if (bankService.unlockAccount(target)) {
                                System.out.println(GREEN + "✔ Account " + target + " is unlocked." + RESET);
                            } else {
                                System.out.println(RED + "✖ Unlock failed. Account not found or not locked." + RESET);
                            }
                        }
                    } else {
                        System.out.println(RED + "✖ Invalid admin credentials!" + RESET);
                    }
                }
                case 8 -> {
                    System.out.println(YELLOW + "Persisting state and terminating... Goodbye!" + RESET);
                    return;
                }
                default -> System.out.println(RED + "Invalid choice! Enter 1-8." + RESET);
            }
            System.out.println();
        }
    }

    private static void printBanner() {
        System.out.println(CYAN + BOLD);
        System.out.println("==========================================================");
        System.out.println("          🏦 ADVANCED SECURE BANKING SYSTEM CLI           ");
        System.out.println("==========================================================" + RESET);
    }
}
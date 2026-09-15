import java.io.*;
import java.util.*;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();
    private final String FILE_PATH = "accounts.csv";

    public BankService() {
        loadAccountsFromFile();
    }

    public void createAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();
        if (accounts.containsKey(accNum)) {
            System.out.println("Account already exists!");
            return;
        }
        System.out.print("Enter Holder Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        Account acc = new Account(accNum, name, initialBalance);
        accounts.put(accNum, acc);
        saveAccountsToFile();
        System.out.println("Account created successfully!");
    }

    public void deposit(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter Amount to Deposit: ");
            double amt = scanner.nextDouble();
            scanner.nextLine();
            acc.deposit(amt);
            saveAccountsToFile();
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter Amount to Withdraw: ");
            double amt = scanner.nextDouble();
            scanner.nextLine();
            if (acc.withdraw(amt)) {
                saveAccountsToFile();
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public void checkBalance(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            System.out.println("Holder: " + acc.getAccountHolder() + " | Balance: $" + acc.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private void saveAccountsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Account acc : accounts.values()) {
                writer.write(acc.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    private void loadAccountsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account acc = Account.fromCSV(line);
                if (acc != null) {
                    accounts.put(acc.getAccountNumber(), acc);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}
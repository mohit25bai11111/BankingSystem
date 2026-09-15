import java.io.*;
import java.util.*;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private final String ACCOUNT_FILE = "accounts.csv";
    private final String TXN_FILE = "transactions.csv";

    public BankService() {
        loadAccounts();
        loadTransactions();
    }

    public boolean createAccount(String accNum, String name, double initialBalance, String pin, String type) {
        if (accounts.containsKey(accNum)) return false;
        Account acc = new Account(accNum, name, initialBalance, pin, type);
        accounts.put(accNum, acc);
        recordTransaction(accNum, "ACCOUNT_CREATED", initialBalance);
        saveAccounts();
        return true;
    }

    public Account getAccount(String accNum) { return accounts.get(accNum); }

    public boolean authenticate(String accNum, String pin) {
        Account acc = accounts.get(accNum);
        if (acc == null || acc.isLocked()) return false;
        boolean authenticated = acc.validatePin(pin);
        saveAccounts(); // Save state update for failed attempts / auto-lock
        return authenticated;
    }

    public boolean deposit(String accNum, double amount) {
        Account acc = accounts.get(accNum);
        if (acc != null && !acc.isLocked() && amount > 0) {
            acc.deposit(amount);
            recordTransaction(accNum, "DEPOSIT", amount);
            saveAccounts();
            return true;
        }
        return false;
    }

    public boolean withdraw(String accNum, double amount, String pin) {
        Account acc = accounts.get(accNum);
        if (acc != null && authenticate(accNum, pin)) {
            if (acc.withdraw(amount)) {
                recordTransaction(accNum, "WITHDRAWAL", amount);
                saveAccounts();
                return true;
            }
        }
        return false;
    }

    public boolean transfer(String senderAccNum, String receiverAccNum, double amount, String pin) {
        Account sender = accounts.get(senderAccNum);
        Account receiver = accounts.get(receiverAccNum);

        if (sender != null && receiver != null && !receiver.isLocked() && authenticate(senderAccNum, pin)) {
            if (sender.withdraw(amount)) {
                receiver.deposit(amount);
                recordTransaction(senderAccNum, "TRANSFER_SENT (" + receiverAccNum + ")", amount);
                recordTransaction(receiverAccNum, "TRANSFER_RECEIVED (" + senderAccNum + ")", amount);
                saveAccounts();
                return true;
            }
        }
        return false;
    }

    public List<Transaction> getPassbook(String accNum) {
        List<Transaction> history = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAccountNumber().equals(accNum)) {
                history.add(t);
            }
        }
        return history;
    }

    public boolean unlockAccount(String accNum) {
        Account acc = accounts.get(accNum);
        if (acc != null && acc.isLocked()) {
            acc.setLocked(false);
            saveAccounts();
            return true;
        }
        return false;
    }

    public Map<String, Account> getAllAccounts() { return accounts; }

    private void recordTransaction(String accNum, String type, double amount) {
        Transaction txn = new Transaction(accNum, type, amount);
        transactions.add(txn);
        saveTransactions();
    }

    private void loadAccounts() {
        File file = new File(ACCOUNT_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Account acc = Account.fromCSV(line);
                if (acc != null) accounts.put(acc.getAccountNumber(), acc);
            }
        } catch (IOException ignored) {}
    }

    public void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account acc : accounts.values()) {
                bw.write(acc.toCSV());
                bw.newLine();
            }
        } catch (IOException ignored) {}
    }

    private void loadTransactions() {
        File file = new File(TXN_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Transaction txn = Transaction.fromCSV(line);
                if (txn != null) transactions.add(txn);
            }
        } catch (IOException ignored) {}
    }

    private void saveTransactions() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TXN_FILE))) {
            for (Transaction txn : transactions) {
                bw.write(txn.toCSV());
                bw.newLine();
            }
        } catch (IOException ignored) {}
    }
}
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String pinHash;
    private int failedAttempts;
    private boolean isLocked;
    private String accountType; // SAVINGS or CHECKING

    // Constructor for creating new accounts
    public Account(String accountNumber, String accountHolder, double balance, String rawPin, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.pinHash = hashPin(rawPin);
        this.failedAttempts = 0;
        this.isLocked = false;
        this.accountType = accountType.toUpperCase();
    }

    // Reconstruct account from storage
    private Account(String accountNumber, String accountHolder, double balance, String pinHash, int failedAttempts, boolean isLocked, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.pinHash = pinHash;
        this.failedAttempts = failedAttempts;
        this.isLocked = isLocked;
        this.accountType = accountType;
    }

    // SHA-256 Cryptographic Hash Engine
    public static String hashPin(String pin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pin.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm missing", e);
        }
    }

    // PIN Authentication with Account Auto-Lock (3 max attempts)
    public boolean validatePin(String inputPin) {
        if (isLocked) return false;

        if (hashPin(inputPin).equals(this.pinHash)) {
            this.failedAttempts = 0;
            return true;
        } else {
            this.failedAttempts++;
            if (this.failedAttempts >= 3) {
                this.isLocked = true;
            }
            return false;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public String getPinHash() { return pinHash; }
    public int getFailedAttempts() { return failedAttempts; }
    public boolean isLocked() { return isLocked; }
    public String getAccountType() { return accountType; }

    public void setLocked(boolean locked) {
        this.isLocked = locked;
        if (!locked) this.failedAttempts = 0;
    }

    public String toCSV() {
        return String.join(",", accountNumber, accountHolder, String.valueOf(balance), pinHash, String.valueOf(failedAttempts), String.valueOf(isLocked), accountType);
    }

    public static Account fromCSV(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) return null;
        String[] parts = csvLine.split(",");
        if (parts.length < 7) return null;
        try {
            return new Account(
                    parts[0].trim(), parts[1].trim(), Double.parseDouble(parts[2].trim()),
                    parts[3].trim(), Integer.parseInt(parts[4].trim()),
                    Boolean.parseBoolean(parts[5].trim()), parts[6].trim()
            );
        } catch (Exception e) {
            return null;
        }
    }
}
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER_SENT, TRANSFER_RECEIVED
    private double amount;
    private String timestamp;

    public Transaction(String accountNumber, String type, double amount) {
        this.transactionId = "TXN" + System.currentTimeMillis() % 100000;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private Transaction(String transactionId, String accountNumber, String type, double amount, String timestamp) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() { return transactionId; }
    public String getAccountNumber() { return accountNumber; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getTimestamp() { return timestamp; }

    public String toCSV() {
        return String.join(",", transactionId, accountNumber, type, String.valueOf(amount), timestamp);
    }

    public static Transaction fromCSV(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) return null;
        String[] parts = csvLine.split(",");
        if (parts.length < 5) return null;
        try {
            return new Transaction(parts[0].trim(), parts[1].trim(), parts[2].trim(), Double.parseDouble(parts[3].trim()), parts[4].trim());
        } catch (Exception e) {
            return null;
        }
    }
}
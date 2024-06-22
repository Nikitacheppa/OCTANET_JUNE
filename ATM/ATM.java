import java.util.HashMap;
import java.util.Map;

public class ATM {
    private Map<String, User> users;
    private User currentUser;

    public ATM() {
        users = new HashMap<>();
        // Example users
        users.put("nikita", new User("user1", "1234", 10000));
        users.put("meenakshi", new User("user2", "5678", 20000));
    }

    public boolean authenticate(String userID, String pin) {
        User user = users.get(userID);
        if (user != null && user.getPin().equals(pin)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    public void deposit(double amount) {
        currentUser.setBalance(currentUser.getBalance() + amount);
        currentUser.addTransaction("Deposited: $" + amount);
        System.out.println("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.addTransaction("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(String targetUserID, double amount) {
        User targetUser = users.get(targetUserID);
        if (targetUser != null && currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            targetUser.setBalance(targetUser.getBalance() + amount);
            currentUser.addTransaction("Transferred: $" + amount + " to " + targetUserID);
            targetUser.addTransaction("Received: $" + amount + " from " + currentUser.getUserID());
            System.out.println("Transferred: $" + amount + " to " + targetUserID);
        } else {
            System.out.println("Transfer failed. Check target user ID and balance.");
        }
    }
}

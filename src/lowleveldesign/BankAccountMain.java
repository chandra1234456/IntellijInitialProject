package lowleveldesign;

public class BankAccountMain {
    public static void main(String[] args) {

 BankAccount obj = new BankAccount("ACC-123", "John Doe");
  boolean param_1 = obj.deposit(1000);
  boolean param_2 = obj.withdraw(5000);
  double param_3 = obj.getBalance();
  String param_4 = obj.getSummary();
  System.out.println(param_1);
  System.out.println(param_2);
  System.out.println(param_3);
  System.out.println(param_4);
    }
}
class BankAccount {
    private final String accountNumber;
    private final String ownerName;
    private Double balance;


    public BankAccount(String accountNumber, String ownerName) {
         this.accountNumber = accountNumber;
         this.ownerName = ownerName;
         this.balance = 0.00;
    }

    public boolean deposit(double amount) {
       if (amount>0){
           balance+=amount;
           return true;
       }
       return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
      return balance;
    }

    public String getSummary() {
        return ownerName + " (" + accountNumber + ") | Balance: $"
                + String.format("%.2f", balance);
    }
}

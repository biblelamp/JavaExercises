package lesson27.homework;

public class BankAccount implements PaymentSystem {
    private double balance;

    @Override
    public void transferMoney(double debet, double credit, PaymentSystem account) {
        if (debet > 0) {
            balance -= debet;
            account.depositMoney(debet);
        } else if (credit > 0) {
            balance += credit;
            account.withdrawMoney(credit);
        }
    }

    @Override
    public void depositMoney(double money) {
        balance += money;
    }

   @Override
    public void withdrawMoney(double money) {
        balance -= money;
    }

    @Override
    public double checkBalance() {
        return balance;
    }
}

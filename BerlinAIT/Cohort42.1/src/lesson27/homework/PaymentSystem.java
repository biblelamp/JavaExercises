package lesson27.homework;

public interface PaymentSystem {
    void transferMoney(double debet, double credit, PaymentSystem system);
    void depositMoney(double money);
    void withdrawMoney(double money);
    double checkBalance();
}

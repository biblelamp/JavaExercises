class BankAccount {
    private volatile int balance = 100; // volatile is neccesary

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance = balance - amount;
    }
}
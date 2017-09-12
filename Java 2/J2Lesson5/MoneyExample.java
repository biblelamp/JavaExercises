class MoneyExample {

    public static void main(String[] args) {
        BankAccount ba = new BankAccount(100);
        System.out.println(ba);
        Thread tx1 = new Thread(() -> {
            ba.getMoney(50, "John");
        });
        Thread tx2 = new Thread(() -> {
            ba.getMoney(50, "Smith");
        });
        Thread tx3 = new Thread(() -> {
            ba.getMoney(50, "Mike");
        });
        Thread tx4 = new Thread(() -> {
            ba.getMoney(50, "Joseph");
        });
        tx1.start();
        tx2.start();
        tx3.start();
        tx4.start();
        try {
            tx1.join();
            tx2.join();
            tx3.join();
            tx4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ba);
    }
}

class BankAccount {
    private volatile int money;

    BankAccount(int money) {
        this.money = money;
    }

    boolean isEnough(int amount) {
        return money >= amount;
    }

    synchronized void getMoney(int amount, String user) { // method synchronized or not
        if (isEnough(amount)) { // have money?
            try {
                Thread.sleep(400); // time  for check
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            money -= amount; // give money
            System.out.println(user + " gets: " + amount + 
                "/account balance: " + money);
        } else {
            System.out.println("Sorry, " + user + ", not enough money.");
        }
    }

    @Override
    public String toString() {
        return "Account Balance: " + money;
    }
}
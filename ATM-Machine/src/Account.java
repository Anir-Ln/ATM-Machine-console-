public class Account {
    private int accountId;
    private int ownerId;
    private int pinNumber;
    private double balance;
    static int numCreatedAccounts = 0;
    private boolean active;
    private boolean removed;


    Account(int ownerId, int pinNumber, double balance) {
        this.accountId = numCreatedAccounts++;
        this.ownerId = ownerId;
        this.pinNumber = pinNumber;
        this.balance = balance;
        this.removed = false;
        this.active = true;
    }

    public boolean checkPin(int pin) {
        return pin == this.pinNumber;
    }

    public void withdraw(double amount) throws Exception {
        if (amount <= this.balance)
            this.balance -= amount;
            // TODO: 12/12/2021 withdraw machine call
        else
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "insufficient funds";
                }
            };
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountId() {
        return accountId;
    }
    public int getOwnerId() {
        return ownerId;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public boolean isRemoved() {
        return this.removed;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}

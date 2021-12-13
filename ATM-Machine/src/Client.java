import java.util.ArrayList;

public class Client {
    private int clientId;
    private String fName;
    private String lName;
    private ArrayList<Account> accounts;
    static int numClients = 0;

    Client(String fName, String lName) {
        this.clientId = numClients++;
        this.fName = fName;
        this.lName = lName;
        accounts = new ArrayList<>();
    }


    public void createNewAccount(double amount, int pinNumber) {
        this.accounts.add(new Account(clientId, pinNumber, amount));
    }

    public void deleteAccount(int accountId) throws Exception{
        if(accountId >= 0 && accountId < accounts.size() && !this.accounts.get(accountId).isRemoved())
            this.accounts.get(accountId).setRemoved(true);
        else
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "This account doesn't exist (or maybe already removed)";
                }
            };
    }

    public Account loginAccount(int accountId, int pin) throws Exception{
        boolean correct = accountId >= 0 && accountId < this.accounts.size() && !this.accounts.get(accountId).isRemoved() && this.accounts.get(accountId).checkPin(pin);

        if(!correct)
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "Uncorrect Credentials";
                }
            };
        return this.accounts.get(accountId);
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", number of accounts=" + accounts.size() +
                '}';
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }
    public String getlName() {
        return lName;
    }
}

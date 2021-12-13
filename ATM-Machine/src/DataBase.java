import java.util.ArrayList;

public class DataBase {
    ArrayList<Client> clients;

    DataBase() {
        clients = new ArrayList<>();
    }

    public Client findClient(int clientId, String fName, String lName) throws Exception{
        if(clientId >= 0 && clientId < clients.size() && clients.get(clientId).getfName().equals(fName) && clients.get(clientId).getlName().equals(lName))
            return clients.get(clientId);
        else
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "Client Not Found";
                }
            };
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "clients=" + clients +
                '}';
    }
}

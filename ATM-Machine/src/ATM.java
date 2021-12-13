public class ATM {
    public static void main(String[] args) {
        DataBase db = new DataBase();

        Client c1 = new Client("Rick", "Grayms");
        c1.createNewAccount(500, 2001);
        db.addClient(c1);
//        System.out.println(c1);
//        System.out.println(db);
        Menu menu = new Menu(db);
        menu.mainMenu();
    }
}

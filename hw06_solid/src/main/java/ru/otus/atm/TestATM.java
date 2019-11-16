package ru.otus.atm;

public class TestATM {
    public static void main(String[] args) {
        Nominal[] banknotes = { new Hundred(), new Hundred(), new Ten(), new Ten(), new Fifty(), new Ten(), new Thousand(), new Thousand()};
        ATM atm  = new ATM();
        atm.insertMoney(banknotes);
        System.out.println(atm.totalMoneyAtm());
        atm.getMoney(2220);
        atm.getMoney(50);
        atm.getAllMoney();

    }
}

package ru.otus.atm;

public class TestATM {
    public static void main(String[] args) {
        Nominals[] newsum = {Nominals.TEN, Nominals.HUNDRED, Nominals.FIFTY, Nominals.THOUSAND, Nominals.THOUSAND, Nominals.HUNDRED};
        ATM atm = new ATM();
        atm.insertMoney(newsum);
        System.out.println(atm.totalMoneyAtm());
        atm.printMoney(30);
        System.out.println("----------------");
        atm.printMoney(60);
        System.out.println("----------------");
        atm.printMoney(200);
        System.out.println("----------------");
        atm.printMoney(1050);
        System.out.println("----------------");
        atm.printMoney(111);
        System.out.println("----------------");
        atm.printAllMoney();
        atm.printAllMoney();
    }
}

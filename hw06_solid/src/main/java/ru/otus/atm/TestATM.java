package ru.otus.atm;

import java.util.Arrays;
import java.util.List;

public class TestATM {
    public static void main(String[] args) {
        Nominals[] newsum = {Nominals.TEN, Nominals.HUNDRED, Nominals.FIFTY, Nominals.THOUSAND, Nominals.THOUSAND, Nominals.HUNDRED};
        ATM atm = new ATM();
        atm.insertMoney(newsum);
        System.out.println(atm.totalMoneyAtm());
        atm.getMoney(30);
        System.out.println("----------------");
        atm.getMoney(60);
        System.out.println("----------------");
        atm.getMoney(200);
        System.out.println("----------------");
        atm.getMoney(1050);
        System.out.println("----------------");
        atm.getMoney(111);
        System.out.println("----------------");
        atm.printAllMoney();
        atm.printAllMoney();
    }
}

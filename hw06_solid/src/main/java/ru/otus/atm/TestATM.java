package ru.otus.atm;

import java.util.Arrays;

public class TestATM {
    public static void main(String[] args) {
        Nominal[] banknotes = {new Hundred(), new Hundred(), new Ten(), new Ten(), new Fifty(), new Ten(), new Thousand(), new Thousand()};
        Nominal[] bunchOfTens = new Ten[100];
        Arrays.fill(bunchOfTens, new Ten());
        ATM atm = new ATM();
        atm.insertMoney(banknotes);
        atm.insertMoney(bunchOfTens);
        System.out.println(atm.totalMoneyAtm());
        atm.getMoney(220);
        System.out.println("----------------");
        atm.getMoney(60);
        System.out.println("----------------");
        atm.getMoney(200);
        System.out.println("----------------");
        atm.getMoney(1050);
        System.out.println("----------------");
        atm.getMoney(111);
        System.out.println("----------------");
        atm.getAllMoney();
        atm.getAllMoney();
    }
}

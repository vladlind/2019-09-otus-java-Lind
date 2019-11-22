package ru.otus.atm;

public class TestATM {
    public static void main(String[] args) {
        ATM atm1 = new ATM(new Cells());
        ATM atm2 = new ATM(new Cells());
        ATMDept atmDept = new ATMDept();
        atmDept.addATM(atm1);
        atmDept.addATM(atm2);
        atmDept.getATM(0).insertMoney(10, 30);
        atmDept.getATM(0).insertMoney(50, 40);
        atmDept.getATM(0).insertMoney(100, 20);
        atmDept.getATM(0).insertMoney(1000, 30);
        System.out.println(atmDept.getATM(0).cells.cellsmap);
        System.out.println(atmDept.getATM(1).cells.cellsmap);
        atm1.getMoney(50450);
        System.out.println(atm1.totalMoneyAtm());
        atm1.printAllMoney();
    }
}

package ru.otus.atm;

public class TestATM {
    public static void main(String[] args) {
        ATMDept atmDept = new ATMDept();
        atmDept.createATM();
        atmDept.createATM();
        atmDept.getATM(0).insertMoney(10, 30);
        atmDept.getATM(0).insertMoney(50, 40);
        atmDept.getATM(0).insertMoney(100, 20);
        atmDept.getATM(0).insertMoney(1000, 30);
        atmDept.getATM(1).insertMoney(10, 30);
        atmDept.getATM(1).insertMoney(50, 40);
        atmDept.getATM(1).insertMoney(100, 20);
        atmDept.getATM(1).insertMoney(1000, 30);
        System.out.println(atmDept.getATM(0).cells.cellsmap);
        System.out.println(atmDept.getATM(1).cells.cellsmap);
        atmDept.getATM(0).getMoney(50450);
        System.out.println(atmDept.getATM(0).totalMoneyAtm());
        atmDept.getATM(0).printAllMoney();
    }
}

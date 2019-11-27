package ru.otus.atm;

public class TestATM {
    public static void main(String[] args) {
        ATMDept atmDept = new ATMDept();
        atmDept.createATM("LongLake", 100);
        atmDept.createATM("Petrogradskiy", 1000);
        atmDept.createATM("Vasilyevskiy", 5000);
        atmDept.getATM(0).insertMoney(10, 30);
        atmDept.getATM(0).insertMoney(50, 40);
        atmDept.getATM(0).insertMoney(100, 20);
        atmDept.getATM(0).insertMoney(1000, 30);
        atmDept.getATM(1).insertMoney(10, 30);
        atmDept.getATM(1).insertMoney(50, 40);
        atmDept.getATM(1).insertMoney(100, 20);
        atmDept.getATM(1).insertMoney(1000, 30);
        atmDept.getATM(2).insertMoney(10, 30);
        atmDept.getATM(2).insertMoney(50, 40);
        atmDept.getATM(2).insertMoney(100, 20);
        atmDept.getATM(2).insertMoney(1000, 30);
        System.out.println("-------------");
        atmDept.getATM(0).printMoney(550450);
        System.out.println("-------------");
        atmDept.getATM(0).printMoney(2250);
        System.out.println("-------------");
        atmDept.getATM(1).printMoney(3370);

        System.out.println("-------------");
        atmDept.sendCommand("totalmoney");
        System.out.println("-------------");
        atmDept.sendCommand("getatmnames");
        System.out.println("-------------");
        atmDept.sendCommand("resetatms");
        System.out.println("-------------");
        atmDept.sendCommand("totalmoney");
        System.out.println("-------------");
        atmDept.sendCommand("getallmoney");
        System.out.println("-------------");
        atmDept.sendCommand("Hi");
    }
}

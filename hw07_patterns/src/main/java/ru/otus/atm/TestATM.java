package ru.otus.atm;

import ru.otus.atm.commands.*;

public class TestATM {
    public static void main(String[] args) {
        ATMDept atmDept = new ATMDept();
        atmDept.createATM("LongLake", 100);
        atmDept.createATM("Petrogradskiy", 1000);
        atmDept.createATM("Vasilyevskiy", 5000);
        System.out.println("-------------");
        atmDept.getATM(0).notify(new InsertMoney(10, 30));
        atmDept.getATM(0).notify(new InsertMoney(50, 40));
        atmDept.getATM(0).notify(new InsertMoney(100, 20));
        atmDept.getATM(0).notify(new InsertMoney(1000, 30));
        atmDept.getATM(1).notify(new InsertMoney(10, 30));
        atmDept.getATM(1).notify(new InsertMoney(50, 40));
        atmDept.getATM(1).notify(new InsertMoney(100, 20));
        atmDept.getATM(1).notify(new InsertMoney(1000, 30));
        atmDept.getATM(2).notify(new InsertMoney(10, 30));
        atmDept.getATM(2).notify(new InsertMoney(50, 40));
        atmDept.getATM(2).notify(new InsertMoney(100, 20));
        atmDept.getATM(2).notify(new InsertMoney(1000, 30));
        System.out.println("-------------");
        atmDept.getATM(0).notify(new PrintMoney(500));
        System.out.println("-------------");
        atmDept.getATM(0).notify(new PrintMoney(865900));
        System.out.println("-------------");
        atmDept.getATM(1).notify(new PrintMoney(77110));
        System.out.println("-------------");
        atmDept.getATM(2).notify(new PrintMoney(775180));
        System.out.println("-------------");
        atmDept.bulkDoCommand(new PrintAllMoney());
        System.out.println("-------------");
        //creating command object and storing command execution result in a object field.
        ResetATM resetatm = new ResetATM();
        atmDept.getATM(0).notify(resetatm);
        System.out.println(resetatm.getReturncells().getCellsmap());
        System.out.println("-------------");
        atmDept.bulkDoCommand(new ResetATM());
        System.out.println("-------------");
        atmDept.bulkDoCommand(new GetATMname());

    }
}

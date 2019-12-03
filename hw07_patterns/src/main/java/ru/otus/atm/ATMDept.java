package ru.otus.atm;

import ru.otus.atm.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class ATMDept {
    private final List<ATM> atms = new ArrayList<>();

    void createATM(String atmname, int nominalscount) {
        ATM atm = new ATM(nominalscount, atmname);
        atms.add(atm);
        System.out.println("ATM " + atmname + " created with " + nominalscount + " banknotes in each cell");
    }

    ATM getATM(int index) {
        return atms.get(index);
    }

    void removeATM(ATM atm) {
        atms.remove(atm);
    }

    void bulkDoCommand (Command command){
        atms.forEach(atm -> atm.notify(command));
    }
}

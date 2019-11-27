package ru.otus.atm;

import java.util.ArrayList;
import java.util.List;

public class ATMDept implements Command {
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

    @Override
    public void sendCommand(String command) {
        atms.forEach(atm -> atm.notify(command));
    }
}

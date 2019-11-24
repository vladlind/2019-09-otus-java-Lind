package ru.otus.atm;

import java.util.ArrayList;
import java.util.List;

public class ATMDept {
    private final List<ATM> atms = new ArrayList<>();

    private final List<ATM> initialatms = new ArrayList<>();


    void createATM(String atmname, int nominalscount) {
        ATM atm = new ATM(new Cells(nominalscount), atmname);
        atms.add(atm);
        initialatms.add(atm.clone());
        System.out.println("ATM " + atmname + " created with " + nominalscount + " banknotes in each cell");
    }

    ATM getATM(int index) {
        return atms.get(index);
    }

    ATM getinitialATM(int index) {
        return initialatms.get(index);
    }

    void resetATM(int index) {
        System.out.println("resetting ATM: " + index);
        atms.set(index, getinitialATM(index));
    }

    void printAllATMsMoney() {
        atms.stream().forEach(atm->atm.printAllMoney());
        System.out.println("gathered money from all atms");
    }
}

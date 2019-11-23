package ru.otus.atm;

import java.util.ArrayList;
import java.util.List;

public class ATMDept {
    private final List<ATM> atms = new ArrayList<>();

    void createATM() {
        ATM atm  = new ATM(new Cells());
        atms.add(atm);
    }

    ATM getATM(int index) {
        return atms.get(index);
    }
}

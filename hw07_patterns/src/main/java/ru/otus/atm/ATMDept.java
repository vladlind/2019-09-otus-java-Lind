package ru.otus.atm;

import java.util.ArrayList;
import java.util.List;

class ATMDept {
    private final List<ATM> atms = new ArrayList<>();

    void addATM(ATM atm) {
        atms.add(atm);
    }
    ATM getATM(int index) {
        return atms.get(index);
    }
}

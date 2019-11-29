package ru.otus.atm.commands;

import ru.otus.atm.ATM;

public class ResetATM implements Command {

    @Override
    public void doCommand(ATM atm) {
        atm.cells = atm.initialcells;
        System.out.println("ATM "+atm.getAtmname()+" Cells reset to initial count: "+atm.cells.nominalscount);
    }
}

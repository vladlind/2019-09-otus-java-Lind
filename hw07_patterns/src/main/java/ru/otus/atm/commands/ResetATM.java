package ru.otus.atm.commands;

import javafx.scene.control.Cell;
import ru.otus.atm.ATM;
import ru.otus.atm.Cells;

public class ResetATM implements Command {

    private Cells returncells;

    @Override
    public void doCommand(ATM atm) {
        atm.cells = atm.initialcells;
        System.out.println("ATM "+atm.getAtmname()+" Cells reset to initial count: "+atm.cells.nominalscount);
        this.returncells = atm.initialcells;
    }

    public Cells getReturncells() {
        return returncells;
    }
}

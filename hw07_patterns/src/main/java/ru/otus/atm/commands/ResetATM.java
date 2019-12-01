package ru.otus.atm.commands;

import javafx.scene.control.Cell;
import ru.otus.atm.ATM;
import ru.otus.atm.Cells;

import java.util.EnumMap;

public class ResetATM implements Command {

    private EnumMap initialcellsmap;

    @Override
    public void doCommand(ATM atm) {
        atm.cells = atm.initialcells;
        System.out.println("ATM "+atm.getAtmname()+" Cells reset to initial count: "+atm.cells.nominalscount);
        this.initialcellsmap = atm.initialcells.getCellsmap();
    }

    public EnumMap getReturncellsmap() {
        return initialcellsmap;
    }
}

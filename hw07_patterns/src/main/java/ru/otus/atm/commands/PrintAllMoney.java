package ru.otus.atm.commands;

import ru.otus.atm.ATM;
import ru.otus.atm.Cells;

public class PrintAllMoney implements Command {

    @Override
    public void doCommand(ATM atm) {
        if (totalMoneyAtm(atm) != 0) {
            for (Cells.Nominals e : Cells.Nominals.values()) {
                atm.cells.cellsmap.put(e, 0);
            }
            System.out.println("All money is retrieved: " + atm.cells.cellsmap);
        } else {
            System.out.println("No money!");
        }
    }
    private int totalMoneyAtm(ATM atm) {
        int totalsum = 0;
        for (Cells.Nominals e : Cells.Nominals.values()) {
            totalsum = totalsum + e.getLabel() * (int) atm.cells.cellsmap.get(e);
        }
        System.out.println("Money in "+atm.getAtmname()+": "+totalsum);
        return totalsum;
    }
}

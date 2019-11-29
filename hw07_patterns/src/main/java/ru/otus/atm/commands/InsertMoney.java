package ru.otus.atm.commands;

import ru.otus.atm.ATM;
import ru.otus.atm.Cells;

public class InsertMoney implements Command {

    int nominalcount;

    int nominalvalue;

    public InsertMoney(int nominalvalue, int nominalcount) {
        this.nominalcount = nominalcount;
        this.nominalvalue = nominalvalue;
    }

    @Override
    public void doCommand(ATM atm) {
        Cells.Nominals nominal = Cells.Nominals.getEnumByInt(nominalvalue);
        if (nominal != null) {
            atm.cells.cellsmap.put(nominal, atm.cells.nominalscount + nominalcount);
            System.out.println("Inserted: "+nominalcount+" banknotes of "+nominalvalue+" in ATM: "+atm.getAtmname());
        }
    }
}

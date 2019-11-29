package ru.otus.atm;

import ru.otus.atm.commands.Command;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ATM implements Listener {

    public Cells cells;

    public final Cells initialcells;

    private String atmname;

    public String getAtmname() {
        return atmname;
    }

    @Override
    public ATM clone() {
        return new ATM(cells.nominalscount, atmname);
    }

    ATM(int nominalscount, String atmname) {
        this.cells = new Cells(nominalscount);
        this.initialcells = new Cells(nominalscount);
        this.atmname = atmname;
    }

    @Override
    public void notify(Command command) {
        command.doCommand(this);
    }
}

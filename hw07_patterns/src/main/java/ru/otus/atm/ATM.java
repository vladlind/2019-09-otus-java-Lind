package ru.otus.atm;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

class ATM implements Listener {

    Cells cells;

    final Cells initialcells;

    private String atmname;

    private String getAtmname() {
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

    void insertMoney(Integer nominalvalue, Integer nominalcount) {
        Cells.Nominals nominal = Cells.Nominals.getEnumByInt(nominalvalue);
        if (nominal != null) {
            cells.cellsmap.put(nominal, cells.nominalscount + nominalcount);
            System.out.println("Inserted: "+nominalcount+" banknotes of "+nominalvalue);
        }
    }

    void printMoney(Integer sum) {
        sum = moneyGetHelper(sum, 1000, cells.cellsmap);
        sum = moneyGetHelper(sum, 100, cells.cellsmap);
        sum = moneyGetHelper(sum, 50, cells.cellsmap);
        sum = moneyGetHelper(sum, 10, cells.cellsmap);
    }

    private void printAllMoney() {
        if (totalMoneyAtm() != 0) {
            for (Cells.Nominals e : Cells.Nominals.values()) {
                cells.cellsmap.put(e, 0);
            }
            System.out.println("All money is retrieved: "+cells.cellsmap);
        } else {
            System.out.println("No money!");
        }
    }

    private void resetAtm() {
        this.cells = this.initialcells;
        System.out.println("ATM "+this.getAtmname()+" Cells reset to initial count: "+this.cells.nominalscount);
    }

    private int totalMoneyAtm() {
        int totalsum = 0;
        for (Cells.Nominals e : Cells.Nominals.values()) {
            totalsum = totalsum + e.getLabel() * (int) cells.cellsmap.get(e);
        }
        System.out.println("Money in "+getAtmname()+": "+totalsum);
        return totalsum;
    }

    private int moneyGetHelper(int sum, int banknotevalue, EnumMap<Cells.Nominals, Integer> cellmap) {
        if (sum / banknotevalue >= 1) {
            int count = sum / banknotevalue;
            Cells.Nominals nominal = Cells.Nominals.getEnumByInt(banknotevalue);
            if (cellmap.get(nominal) >= count) {
                sum = sum - banknotevalue * count;
                cellmap.put(nominal, cellmap.get(nominal) - count);
                System.out.println("Take " + Cells.Nominals.getEnumByInt(banknotevalue) + "'s: " + count);
            } else {
                System.out.println("Not enough " + banknotevalue + " banknotes in ATM!");
            }
        }
        return sum;
    }

    @Override
    public void notify(String command) {
        switch (command) {
            case "totalmoney":
                this.totalMoneyAtm();
                break;
            case "getallmoney":
                this.printAllMoney();
                break;
            case "getatmnames":
                final String atmname = this.getAtmname();
                System.out.println(atmname);
                break;
            case "resetatms":
                this.resetAtm();
                break;
            default:
                System.out.println("No such bulk command!");
                break;
        }
    }
}

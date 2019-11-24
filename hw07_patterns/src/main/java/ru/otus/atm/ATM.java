package ru.otus.atm;

import java.util.EnumMap;

class ATM implements Cloneable {

    final Cells cells;

    private String atmname;

    String getAtmname() {
        return atmname;
    }

    @Override
    public ATM clone() {
        return new ATM(new Cells(cells.nominalscount), atmname);
    }

    ATM(Cells cells, String atmname) {
        this.cells = cells;
        this.atmname = atmname;
    }

    void insertMoney(Integer nominalvalue, Integer nominalcount) {
        Cells.Nominals nominal = Cells.Nominals.getEnumByInt(nominalvalue);
        if (nominal != null) {
            cells.cellsmap.put(nominal, cells.nominalscount + nominalcount);
        }
    }

    void printMoney(Integer sum) {
        sum = moneyGetHelper(sum, 1000, cells.cellsmap);
        sum = moneyGetHelper(sum, 100, cells.cellsmap);
        sum = moneyGetHelper(sum, 50, cells.cellsmap);
        sum = moneyGetHelper(sum, 10, cells.cellsmap);
    }

    void printAllMoney() {
        if (totalMoneyAtm() != 0) {
            System.out.println("Get remaining money: " + totalMoneyAtm());
            for (Cells.Nominals e : Cells.Nominals.values()) {
                cells.cellsmap.put(e, 0);
            }
            System.out.println(cells.cellsmap);
        } else {
            System.out.println("No money!");
        }
    }

    int totalMoneyAtm() {
        int totalsum = 0;
        for (Cells.Nominals e : Cells.Nominals.values()) {
            totalsum = totalsum + e.getLabel() * (int) cells.cellsmap.get(e);
        }
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

}
package ru.otus.atm.commands;

import ru.otus.atm.ATM;
import ru.otus.atm.Cells;

import java.util.EnumMap;

public class PrintMoney implements Command{

    public int sum;

    public  PrintMoney(int sum) {
        this.sum = sum;
    }
    @Override
    public void doCommand(ATM atm) {
        sum = moneyGetHelper(sum, 1000, atm.cells.cellsmap);
        sum = moneyGetHelper(sum, 100, atm.cells.cellsmap);
        sum = moneyGetHelper(sum, 50, atm.cells.cellsmap);
        sum = moneyGetHelper(sum, 10, atm.cells.cellsmap);
    }

    private int moneyGetHelper(int sum, int banknotevalue, EnumMap<Cells.Nominals, Integer> cellmap) {
        if (sum / banknotevalue >= 1) {
            int count = sum / banknotevalue;
            Cells.Nominals nominal = Cells.Nominals.getEnumByInt(banknotevalue);
            if (cellmap.get(nominal) >= count) {
                sum = sum - banknotevalue * count;
                assert nominal != null;
                cellmap.put(nominal, cellmap.get(nominal) - count);
                System.out.println("Take " + Cells.Nominals.getEnumByInt(banknotevalue) + "'s: " + count);
            } else {
                System.out.println("Not enough " + banknotevalue + " banknotes in ATM!");
            }
        }
        return sum;
    }
}

package ru.otus.atm;

import java.util.ArrayList;
import java.util.Arrays;

class ATM {

    private Cell cell = new Cell();

    void insertMoney(Nominal[] banknotes) {
        Arrays.stream(banknotes).forEach(this::moneyStoreHelper);
    }

    void getMoney(int sum) {
        if (sum % 10 == 0) {
            sum = moneyGetHelper(sum, 1000, cell.cellForThousands);
            sum = moneyGetHelper(sum, 100, cell.cellForHundreds);
            sum = moneyGetHelper(sum, 50, cell.cellForFifties);
            sum = moneyGetHelper(sum, 10, cell.cellForTens);
        } else {
            System.out.println("sum must be a multiple of 10!");
        }
    }

    void getAllMoney() {
        if (totalMoneyAtm() != 0) {
            System.out.println("Get remaining money: " + totalMoneyAtm());
            cell.cellForTens.clear();
            cell.cellForFifties.clear();
            cell.cellForHundreds.clear();
            cell.cellForThousands.clear();
        } else {
            System.out.println("No money!");
        }
    }

    int totalMoneyAtm() {
        return (cell.cellForTens.size() * 10 + cell.cellForFifties.size() * 50 + cell.cellForHundreds.size() * 100 + cell.cellForThousands.size() * 1000);
    }

    private void moneyStoreHelper(Nominal banknote) {
        if (banknote instanceof Ten) {
            cell.cellForTens.add((Ten) banknote);
        } else if (banknote instanceof Fifty) {
            cell.cellForFifties.add((Fifty) banknote);
        } else if (banknote instanceof Hundred) {
            cell.cellForHundreds.add((Hundred) banknote);
        } else if (banknote instanceof Thousand) {
            cell.cellForThousands.add((Thousand) banknote);
        } else {
            throw new RuntimeException("Not a valid banknote!");
        }
    }

    private int moneyGetHelper(int sum, int banknoteValue, ArrayList cellType) {
        if (sum / banknoteValue >= 1) {
            if (cellType.size() >= sum / banknoteValue) {
                int count = sum / banknoteValue;
                for (int i = 0; i < count; i++) {
                    cellType.remove(cellType.size() - 1);
                }
                sum = sum - banknoteValue * count;
                System.out.println("Take " + banknoteValue + "'s: " + count);
            } else {
                System.out.println("Not enough " + banknoteValue + " banknotes in ATM!");
            }
        }
        return sum;
    }
}

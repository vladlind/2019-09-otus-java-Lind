package ru.otus.atm;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE)
class ATM {

    Cell cell = new Cell();

    void insertMoney(Nominal[] banknotes) {
        Arrays.stream(banknotes).forEach(this::storeMoney);
    }

    void getMoney(int sum) {
        if (sum % 10 == 0) {
            if (sum / 1000 >= 1 && cell.cellForThousands.size() >= sum / 1000) {
                int count = sum / 1000;
                for (int i = 0; i < count; i++) {
                    cell.cellForThousands.remove(cell.cellForThousands.size() - 1);
                }
                sum = sum - 1000 * count;
                System.out.println("Take 1000's: "+count);
            }
            if (sum / 100 >= 1 && cell.cellForHundreds.size() >= sum / 100) {
                int count = sum / 100;
                for (int i = 0; i < count; i++) {
                    cell.cellForHundreds.remove(cell.cellForHundreds.size() - 1);
                }
                sum = sum - 100 * count;
                System.out.println("Take 100's: "+count);
            }
            if (sum / 50 >= 1 && cell.cellForFifties.size() >= sum / 50) {
                int count = sum / 50;
                for (int i = 0; i < count; i++) {
                    cell.cellForFifties.remove(cell.cellForFifties.size() - 1);
                }
                sum = sum - 50 * count;
                System.out.println("Take 50's: "+count);
            }
            if (sum / 10 >= 1 && cell.cellForTens.size() >= sum / 10) {
                int count = sum / 10;
                for (int i = 0; i < count; i++) {
                    cell.cellForTens.remove(cell.cellForTens.size() - 1);
                }
                sum = sum - 10 * count;
                System.out.println("Take 10's: "+count);
            }
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
            throw new RuntimeException("No money!");
        }
    }

    public int totalMoneyAtm() {
        return (cell.cellForTens.size() * 10 + cell.cellForFifties.size() * 50 + cell.cellForHundreds.size() * 100 + cell.cellForThousands.size() * 1000);
    }

    private void storeMoney(Nominal banknote) {
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
}

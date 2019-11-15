package ru.otus.atm;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE)
class ATM {

    Cell cell = new Cell();

    void insertMoney(Nominal[] banknotes) {
        Arrays.stream(banknotes).forEach(this::storeMoney);
    }

    void getMoney(int Sum) {
        if (Sum % 10 == 0) {
            if (Sum / 1000 >= 1) {
                for (int i = 0; i < (Sum / 1000); i++) {
                    cell.cellForThousands.remove(cell.cellForThousands.size() -1 );
                }
            }
            } else {
                throw new RuntimeException("Sum must be a multiple of 10!");
            }
        }
        void getAllMoney () {
            if (totalMoneyAtm() != 0) {
                System.out.println(" Get remaining money: " + totalMoneyAtm());
                cell.cellForTens.clear();
                cell.cellForFifties.clear();
                cell.cellForHundreds.clear();
                cell.cellForThousands.clear();
            } else {
                throw new RuntimeException("No money!");
            }
        }
        public int totalMoneyAtm () {
            return (cell.cellForTens.size() * 10 + cell.cellForFifties.size() * 50 + cell.cellForHundreds.size() * 100 + cell.cellForThousands.size() * 1000);
        }

        private void storeMoney (Nominal banknote){
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

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
            if (sum / 1000 >= 1) {
                for (int i = 0; i < (sum / 1000); i++) {
                    try {
                        cell.cellForThousands.remove(cell.cellForThousands.size() - 1);
                    } catch (Exception e) {
                        System.out.println("No 1000's in ATM! - "+e);
                    }
                }
                sum = sum - 1000 * (sum / 1000);
                System.out.println(sum);
            }
            if (sum / 100 >= 1) {
                for (int i = 0; i < (sum / 100); i++) {
                    try {
                        cell.cellForHundreds.remove(cell.cellForHundreds.size() - 1);
                    } catch (Exception e) {
                        System.out.println("No 100's in ATM! - "+e);
                    }
                }
                sum = sum - 100 * (sum / 100);
                System.out.println(sum);
            }
            if (sum / 50 >= 1) {
                for (int i = 0; i < (sum / 50); i++) {
                    try {
                        cell.cellForFifties.remove(cell.cellForFifties.size() - 1);
                    } catch (Exception e) {
                        System.out.println("No 50's in ATM! - "+e);
                    }
                }
                sum = sum - 50 * (sum / 50);
                System.out.println(sum);
            }
            if (sum / 10 >= 1) {
                for (int i = 0; i < (sum / 10); i++) {
                    try {
                        cell.cellForTens.remove(cell.cellForTens.size() - 1);
                    } catch (Exception e) {
                        System.out.println("No 1000's in ATM! - "+e);
                    }
                }
                sum = sum - 10 * (sum / 10);
            }
        } else {
            throw new RuntimeException("sum must be a multiple of 10!");
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

package ru.otus.atm;

import lombok.Getter;

import java.util.EnumMap;
import java.util.EnumSet;

class Cells {

    public enum Nominals {
        TEN(10),
        FIFTY (50),
        HUNDRED(100),
        THOUSAND(1000);

        @Getter
        private final Integer label;

        Nominals(int label) {
            this.label = label;
        }

        public static Nominals getEnumByInt(int value){
            for(Nominals e : Nominals.values()){
                if(value == e.label) return e;
            }
            return null;
        }
    }

    final EnumMap cellsmap;

    Cells() {
        this.cellsmap = createCells();
    }

    private EnumMap createCells() {
        EnumMap<Nominals, Integer> cellsmap = new EnumMap<>(Nominals.class);
        EnumSet.allOf(Nominals.class).forEach(nominal -> cellsmap.put(nominal, 0));
        return cellsmap;
    }
}

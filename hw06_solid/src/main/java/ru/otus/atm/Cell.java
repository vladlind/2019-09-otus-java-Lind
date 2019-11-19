package ru.otus.atm;

import java.util.ArrayList;

class Cell {
    ArrayList<Nominals> cellForTens = new ArrayList<>();
    ArrayList<Nominals> cellForFifties = new ArrayList<>();
    ArrayList<Nominals> cellForHundreds = new ArrayList<>();
    ArrayList<Nominals> cellForThousands = new ArrayList<>();
}

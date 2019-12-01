package ru.otus.atm.commands;

import ru.otus.atm.ATM;

public interface Command {

    void doCommand(ATM atm);
}

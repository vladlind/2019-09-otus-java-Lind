package ru.otus.atm.commands;

import ru.otus.atm.ATM;

public class GetATMname implements Command {

    @Override
    public void doCommand(ATM atm) {
        System.out.println(atm.getAtmname());
    }
}

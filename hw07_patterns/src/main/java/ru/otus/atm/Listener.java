package ru.otus.atm;

import ru.otus.atm.commands.Command;

public interface Listener {
    void notify(Command command);
}

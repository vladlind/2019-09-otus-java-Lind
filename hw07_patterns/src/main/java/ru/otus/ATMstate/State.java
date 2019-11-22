package ru.otus.ATMstate;

import ru.otus.atm.ATM;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class State {
    private final ATM atm;

    State(ATM atm) {
        this.atm = atm;
    }

    ATM getAtm() {
        return atm;
    }

    State(State state) {
        this.atm = state.getAtm();
    }
}

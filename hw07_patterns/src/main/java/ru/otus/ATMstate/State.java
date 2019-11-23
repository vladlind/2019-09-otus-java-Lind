package ru.otus.ATMstate;

import ru.otus.atm.ATMDept;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class State {
    private final ATMDept atmdept;

    State(ATMDept atmdept) {
        this.atmdept = atmdept;
    }

    private ATMDept getAtmdept() {
        return atmdept;
    }

    State(State state) {
        this.atmdept = state.getAtmdept();
    }
}

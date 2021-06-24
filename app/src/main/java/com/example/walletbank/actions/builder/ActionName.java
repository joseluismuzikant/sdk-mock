package com.example.walletbank.actions.builder;

import java.util.ArrayList;
import java.util.List;

public enum ActionName {

    NO_ACTION("NO_ACTION"),

    /**
     * Functional services
     **/
    ACTIVATE_TOKEN("ACTIVATE_TOKEN"),
    ANIMATE_PAYMENT("ANIMATE_PAYMENT"),
    BASE_REGISTER_TOKEN("BASE_REGISTER_TOKEN"),
    GET_NFC_STATUS("GET_NFC_STATUS"),
    PAY_WITH_NFC("PAY_WITH_NFC"),
    REGISTER_TOKEN("REGISTER_TOKEN"),
    SELECT_TOKEN("SELECT_TOKEN"),
    SUSPEND_TOKEN("SUSPEND_TOKEN"),
    UNREGISTER_TOKEN("UNREGISTER_TOKEN");

    private final String name;

    private ActionName(String s) {
        name = s;
    }

    public static List<String> getActionNames() {
        List<String> actionNames = new ArrayList<>();
        for (ActionName actionName : ActionName.values()) {
            actionNames.add(actionName.name);
        }
        return actionNames;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}

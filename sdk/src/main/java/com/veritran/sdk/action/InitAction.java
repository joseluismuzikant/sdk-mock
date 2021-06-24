package com.veritran.sdk.action;

import android.app.Application;

import com.veritran.ui.interfaces.VeritranModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InitAction {

    private Application application;
    private Map<String, String> constants;
    private List<VeritranModule> permissions;

    public InitAction(Application application, Map<String, String> constants, List<VeritranModule> permissions) {
        this.application = application;
        this.constants = constants;
        if (permissions != null) {
            this.permissions = permissions;
        } else {
            this.permissions = new ArrayList<>();
        }
    }

    public void execute() {

    }

}

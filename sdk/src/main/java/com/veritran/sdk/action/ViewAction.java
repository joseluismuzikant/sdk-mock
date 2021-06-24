package com.veritran.sdk.action;

import android.app.Activity;

public class ViewAction {

    private Activity activity;

    public ViewAction(Activity activity, ActionMessage flowMessageCallback) {
        this.activity = activity;

    }

    public void execute() {
        execute(null);
    }

    public void execute(String view) {

    }

}
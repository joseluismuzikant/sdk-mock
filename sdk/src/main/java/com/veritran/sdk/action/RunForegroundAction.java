package com.veritran.sdk.action;

import android.app.Activity;

import com.veritran.sdk.Sdk;

import java.util.Map;

public final class RunForegroundAction {

    private final Activity activity;
    private final Sdk.SdkActionMessage sdkMessage;
    private final String name;
    private final Map<String, String> parameters;

    public RunForegroundAction(Activity activity, final Sdk.SdkActionMessage sdkMessage, final String name, Map<String, String> parameters) {
        this.activity = activity;
        this.sdkMessage = sdkMessage;
        this.name = name;
        this.parameters = parameters;
    }

    public void execute() {
        throw new RuntimeException("Not implmented in mock version!!");
    }
}

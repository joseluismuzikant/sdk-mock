package com.veritran.sdk.action;

import android.app.Activity;

public interface ActionMessage {
    void onMessage(String command);

    void onError();

    void onUpdateConfigurationRequired();

    void onUpdateSdkRequired();

    void onClose(Activity activity);
}

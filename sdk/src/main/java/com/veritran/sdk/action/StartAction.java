package com.veritran.sdk.action;

import android.app.Activity;
import android.os.Looper;

public class StartAction {

    private Activity activity;
    private Message startMessage;

    public StartAction(Activity activity, Message startMessage) {
        this.activity = activity;
        this.startMessage = startMessage;
    }

    public void execute() {
        final android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
    }

    public interface Message {
        void onProgressMessageUpdate(String message);

        void onProgressPercentageUpdate(int percentage);

        void onFinish();

        void onError(String message);
    }
}

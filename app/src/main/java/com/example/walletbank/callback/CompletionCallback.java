package com.example.walletbank.callback;

import android.app.Activity;

public abstract class CompletionCallback {


    private Activity activity;

    public CompletionCallback(Activity activity) {
        this.activity = activity;
    }


    public abstract void onError(Exception e);

    public abstract void onSuccess(String... results);

    protected Activity getActivity() {
        return activity;
    }
}

package com.example.walletbank.callback;

import android.app.Activity;
import android.widget.Toast;

public class DeafCallback extends CompletionCallback {

    public DeafCallback(Activity activity) {
        super(activity);
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getActivity(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String... results) {
        Toast.makeText(getActivity(), results[0], Toast.LENGTH_LONG).show();
    }
}

package com.example.walletbank.callback;

import android.app.Activity;
import android.widget.Toast;

import java.util.Arrays;

public class DefaultCompletionCallbackImpl extends CompletionCallback {


    public DefaultCompletionCallbackImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String... results) {
        Toast.makeText(getActivity(), Arrays.toString(results), Toast.LENGTH_LONG).show();
    }
}

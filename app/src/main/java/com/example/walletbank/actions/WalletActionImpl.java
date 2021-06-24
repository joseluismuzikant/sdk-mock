package com.example.walletbank.actions;

import android.app.Activity;

import com.example.walletbank.callback.CompletionCallback;
import com.veritran.sdk.Sdk;

import java.util.Map;

public abstract class WalletActionImpl implements WalletAction {
    private final CompletionCallback completionCallback;
    private final Activity activity;
    private final Map<String, String> parameters;

    public WalletActionImpl(CompletionCallback completionCallback,
                            Activity activity,
                            Map<String, String> parameters) {
        this.completionCallback = completionCallback;
        this.activity = activity;
        this.parameters = parameters;
    }

    protected abstract String getName();

    protected abstract Sdk.SdkActionMessage buildSdkActionMessage();

    protected abstract void addInboundParameters(Sdk.Action action);

    @Override
    public void execute() {

        Sdk.Action action = new Sdk.Action(activity, this.buildSdkActionMessage(), getName());

        this.addInboundParameters(action);

        Sdk.runAction(action);

    }

    protected CompletionCallback getCompletionCallback() {
        return completionCallback;
    }

    protected Activity getActivity() {
        return activity;
    }
    
    public Map<String, String> getParameters() {
        return parameters;
    }
}

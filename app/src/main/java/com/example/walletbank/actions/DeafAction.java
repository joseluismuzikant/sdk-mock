package com.example.walletbank.actions;

import com.example.walletbank.callback.CompletionCallback;

public class DeafAction implements WalletAction {
    private CompletionCallback callback;

    public DeafAction(CompletionCallback callback) {
        this.callback = callback;
    }

    @Override
    public void execute() {
        this.callback.onSuccess(new String[]{"deaf action success!!"});
    }
}

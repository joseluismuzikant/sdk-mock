package com.example.walletbank.actions;

import android.app.Activity;

import com.example.walletbank.actions.builder.ActionName;
import com.example.walletbank.callback.CompletionCallback;
import com.example.walletbank.exceptions.WalletException;
import com.veritran.sdk.Sdk;

import java.util.Map;

public class PayWithNfcAction extends WalletActionImpl {


    public PayWithNfcAction(CompletionCallback completionCallback,
                            Activity activity,
                            Map<String, String> parameters) {
        super(completionCallback, activity, parameters);
    }

    @Override
    protected String getName() {
        return ActionName.PAY_WITH_NFC.name();
    }

    @Override
    protected Sdk.SdkActionMessage buildSdkActionMessage() {
        return new Sdk.SdkActionMessage() {
            @Override
            public void onMessage(Sdk.Response response) {
                String result = response.getOutput("response_code");
                if (!result.equalsIgnoreCase("400")) {
                    String resultMessage = response.getOutput("resultMessage");
                    getCompletionCallback().onError(new WalletException(resultMessage != null ? resultMessage : ""));
                } else {
                    String response_code = response.getOutput("response_code");
                    getCompletionCallback().onSuccess(response_code);
                }
            }

            @Override
            public void onError(String errorMessage) {
                getCompletionCallback().onError(new WalletException(errorMessage));
            }

            @Override
            public void onUpdateRequired(String s) {
            }

            @Override
            public void onClose(String s, Activity activity) {
            }
        };
    }

    @Override
    protected void addInboundParameters(Sdk.Action action) {
        /*No input parameters*/
    }
}
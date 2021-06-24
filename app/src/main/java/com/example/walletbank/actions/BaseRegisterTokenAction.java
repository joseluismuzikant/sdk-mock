package com.example.walletbank.actions;


import android.app.Activity;

import com.example.walletbank.actions.builder.ActionName;
import com.example.walletbank.callback.CompletionCallback;
import com.example.walletbank.exceptions.WalletException;
import com.veritran.sdk.Sdk;

import java.util.Map;

public class BaseRegisterTokenAction extends WalletActionImpl {


    public BaseRegisterTokenAction(CompletionCallback completionCallback,
                                   Activity activity,
                                   Map<String, String> parameters) {
        super(completionCallback, activity, parameters);
    }


    @Override
    protected String getName() {
        return ActionName.BASE_REGISTER_TOKEN.name();
    }

    @Override
    protected Sdk.SdkActionMessage buildSdkActionMessage() {
        return new Sdk.SdkActionMessage() {
            @Override
            public void onMessage(Sdk.Response response) {
                String result = response.getOutput("response_code");
                if (!result.equalsIgnoreCase("200")) {
                    String resultMessage = response.getOutput("resultMessage");
                    getCompletionCallback().onError(new WalletException(resultMessage != null ? resultMessage : ""));
                } else {
                    String token_key = response.getOutput("token_key");
                    getCompletionCallback().onSuccess(token_key);
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
        action.addInput("primary_account_number",getParameters().get("primary_account_number"));
        action.addInput("expiration_date",getParameters().get("expiration_date"));
        action.addInput("security_code",getParameters().get("security_code"));
    }
}
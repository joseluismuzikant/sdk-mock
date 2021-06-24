package com.example.walletbank.actions.builder;

import android.app.Activity;

import com.example.walletbank.actions.ActivateTokenAction;
import com.example.walletbank.actions.AnimateAction;
import com.example.walletbank.actions.BaseRegisterTokenAction;
import com.example.walletbank.actions.DeafAction;
import com.example.walletbank.actions.GetNfcStatusAction;
import com.example.walletbank.actions.PayWithNfcAction;
import com.example.walletbank.actions.RegisterTokenAction;
import com.example.walletbank.actions.SelectTokenAction;
import com.example.walletbank.actions.SuspendTokenAction;
import com.example.walletbank.actions.UnregisterTokenAction;
import com.example.walletbank.actions.WalletAction;
import com.example.walletbank.callback.DeafCallback;
import com.example.walletbank.callback.DefaultCompletionCallbackImpl;

import java.util.Map;

public class ActionBuilder {

    private ActionBuilder() {

    }

    public static WalletAction buildExecutableActionMessage(Activity activity, ActionName actionName, Map<String, String> parameters) {

        WalletAction walletAction = buildDefaultAction(activity, actionName, parameters);

        switch (actionName) {
            case ACTIVATE_TOKEN:
                walletAction = buildActivateTokenAction(activity, actionName, parameters);
                break;
            case ANIMATE_PAYMENT:
                walletAction = buildAnimateAction(activity, actionName, parameters);
                break;
            case BASE_REGISTER_TOKEN:
                walletAction = buildBaseregisterTokenAction(activity, actionName, parameters);
                break;
            case GET_NFC_STATUS:
                walletAction = buildGetNfcStatusAction(activity, actionName, parameters);
                break;
            case PAY_WITH_NFC:
                walletAction = buildPayWithNfcAction(activity, actionName, parameters);
                break;
            case REGISTER_TOKEN:
                walletAction = buildRegisterTokenAction(activity, actionName, parameters);
                break;
            case SELECT_TOKEN:
                walletAction = buildSelectTokenAction(activity, actionName, parameters);
                break;
            case SUSPEND_TOKEN:
                walletAction = buildSuspendTokenAction(activity, actionName, parameters);
                break;
            case UNREGISTER_TOKEN:
                walletAction = buildUnregisterTokenAction(activity, actionName, parameters);
                break;

        }

        return walletAction;

    }

    private static WalletAction buildActivateTokenAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        parameters.put("token_key", "16c31aa6-2cab-48d5-8db9-30c9ddaac582");

        WalletAction walletAction = new ActivateTokenAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildAnimateAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        parameters.put("brand_name", "VISA");

        WalletAction walletAction = new AnimateAction(
                new DefaultCompletionCallbackImpl(activity),
                activity, parameters);
        return walletAction;
    }

    private static WalletAction buildBaseregisterTokenAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        parameters.put("primary_account_number", "1111222233334444");
        parameters.put("expiration_date", "0122");
        parameters.put("security_code", "233");

        WalletAction walletAction = new BaseRegisterTokenAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildGetNfcStatusAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        WalletAction walletAction = new GetNfcStatusAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildPayWithNfcAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        WalletAction walletAction = new PayWithNfcAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildSelectTokenAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        parameters.put("token_key", "22-c31aa6-2cab-48d5-8db9-30c9ddaac5-92");

        WalletAction walletAction = new SelectTokenAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildSuspendTokenAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        parameters.put("token_key", "20-c31aa6-2cab-48d5-8db9-30c9ddaac5-90");

        WalletAction walletAction = new SuspendTokenAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildRegisterTokenAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        parameters.put("pan_reference_number", "2212212-2212-XHSH212121");

        WalletAction walletAction = new RegisterTokenAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildUnregisterTokenAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        parameters.put("token_key", "21-c31aa6-2cab-48d5-8db9-30c9ddaac5-91");

        WalletAction walletAction = new UnregisterTokenAction(
                new DefaultCompletionCallbackImpl(activity),
                activity,
                parameters);
        return walletAction;
    }

    private static WalletAction buildDefaultAction(Activity activity, ActionName actionName, Map<String, String> parameters) {
        return new DeafAction(new DeafCallback(activity));
    }

}

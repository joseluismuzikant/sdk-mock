package com.veritran.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.veritran.sdk.action.ClassicStartAction;
import com.veritran.sdk.action.InitAction;
import com.veritran.sdk.action.RunAction;
import com.veritran.sdk.action.RunBackgroundAction;
import com.veritran.sdk.action.RunForegroundAction;
import com.veritran.sdk.action.StartAction;
import com.veritran.sdk.exceptions.UndefinedEntrypoint;
import com.veritran.sdk.map.Mapping;
import com.veritran.sdk.map.OutboundParameters;
import com.veritran.ui.interfaces.VeritranModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Sdk {

    public static final String DEFAULT_ENTRYPOINT = "ENTRYPOINT";
    public static final String CONFIGURATION_UPDATE_MESSAGE = "CONFIGURATION";
    public static final String SDK_UPDATE_MESSAGE = "SDK";

    private Sdk() {
    }

    /**
     * Sdk initialize on Application start
     *
     * @param application
     * @param constants
     * @param permissions
     */
    public static void init(final Application application, Map<String, String> constants, List<VeritranModule> permissions) {
        InitAction initAction = new InitAction(application, constants, permissions);
        initAction.execute();
        Mapping.init(application.getApplicationContext());
    }

    public static void init(final Application application, Map<String, String> constants) {
        InitAction initAction = new InitAction(application, constants, null);
        initAction.execute();
        Mapping.init(application.getApplicationContext());
    }

    /**
     * Use internal Splash and Navigation flow
     * classic
     *
     * @param activity
     */
    public static void classicStart(VTCommonActivity activity) {
        ClassicStartAction classicStartAction = new ClassicStartAction(activity);
        classicStartAction.execute();
    }

    /**
     * Download and Parse configuration on a custom View
     * custom
     *
     * @param activity
     * @param downloadMessage
     */
    public static void start(final Activity activity, final StartAction.Message downloadMessage) {
        StartAction startAction = new StartAction(activity, downloadMessage);
        startAction.execute();
    }

    /**
     * Run Navigation flow on a custon Activity
     * custom
     *
     * @param activity
     */
    static void run(VTCommonActivity activity) {
        RunAction runAction = new RunAction(activity);
        runAction.execute();
    }

    public static Drawable imageById(Context context, String id) {
        throw new RuntimeException("Not implemented method");
    }

    /**
     * Run Action
     */

    public static void runAction(Action action) {
        action.execute();
    }

    public static void runForeground(Action action) {
        action.executeForeground();
    }

    public static void runBackground(Action action) {
        action.executeBackground();
    }

    /**
     * interfaces
     */

    public interface SdkActionMessage {
        void onMessage(Sdk.Response response);

        void onError(String actionName);

        void onUpdateRequired(String actionName);

        void onClose(String actionName, Activity activity);
    }

    /**
     * action
     */

    public static class Action {

        private Activity activity;
        private SdkActionMessage actionCallback;
        private String actionName;

        private Map<String, String> input = new HashMap<>();

        public Action(Activity activity, SdkActionMessage actionCallback) {
            this.activity = activity;
            this.actionCallback = actionCallback;
        }

        public Action(Activity activity, SdkActionMessage actionCallback, String actionName) {
            this(activity, actionCallback);
            this.actionName = actionName;
        }

        public void addInput(String key, String value) {
            input.put(key, value);
        }

        void execute() {
            final Mapping.EntryPoint entryPoint = Mapping.getEntryPoint(this.actionName);
            if (entryPoint == null) {
                actionCallback.onError(new UndefinedEntrypoint().getMessage());
                return;
            }

            executeBackground();
        }

        void executeBackground() {
            new RunBackgroundAction(activity, actionCallback, actionName, input).execute();
        }

        void executeForeground() {
            new RunForegroundAction(activity, actionCallback, actionName, input).execute();
        }
    }

    public static class Response {
        String action;
        String command;
        OutboundParameters result = new OutboundParameters();

        public Response(String action, String command, OutboundParameters result) {
            this.action = action;
            this.command = command;
            this.result = result;
        }

        public String getAction() {
            return action;
        }

        public String getCommand() {
            return command;
        }

        public String getOutput(String key) {
            return result.get(key);
        }

        public Map<String, String> getMap() {
            return result.getMap();
        }


    }
}

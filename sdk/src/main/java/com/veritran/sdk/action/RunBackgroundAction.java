package com.veritran.sdk.action;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.veritran.sdk.Sdk;
import com.veritran.sdk.exceptions.InvalidInboundParameters;
import com.veritran.sdk.map.InboundParameters;
import com.veritran.sdk.map.Mapping;
import com.veritran.sdk.map.OutboundParameters;

import java.util.Map;

public final class RunBackgroundAction extends AsyncTask<String, Integer, String> {
    private static final String SUCCESS_MESSAGE = "Run Background Action Successfully!";


    private static final int ITERATIONS_TO_WAIT = 5;

    private final Activity activity;
    private final Sdk.SdkActionMessage sdkMessage;
    private final String name;
    private final Map<String, String> parameters;
    private Sdk.Response response;
    private ProgressDialog mProgressDialog;

    public RunBackgroundAction(Activity activity, final Sdk.SdkActionMessage sdkMessage,
                               final String name, Map<String, String> parameters) {
        this.activity = activity;
        this.sdkMessage = sdkMessage;
        this.name = name;
        this.parameters = parameters;

        // Initialize the progress dialog
        mProgressDialog = new ProgressDialog(this.activity);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setTitle("Run Background Action");
        mProgressDialog.setMessage("Please wait, we are sending message to Xpress Plug sdk...");

    }

    @Override
    protected void onPreExecute() {
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        Mapping.EntryPoint entryPoint = Mapping.getEntryPoint(name);
        InboundParameters inboundParameters = entryPoint.getInboundParameters();
        OutboundParameters outboundParameters = entryPoint.getOutboundParameters();

        try {
            inboundParameters.validateParams(this.parameters);
        } catch (InvalidInboundParameters iipe) {
            response = new Sdk.Response(name, iipe.getMessage(), outboundParameters);
            return iipe.getMessage();
        }

        for (int i = 0; i < ITERATIONS_TO_WAIT; i++) {
            // Sleep the thread for 1 second
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress((int) (((i + 1) / (float) ITERATIONS_TO_WAIT) * 100));

            if (isCancelled()) {
                break;
            }
        }

        response = new Sdk.Response(name, SUCCESS_MESSAGE, outboundParameters);

        return SUCCESS_MESSAGE;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        mProgressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.equals(SUCCESS_MESSAGE))
            sdkMessage.onMessage(response);
        else
            sdkMessage.onError(result);

        mProgressDialog.dismiss();
    }

}

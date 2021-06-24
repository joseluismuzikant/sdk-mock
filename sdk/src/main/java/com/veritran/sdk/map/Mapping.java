package com.veritran.sdk.map;

import android.content.Context;

import com.veritran.sdk.exceptions.InvalidOutboundParameters;
import com.veritran.sdk.exceptions.UndefinedEntrypoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.TreeMap;

/**
 * Old Mapping class
 */
public class Mapping {

    private static Map<String, EntryPoint> entryPointsMap = new TreeMap<String, EntryPoint>();

    private static String[] actionNames;
    private static JSONObject jsonMapping = null;

    private Mapping() {

    }

    /**
     * esto tiene que tomar el mapping.json de un siteparameter
     */
    public static boolean init(Context context) {
        if (jsonMapping != null) {
            return true;
        }

        if (context == null)
            return false;

        Writer writer = new StringWriter();
        try {
            InputStream is = context.getAssets().open("mapping.json");
            char[] buffer = new char[1024];
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
            jsonMapping = new JSONObject(writer.toString());
        } catch (Exception ignored) {
            return false;
        }

        loadEntryPoints();

        return true;
    }

    public static String[] getActionNames() {
        return actionNames;
    }

    public static EntryPoint getEntryPoint(String entryPointName) {
        return entryPointsMap.get(entryPointName);
    }

    private static void loadEntryPoints() {
        JSONArray entryPoints = getEntryPoints();
        EntryPoint entryPoint;
        JSONObject entryPointJsonObject;
        try {
            for (int i = 0; i < entryPoints.length(); i++) {
                entryPointJsonObject = entryPoints.getJSONObject(i);
                entryPoint = new EntryPoint(entryPointJsonObject);
                entryPointsMap.put(entryPointJsonObject.getString("name").toUpperCase(),
                        entryPoint);
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        actionNames = entryPointsMap.keySet().toArray(new String[entryPointsMap.size()]);

    }

    private static JSONArray getEntryPoints() {
        JSONArray entryPoints;
        try {
            entryPoints = jsonMapping.getJSONArray("entryPoint");
        } catch (JSONException e) {
            return null;
        }
        return entryPoints;
    }

    private static void populateParameters(Parameters parameters, JSONArray jsonParameters) throws JSONException {
        for (int i = 0; i < jsonParameters.length(); i++) {
            JSONObject jsonParam = jsonParameters.getJSONObject(i);
            String name = jsonParam.getString("name");
            String value ="";
            try{
                value = jsonParam.getString("value");
            }catch (JSONException je){
                value= "";
            }
            parameters.put(name, value);
        }
    }

    public static class EntryPoint {
        private final JSONObject entryPoint;
        private final OutboundParameters outbound = new OutboundParameters();
        private final InboundParameters inbound = new InboundParameters();

        EntryPoint(JSONObject entryPoint) {
            this.entryPoint = entryPoint;
            try {
                JSONObject parametersJson = entryPoint.getJSONObject("parameters");

                JSONArray jsonOutbounds = parametersJson.getJSONArray("outbound");
                populateParameters(outbound, jsonOutbounds);

                JSONArray jsonInbounds = parametersJson.getJSONArray("inbound");

                populateParameters(inbound, jsonInbounds);


            } catch (JSONException e) {
                new InvalidOutboundParameters("Invalid Outbound parameters in mapping config");
            }

        }

        public String getAction() {
            if (entryPoint == null) throw new UndefinedEntrypoint();
            try {
                return entryPoint.getString("action");
            } catch (JSONException e) {
                return null;
            }
        }

        public OutboundParameters getOutboundParameters() {
            return outbound;
        }

        public InboundParameters getInboundParameters() {
            return inbound;
        }
    }
}

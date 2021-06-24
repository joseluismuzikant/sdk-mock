package com.veritran.sdk.map;

import com.veritran.sdk.exceptions.InvalidInboundParameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InboundParameters implements Parameters {

    private List<String> inboundParameters = new ArrayList<>();

    public String get(String key) {
        String result = inboundParameters.get(inboundParameters.indexOf(key));
        return result != null ? result : "";
    }

    public void put(String key, String value) {
        inboundParameters.add(key);
    }


    public void validateParams(Map<String, String> parameters) {
        if (inboundParameters.containsAll(parameters.keySet()) &&
                parameters.keySet().containsAll(inboundParameters) &&
                validateValues(parameters.values())) {
            return;
        } else {
            throw new InvalidInboundParameters("Invalid Inbound Error: parameters no match mapping config");
        }
    }

    private boolean validateValues(Collection<String> values) {
        boolean allValids = true;
        for(String value: values){
            if(value == null || value.trim().length() ==0)
                return false;
        }
        return allValids;
    }
}

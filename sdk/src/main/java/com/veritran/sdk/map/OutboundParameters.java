package com.veritran.sdk.map;

import java.util.HashMap;
import java.util.Map;

public class OutboundParameters implements Parameters {

    private Map<String, String> map = new HashMap<>();

    public String get(String key) {
        String result = map.get(key);
        return result != null ? result : "";
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    public Map<String, String> getMap() {
        return map;
    }
}

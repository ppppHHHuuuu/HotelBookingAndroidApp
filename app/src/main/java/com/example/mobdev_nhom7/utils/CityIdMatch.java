package com.example.mobdev_nhom7.utils;

import java.util.HashMap;
import java.util.Map;

public  class CityIdMatch {
    // Global static map
    public static final Map<String, String> globalMap;

    // Static initialization block to populate the map
    static {
        globalMap = new HashMap<>();
        globalMap.put("Ha Noi", "Z6YyrwkuyVbsyaLxOE7E");
        globalMap.put("Ho Chi Minh", "8u8doWKXGkMc0gvXYA58");
        globalMap.put("Rome", "AOLUEoqmAt5lN8IhpQ50");
        globalMap.put("Hai Phong", "nw2udhrsvdQGsXSgOO43");
        globalMap.put("New York", "aSQphDmzTKedOvUrkQPR");
        globalMap.put("Madrid", "kHHB7p3XZysb9H83n8gz");
        globalMap.put("Paris", "a3gN4bdOKVEgpEjxi5nU");
    }

    public static String getValue(String key) {
        if (globalMap.get(key) == null ) {
            return "";
        }
        return globalMap.get(key);
    }
    public static String getMatchingKey(String prefix) {
        String matchingKey = "";
        for (String key : globalMap.keySet()) {
            if (key.toLowerCase().startsWith(prefix.toLowerCase())) {
                matchingKey = key;
            }
        }
        if (matchingKey == "") matchingKey = "";
        return matchingKey;
    }
}

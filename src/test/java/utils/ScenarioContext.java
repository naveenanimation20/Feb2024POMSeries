package utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, Object> dataMap = new HashMap<>();

    public void setContext(String key, Object value) {
        dataMap.put(key, value);
    }

    public Object getContext(String key) {
        return dataMap.get(key);
    }

    public boolean contains(String key) {
        return dataMap.containsKey(key);
    }

    public void clear() {
        dataMap.clear();
    }
}


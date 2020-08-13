package com.sprint.summerproject.storages;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class CodeStorage {

    private final int CAP = 500;
    private LinkedHashMap<String, String> cache = new LinkedHashMap<String, String>();

    private void makeRecently(String key) {
        String val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

    public String get(String key) {
        if (!cache.containsKey(key)) return null;
        makeRecently(key);
        return cache.get(key);
    }

    public void put(String key, String val) {
        if (cache.containsKey(key)) {
            cache.put(key, val);
            makeRecently(key);
        } else {
            if (cache.size() > CAP) {
                cache.remove(cache.keySet().iterator().next());
            }
            cache.put(key, val);
        }
    }
}

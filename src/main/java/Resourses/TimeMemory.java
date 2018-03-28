package Resourses;

import java.util.HashMap;
import java.util.Map;

public final class TimeMemory {
    private static Map<String,String> stringMap = new HashMap<>();

    private TimeMemory() {}

    public static HashMap<String,String> getInstance() {
        return (HashMap<String, String>) stringMap;
    }
    public static void setInstance(String a1, String a2) {
        stringMap.put(a1,a2);
    }
}

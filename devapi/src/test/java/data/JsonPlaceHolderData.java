package data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderData {

    public static Map<String, Object> expectedDatasetup() {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("id", 201);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);

        return expectedData;
    }

    public static Map<String, Object> expectedDataPut() {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 21);
        expectedData.put("title", "Wash the forks");
        expectedData.put("completed", false);

        return expectedData;
    }
}

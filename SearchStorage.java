package org.core.projects.searchengine;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchStorage {
    private static final String SOURCE = "src/main/java/org/core/projects/searchengine/searchData.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void save(Map<String, Integer> freqMap) {
        try {
            Search search = new Search(freqMap);
            Files.write(gson.toJson(search).getBytes(), new File(SOURCE));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save search data", e);
        }
    }

    public Map<String, Integer> load() {
        try (FileReader reader = new FileReader(SOURCE)) {
            Search search = gson.fromJson(reader, Search.class);
            return search != null ? search.getFreqMap() : new LinkedHashMap<>();
        } catch (Exception e) {
            return new LinkedHashMap<>();
        }
    }
}

package org.core.projects.searchengine;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class SearchMemory {
    private Map<String, Integer> freqMap = new LinkedHashMap<>();
    private static final int MIN_SEARCH_LENGTH = 2;

    public void setFreqMap(Map<String, Integer> freqMap) {
        this.freqMap = freqMap != null ? freqMap : new LinkedHashMap<>();
    }

    public void update(String search) {
        if (search.length() <= 1) return;
        freqMap.merge(search, 1, Integer::sum);
    }

    public void clean() {
        if (freqMap.size() > 50) {
            freqMap = freqMap.entrySet()
                    .stream()
                    .filter(e -> e.getValue() > MIN_SEARCH_LENGTH)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }

    public void clear() {
        freqMap.clear();
    }
}

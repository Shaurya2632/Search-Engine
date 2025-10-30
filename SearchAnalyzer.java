package org.core.projects.searchengine;

import org.apache.commons.text.similarity.CosineSimilarity;
import java.util.*;
import java.util.stream.Collectors;

public class SearchAnalyzer {
    private static final CosineSimilarity SIMILARITY = new CosineSimilarity();

    public List<String> analyze(String search, Map<String, Integer> freqMap) {
        if (freqMap.isEmpty() || search.isEmpty()) return Collections.emptyList();

        Map<String, Double> rank = new HashMap<>();

        for (String word : freqMap.keySet()) {
            double score = SIMILARITY.cosineSimilarity(freq(search), freq(word)) + freqMap.get(word);
            rank.put(word, score);
        }

        return rank.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Map<CharSequence, Integer> freq(String s) {
        Map<CharSequence, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.merge(String.valueOf(c), 1, Integer::sum);
        }
        return map;
    }
}

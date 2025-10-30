package org.core.projects.searchengine;

import java.util.List;

public class SearchEngine {
    private static final int MAX_RESULT = 10;
    private static final SearchStorage storage = new SearchStorage();
    private static final SearchMemory memory = new SearchMemory();
    private static final SearchAnalyzer analyzer = new SearchAnalyzer();

    static void main() {
        memory.setFreqMap(storage.load());

        while (true) {
            String search = IO.readln("Enter Search: ").trim().toLowerCase();
            memory.clean();

            if (search.equals("/")) break;
            if (search.equalsIgnoreCase("clear")) memory.clear();

            List<String> results = analyzer.analyze(search, memory.getFreqMap());

            showResults(results);
            memory.update(search);
            storage.save(memory.getFreqMap());
        }
    }

    private static void showResults(List<String> words) {
        if (words.isEmpty()) {
            IO.println("No results found.\n");
            return;
        }

        for (int i = 0; i < Math.min(MAX_RESULT, words.size()); i++) {
            IO.println((i + 1) + ". " + words.get(i));
        }
        IO.println();
    }
}

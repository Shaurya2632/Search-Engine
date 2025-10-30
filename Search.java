package org.core.projects.searchengine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Search {
    private Map<String, Integer> freqMap;
}

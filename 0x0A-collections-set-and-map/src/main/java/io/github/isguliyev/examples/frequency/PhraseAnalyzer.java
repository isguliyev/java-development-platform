package io.github.isguliyev.examples.frequency;

import java.util.Map;
import java.util.HashMap;

public class PhraseAnalyzer {
    public static Map<String, Integer> getWordFrequencies(String text) {
        final Map<String, Integer> frequency = new HashMap<String, Integer>();

        if (text == null || text.isEmpty()) {
            return frequency;
        }

        for (String word : text.toLowerCase().split("[\\s,.?!]+")) {
            if (word.isEmpty()) {
                continue;
            }

            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        return frequency;
    }
}
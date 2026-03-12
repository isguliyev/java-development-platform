package io.github.isguliyev.examples.frequency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

public class PhraseAnalyzerTest {
    @Test
    void getWordFrequencies_returnsWordFrequencyMap() {
        final String text = "I'm not gonna run away, I never go back on my word! That's my ninja way!";

        final Map<String, Integer> expectedFrequency = Map.ofEntries(
            Map.entry("i'm", 1),
            Map.entry("not", 1),
            Map.entry("gonna", 1),
            Map.entry("run", 1),
            Map.entry("away", 1),
            Map.entry("i", 1),
            Map.entry("never", 1),
            Map.entry("go", 1),
            Map.entry("back", 1),
            Map.entry("on", 1),
            Map.entry("my", 2),
            Map.entry("word", 1),
            Map.entry("that's", 1),
            Map.entry("ninja", 1),
            Map.entry("way", 1)
        );

        assertEquals(expectedFrequency, PhraseAnalyzer.getWordFrequencies(text));
    }
}
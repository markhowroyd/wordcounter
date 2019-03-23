package wordcount.counter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultWordFrequencyCounter implements WordFrequencyCounter {

    private Map<String, AtomicInteger> wordFrequency = new HashMap<>();

    @Override
    public void addWordOccurrence(String word) {
        wordFrequency
                .computeIfAbsent(normaliseCase(word), k -> new AtomicInteger())
                .incrementAndGet();
    }

    @Override
    public int getOccurrenceCount(String word) {
        AtomicInteger freq = wordFrequency.get(normaliseCase(word));
        return freq == null ? 0 : freq.intValue();
    }

    private String normaliseCase(String word) {
        return word.toLowerCase();
    }
}
package wordcount.counter;

/**
 * An accumulator for keeping a tally of word occurrences.
 */
public interface WordFrequencyCounter {

    void addWordOccurrence(String word);

    int getOccurrenceCount(String word);
}

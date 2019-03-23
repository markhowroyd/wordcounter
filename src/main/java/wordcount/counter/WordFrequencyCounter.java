package wordcount.counter;

public interface WordFrequencyCounter {

    void addWordOccurrence(String word);

    int getOccurrenceCount(String word);

}

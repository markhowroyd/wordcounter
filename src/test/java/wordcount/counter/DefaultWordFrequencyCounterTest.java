package wordcount.counter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultWordFrequencyCounterTest {

    private WordFrequencyCounter wordFrequencyCounter = new DefaultWordFrequencyCounter();

    @Test
    public void givenANewCounter_whenWordAdded_thenCountIsOne() {
        String word = "cat";

        wordFrequencyCounter.addWordOccurrence(word);

        assertThat(wordFrequencyCounter.getOccurrenceCount(word)).isEqualTo(1);
    }

    @Test
    public void givenANewCounter_whenWordAddedTwice_thenCountIsTwo() {
        String word = "cat";

        wordFrequencyCounter.addWordOccurrence(word);
        wordFrequencyCounter.addWordOccurrence(word);

        assertThat(wordFrequencyCounter.getOccurrenceCount(word)).isEqualTo(2);
    }

    @Test
    public void givenANewCounter_whenWordAddedTwiceInDifferingCase_thenCountIsTwo() {
        String word = "cat";

        wordFrequencyCounter.addWordOccurrence(word);
        wordFrequencyCounter.addWordOccurrence(word.toUpperCase());

        assertThat(wordFrequencyCounter.getOccurrenceCount(word)).isEqualTo(2);
    }

    @Test
    public void givenANewCounter_whenTwoDifferentWordsAreAdded_thenCountForEachWordIsOne() {
        String word1 = "cat";
        String word2 = "chatter";

        wordFrequencyCounter.addWordOccurrence(word1);
        wordFrequencyCounter.addWordOccurrence(word2);

        assertThat(wordFrequencyCounter.getOccurrenceCount(word1)).isEqualTo(1);
        assertThat(wordFrequencyCounter.getOccurrenceCount(word2)).isEqualTo(1);
    }
}
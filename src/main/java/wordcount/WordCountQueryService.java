package wordcount;

/**
 * A (read only) service providing the means to query for word frequencies.
 */
public interface WordCountQueryService {

    int getWordFrequency(String word);
}

package wordcount;

/**
 * A service to collect counts of word occurrences and make such word frequencies available for query.
 */
public interface WordCountService extends WordCountQueryService {

    void addWord(String word, String isoLanguageCode) throws WordCounterServiceException;
}

package wordcount;

public interface WordCountService {

    void addWord(String word, String isoLanguageCode) throws WordCounterServiceException;

    int getWordFrequency(String word);
}

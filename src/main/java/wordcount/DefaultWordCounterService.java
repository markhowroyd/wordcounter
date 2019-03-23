package wordcount;

import wordcount.counter.WordFrequencyCounter;
import wordcount.external.Translator;
import wordcount.validator.InvalidWordException;
import wordcount.validator.WordValidator;

import java.util.Locale;

public class DefaultWordCounterService implements WordCountService {

    private WordFrequencyCounter frequencyCounter;

    private WordValidator wordValidator;

    private Translator translator;

    public DefaultWordCounterService(WordFrequencyCounter frequencyCounter, WordValidator wordValidator, Translator translator) {
        this.frequencyCounter = frequencyCounter;
        this.wordValidator = wordValidator;
        this.translator = translator;
    }

    @Override
    public void addWord(String word, String isoLanguageCode) throws WordCounterServiceException {
        try {
            wordValidator.assertValid(word);
            String theWord = isoLanguageCode.equals(Locale.ENGLISH.getISO3Language()) ? word : translator.translate(word, isoLanguageCode);
            frequencyCounter.addWordOccurrence(theWord);

        } catch (InvalidWordException e) {
            throw new WordCounterServiceException("The word could not be added.", e);
        }
    }

    @Override
    public int getWordFrequency(String word) {
        return frequencyCounter.getOccurrenceCount(word);
    }
}

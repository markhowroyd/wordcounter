package wordcount;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wordcount.counter.WordFrequencyCounter;
import wordcount.external.Translator;
import wordcount.validator.InvalidWordException;
import wordcount.validator.WordValidator;

import java.util.Locale;
import static org.mockito.Mockito.*;

public class DefaultWordCounterServiceTest {

    @Mock
    private Translator translator;

    @Mock
    private WordFrequencyCounter frequencyCounter;

    @Mock
    private WordValidator validator;

    @InjectMocks
    private DefaultWordCounterService wordCounterService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenAnInitializedCounterService_whenEnglishWordIsAdded_thenResultIsCorrect() throws WordCounterServiceException, InvalidWordException {
        String word = "test";
        String language = Locale.ENGLISH.getISO3Language();

        wordCounterService.addWord(word, language);

        verify(validator).assertValid(word);
        verify(frequencyCounter).addWordOccurrence(word);
        verifyNoMoreInteractions(validator, translator, frequencyCounter);
    }

    @Test
    public void givenAnInitializedCounterService_whenForeignWordIsAdded_thenResultIsCorrect() throws InvalidWordException, WordCounterServiceException {
        String word = "test";
        String language = Locale.FRENCH.getISO3Language();

        String translatedWord = "translated";
        when(translator.translate(word, language)).thenReturn(translatedWord);

        wordCounterService.addWord(word, language);

        verify(validator).assertValid(word);
        verify(translator).translate(word,language);
        verify(frequencyCounter).addWordOccurrence(translatedWord);
        verifyNoMoreInteractions(validator, translator, frequencyCounter);
    }
}
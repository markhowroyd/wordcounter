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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
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
    public void givenANewCounterService_whenEnglishWordIsAdded_thenItSucceeds() throws InvalidWordException {
        String word = "test";
        String language = Locale.ENGLISH.getISO3Language();

        wordCounterService.addWord(word, language);

        verify(validator).assertValid(word);
        verify(frequencyCounter).addWordOccurrence(word);
        verifyNoMoreInteractions(validator, translator, frequencyCounter);
    }

    @Test
    public void givenANewCounterService_whenForeignWordIsAdded_thenItSucceeds() throws InvalidWordException {
        String word = "test";
        String language = Locale.FRENCH.getISO3Language();

        String translatedWord = "translated";
        when(translator.translate(word, language)).thenReturn(translatedWord);

        wordCounterService.addWord(word, language);

        verify(validator).assertValid(word);
        verify(translator).translate(word, language);
        verify(frequencyCounter).addWordOccurrence(translatedWord);
        verifyNoMoreInteractions(validator, translator, frequencyCounter);
    }

    @Test
    public void givenANewCounterService_whenWordIsBad_thenExceptionThrown() throws InvalidWordException {
        String word = "test";
        String language = Locale.FRENCH.getISO3Language();
        doThrow(new InvalidWordException("bad word")).when(validator).assertValid(word);

        try {
            wordCounterService.addWord(word, language);
            fail("Exception was expected");
        } catch (WordCounterServiceException e) {
            assertThat(e.getCause()).isInstanceOf(InvalidWordException.class);
        }

        verify(validator).assertValid(word);
        verifyNoMoreInteractions(validator, translator, frequencyCounter);
    }

    @Test
    public void givenANewCounterService_whenWordIsNull_thenItFailsFast() {
        try {
            wordCounterService.addWord(null, Locale.FRENCH.getISO3Language());
        } catch (NullPointerException e) {
            assertThat(e).isInstanceOf(NullPointerException.class).hasMessageContaining("The supplied word must not be null");
        }
    }

    @Test
    public void givenANewCounterService_whenLanguageIsNull_thenItFailsFast()  {
        try {
            wordCounterService.addWord("test", null);
        } catch (NullPointerException e) {
            assertThat(e).isInstanceOf(NullPointerException.class).hasMessageContaining("The supplied language must not be null");
        }
    }

    @Test
    public void givenANewCounterService_whenWordIsQueried_thenItSucceeds() {
        String word = "test";

        wordCounterService.getWordFrequency(word);

        verify(frequencyCounter).getOccurrenceCount(word);
        verifyNoMoreInteractions(validator, translator, frequencyCounter);
    }

    @Test
    public void givenANewCounterService_whenQueriedWordIsNull_thenItFailsFast() {
        try {
            wordCounterService.getWordFrequency(null);
        } catch (NullPointerException e) {
            assertThat(e).isInstanceOf(NullPointerException.class).hasMessageContaining("The supplied word must not be null");
        }
    }
}
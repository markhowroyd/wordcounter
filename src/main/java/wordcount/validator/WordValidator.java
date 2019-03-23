package wordcount.validator;

/**
 * A validator that determines the validity of an input word string.
 */
public interface WordValidator {

    void assertValid(String word) throws InvalidWordException;
}

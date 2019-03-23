package wordcount.validator;

public interface WordValidator {

    void assertValid(String word) throws InvalidWordException;
}

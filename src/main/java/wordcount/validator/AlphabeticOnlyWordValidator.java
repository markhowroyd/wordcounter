package wordcount.validator;

import java.util.regex.Pattern;

public class AlphabeticOnlyWordValidator implements WordValidator {

    private Pattern patternMatch = Pattern.compile("[a-zA-Z]+");

    @Override
    public void assertValid(String word) throws InvalidWordException {
        if (word == null || !patternMatch.matcher(word).matches()) {
            throw new InvalidWordException("The word " + word + " is not valid - it must consist of alphabetic characters");
        }
    }
}

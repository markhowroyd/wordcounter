package wordcount.validator;

import java.util.regex.Pattern;

public class AlphabeticOnlyWordValidator implements WordValidator {

    private Pattern patternMatch = Pattern.compile("\\w+");

    @Override
    public void assertValid(String word) throws InvalidWordException {
        if (word == null || word.isEmpty() || !word.chars().allMatch(Character::isLetter)) {
            throw new InvalidWordException("The word " + word + " is not valid - it must consist of alphabetic characters");
        }
    }
}

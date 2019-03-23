package wordcount.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DefaultWordValidatorTest {

    private WordValidator validator = new DefaultWordValidator();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "trade", true },
                { "TRADE", true },
                { "TrAdE", true },
                { "trade!", false },
                { "trad3", false },
                { "    ", false },
                { "", false },
                { null, false }
        });
    }

    @Parameterized.Parameter
    public String word;

    @Parameterized.Parameter(1)
    public boolean expectedValid;

    @Test
    public void testWordValidity() {
        try {
            validator.assertValid(word);
            if (!expectedValid)
                fail("Should throw for invalid word " + word);
        } catch (InvalidWordException e) {
            if (expectedValid)
                fail("Should not throw for valid word " + word);
        }
    }
}
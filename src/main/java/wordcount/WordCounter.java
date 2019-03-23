package wordcount;

import wordcount.counter.DefaultWordFrequencyCounter;
import wordcount.validator.DefaultWordValidator;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {

        WordCountService wordCountService = new DefaultWordCounterService(
                new DefaultWordFrequencyCounter(),
                new DefaultWordValidator(),
                (word, lang) -> word); // no translator available yet

       // just use English only for now, as no translation available
       String textLanguage = Locale.ENGLISH.getISO3Language();

       try (Scanner scanner = new Scanner(System.in)) {
           System.out.println("Please enter a word followed by RETURN:");
           String word = scanner.nextLine();

           while (!word.isEmpty()) {
               try {
                   wordCountService.addWord(word.trim(), textLanguage);

                   System.out.println(MessageFormat.format("There have been {0} occurrence(s) of word {1}",
                           wordCountService.getWordFrequency(word), word));

               } catch (WordCounterServiceException e) {
                   System.err.println(e.getMessage());
               }

               word = scanner.nextLine();
           }
       }
    }
}

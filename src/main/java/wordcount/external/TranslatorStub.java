package wordcount.external;

public class TranslatorStub implements Translator {
    @Override
    public String translate(String word, String isoLanguageCode) {
        // do nothing
        return word;
    }
}

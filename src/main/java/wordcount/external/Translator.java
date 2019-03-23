package wordcount.external;

/**
 * The externally provided translator component.
 *
 * Assumption: this must need an indication of language, or else ambiguities could arise.
 */
public interface Translator {

    String translate(String word, String isoLanguageCode);

}

package pl.put.poznan.transformer.logic;

import java.lang.Character;
import java.util.HashSet;
import java.util.Set;

/**
 * This class expands abbreviations to their full form
 *
 * @author Piotr Wojsznis
 * @version 1.0
 */
public class ExpandAbbreviation extends TextTransformer{

    /**
     * Constructor of text transformation class.
     * @param transInterface text to decorate
     */
    public ExpandAbbreviation (IText transInterface) {
        super(transInterface);
    }

    /**
     * Method performing the transformation
     * @param text given string by user
     * @return text after transformation
     */
    @Override
    public String transform(String text){

        return expandAbbreviation(this.transInterface.transform(text));
    }

    /**
     * This method expands abbreviations to their full form
     * @param text given string by user
     * @return text after transformation
     */
    private String expandAbbreviation(String text)
    {
        String[] abbreviations = {"prof.", "dr", "np.", "itd.", "itp.", "nr", "mgr"};
        String[] expansions = {"profesor", "doktor", "na przykład", "i tak dalej", "i tym podobne", "numer", "magister"};
        String[] expansionsWithDots = {"np.", "itd.", "itp."};
        Set<String> abbreviationsWithDots = new HashSet<String>();
        abbreviationsWithDots.add("prof.");
        abbreviationsWithDots.add("np.");
        abbreviationsWithDots.add("itd.");
        abbreviationsWithDots.add("itp.");
        String[] words = text.split(" ");
        StringBuilder newText = new StringBuilder();
        boolean comma = false;
        boolean dot = false;
        String lowerWord;


        for(int i = 0; i < words.length; i++) {
            if (words[i].endsWith(",")) {
                words[i] = words[i].substring(0, words[i].length() - 1);
                comma = true;
            }
            else if (words[i].endsWith(".") && !abbreviationsWithDots.contains(words[i].toLowerCase())) {
                words[i] = words[i].substring(0, words[i].length() - 1);
                dot = true;
            }
            lowerWord = words[i].toLowerCase();
            for (int j = 0; j < abbreviations.length; j++) {
                if (lowerWord.equals(abbreviations[j])) {
                    if (Character.isUpperCase(words[i].charAt(0))) {
                        words[i] = expansions[j].substring(0, 1).toUpperCase() + expansions[j].substring(1);
                    } else {
                        words[i] = expansions[j];
                    }
                    break;
                }
            }
            if (comma) {
                if (i == 0 || words[i].equals("")) newText.append(words[i]).append(",");
                else newText.append(" ").append(words[i]).append(",");
            } else if (dot) {
                if (i == 0 || words[i].equals("")) newText.append(words[i]).append(".");
                else newText.append(" ").append(words[i]).append(".");
            } else {
                if (i == 0 || words[i].equals("")) newText.append(words[i]);
                else newText.append(" ").append(words[i]);
            }
            comma = false;
            dot = false;
        }
        return newText.toString();
    }
}

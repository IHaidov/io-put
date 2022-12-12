package pl.put.poznan.transformer.logic;
import org.apache.commons.lang.WordUtils;

/**
 * Сlass capitalizing the first letter in each word
 *
 * @author Jan Kabzinski
 * @version 1.0
 */
public class Capitalize extends TextTransformer{
    /**
     *Constructor of text transformation class.
     *@param transInterface text to decorate
     */
    public Capitalize(IText transInterface) {
        super(transInterface);
    }

    /**
     * Method performing the transformation
     * @param text given string by user
     * @return text after transformation
     */
    @Override
    public String transform(String text){
        return capitalize(this.transInterface.transform(text));
    }

    /**
     * Method capitalizing the first letter in each sentence
     * @param text given string by user
     * @return text after transformation
     */
    private String capitalize(String text)
    {
        return WordUtils.capitalizeFully(text);
    }

}

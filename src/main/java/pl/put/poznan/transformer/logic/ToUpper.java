package pl.put.poznan.transformer.logic;

/**
 * Class converting given text to UPPERCASE
 *
 * @author Jan Krenz
 * @version 1.0
 */
public class ToUpper extends TextTransformer{
     /**
     * Constructor of text transformation class.
     *
     * @param transInterface text to decorate
     */
    public ToUpper(IText transInterface) {
        super(transInterface);
    }
    /**
     * Method performing the transformation
     *
     * @return text after transformation
     */
    @Override
    public String transform(String text){
        return toUpper(this.transInterface.transform(text));
    }
    /**
     * Method converting given text to UPPERCASE
     *
     * @param text given string by user
     * @return text after transformation
     */
    private String toUpper(String text)
    {
        return text.toUpperCase();
    }
}

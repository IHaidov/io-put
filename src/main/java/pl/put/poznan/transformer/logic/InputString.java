package pl.put.poznan.transformer.logic;

/**
 * Class to give input string on which transformations will be performed
 */
public class InputString implements IText{
    /**
     *
     * @param text given string by user
     * @return text
     */
    @Override
    public String transform(String text){
        return text;
    }
}

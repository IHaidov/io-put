package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public abstract class TextTransformer implements IText {

    protected IText transInterface;
    public TextTransformer(IText transInterface) {
        this.transInterface = transInterface;
    }

    @Override
    public String transform(String text) {
        return transInterface.transform(text);
    }

}

package pl.put.poznan.transformer.logic;

public class UpperCase extends TextTransformer{
    private final TextTransformer trans;

    public UpperCase(TextTransformer trans){
        this.trans = trans;
    }

    @Override
    public String transform()
    {
        return trans.transform().toUpperCase();
    }
}

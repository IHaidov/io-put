package pl.put.poznan.transformer.logic;

public class Capitalize extends TextTransformer{
    private final TextTransformer trans;

    public Capitalize(TextTransformer trans){
        this.trans = trans;
    }

    @Override
    public String transform()
    {
        String text = trans.transform();
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}

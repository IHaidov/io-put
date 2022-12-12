package pl.put.poznan.transformer.json_format;

import java.util.List;

public class transInfo {
    private String text;
    private List<String> transforms;

    public String getText()
    {
        return text;
    }
    public String[] getTransforms()
    {
        return transforms.toArray(new String[0]);
    }
}
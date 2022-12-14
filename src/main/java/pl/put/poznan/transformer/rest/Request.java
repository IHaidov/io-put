package pl.put.poznan.transformer.rest;

public class Request {
    private final String text;
    private  final String[] transforms;

    public Request(String text, String[] transforms){
        this.text = text;
        this.transforms = transforms;
    }

    public String getText(){
        return this.text;
    }

    public String[] getTransforms(){
        return this.transforms;
    }

}

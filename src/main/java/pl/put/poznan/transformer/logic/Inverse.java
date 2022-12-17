package pl.put.poznan.transformer.logic;

public class Inverse extends TextTransformer {
    private final TextTransformer trans;

    public Inverse(TextTransformer trans){
        this.trans = trans;
    }

    @Override
    public String transform() {

        String reversed;
        String text = trans.transform();
        Boolean[] upper = new Boolean[text.length()];

        for (int j = 0; j < text.length(); j++) {
            upper[j] = text.charAt(j) > 'A' && text.charAt(j) < 'Z';
        }

        reversed = "";
        for (int j = 0; j < text.length(); j++) {
            if (!upper[j]) {
                reversed += Character.toString(text.charAt(text.length() - j - 1)).toLowerCase();
            } else {
                reversed += Character.toString(text.charAt(text.length() - j - 1)).toUpperCase();
            }
        }
        return reversed;
    }
}

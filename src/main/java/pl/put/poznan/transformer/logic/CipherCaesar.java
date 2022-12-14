package pl.put.poznan.transformer.logic;

/**
 * Klasa odpowiedzialna za szyfrowanie tekstu szyfrem cezara o kroku 3.
 * Klasa posiada 2 atrybuty:
 *      - trans, przchowuje nadrzednego dekoratora.
 *      - Alphabet, przechowuje tablicę znaków alfabetu od A do Z oraz cyfry od 0 do 9.
 *
 * @author Jakub Furs
 * @version 1.0
 */

public class CipherCaesar extends TextTransformer{
    private final TextTransformer trans;
    private final char[] Alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};

    /**
     * Konstruktor klasy.
     *
     * @param trans - nadrzędny dekorator
     */

    public CipherCaesar(TextTransformer trans){this.trans = trans;}

    /**
     * Metoda odpowiedzialna za transformację obiektu.
     *
     * @return tekst zaszyfrowany szyfrem cezara
     */

    @Override
    public String transform() {
        String text = trans.transform().toUpperCase();
        return cipher(text, 3);
    }

    private String cipher(String text, int rot)
    {
        String output = "";
        for(int i=0;i<text.length();i++)
        {
            char sign = text.charAt(i);
            int idx = SignAt(sign);
            if(idx == -1) output += sign;
            else output += Alphabet[(idx + rot) % Alphabet.length];
        }
        return output;
    }

    private int SignAt(char sign)
    {
        for(int i=0;i<Alphabet.length;i++)
            if(sign == Alphabet[i]) return i;
        return -1;
    }
}

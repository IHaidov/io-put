package pl.put.poznan.transformer.logic;
import java.util.StringTokenizer;

/**
 * Klasa jest odpowiedzialna za funkjonalnosc usuniecia powtarzajacych sie wyrazow
 *
 * @author Ivan Haidov
 * @version 1.0
 */


public class RepetitionsRemove extends TextTransformer{
    private final TextTransformer trans;

    /**
     * Konstruktor klasy
     *
     * @param trans - nadrzedny dekorator
     */

    public RepetitionsRemove(TextTransformer trans){
        this.trans = trans;
    }

    /**
     * metoda odpowiedzialna za transformacje obiektu
     *
     * @return tekst po zamianie lister na male
     */

    @Override
    public String transform()
    {
        StringBuilder out = new StringBuilder();
        StringTokenizer st = new StringTokenizer(this.trans.transform());

        String last = "";
        String current = "";

        while(st.hasMoreTokens()){
            current = st.nextToken();
            if(! current.equals(last)){
                if(!last.equals("")){
                    out.append(" ");
                }
                out.append(current);
                last = current;
            }
        }


        return out.toString();
    }
}


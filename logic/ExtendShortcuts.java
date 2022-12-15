package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * klasa odpowiedzialna za rozwijanie predefiniowanych skrótów z zachowaniem wielkośći liter
 * klasa ta posiada dwa atrybuty - text, przechowujący transformowaną informację oraz mapę skrótów i ich rozwinięć
 *
 * @author Piotr Wojsznis
 * @version 1.0
 */

public class ExtendShortcuts extends TextTransformer {
    private final TextTransformer trans;
    private final Map<String, String> shortcuts;

    /**
     * Konstruktor klasy
     *
     * @param trans - nadrzędny dekorator
     */

    public ExtendShortcuts(TextTransformer trans){
        this.trans = trans;
        this.shortcuts = new HashMap<>();
        genMap();
    }

    /**
     * metoda odpowiedzialna za transformację obiektu
     *
     * @return tekst po rozwinięciu skrótów
     */

    public String transform(){
        String[] splited = trans.transform().split(" ");
        StringBuilder result = new StringBuilder();
        for(int i = 0; splited.length > i; i++){
                result.append(subTransform(splited[i]));
                if(i == splited.length - 1) break;
                result.append(" ");
        }
        return result.toString();
    }

    private String subTransform(String t){
        String result;
        Boolean[] cap = getCapitals(t);
        String temp = t.toLowerCase();

        if(shortcuts.containsKey(temp)) temp = shortcuts.get(temp);
        else return t;

        char[] res = temp.toCharArray();
        for(int i = 0; i < cap.length; i++){
            if(cap[i]) res[i] -= 'a' - 'A';
        }
        result = String.valueOf(res);
        return result;
    }

    private Boolean[] getCapitals(String text){
        Boolean[] capitals = new Boolean[text.length()];
        for(int i = 0; i < text.length(); i++){
            capitals[i] = text.charAt(i) <= 'Z' && text.charAt(i) >= 'A';
        }
        return capitals;
    }

    private void genMap(){
        shortcuts.put("dr", "doktor");
        shortcuts.put("prof.", "profesor");
        shortcuts.put("inż.", "inżynier");
        shortcuts.put("mgr", "magister");
    }
}

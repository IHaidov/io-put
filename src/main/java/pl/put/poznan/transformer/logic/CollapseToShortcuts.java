package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * klasa odpowiedzialna za zwijanie predefiniowanych skrótów z zachowaniem wielkośći liter
 * klasa ta posiada dwa atrybuty - text, przechowujący transformowaną informację oraz mapę rozwinięć i ich skrótów
 *
 * @author Ivan Haidov
 * @version 1.1
 */


public class CollapseToShortcuts  extends TextTransformer{
    private final TextTransformer trans;
    private final Map<String, String> shortcuts;

    /**
     * Konstruktor klasy
     *
     * @param trans - nadrzędny dekorator
     */

    public CollapseToShortcuts(TextTransformer trans){
        this.trans = trans;
        this.shortcuts = new HashMap<>();
        genMap();
    }

    /**
     * metoda odpowiedzialna za transformację obiektu
     *
     * @return tekst po zwinięciu do skrótu
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
        for(int i = 0; i < temp.length(); i++){
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
        shortcuts.put("doktor", "dr");
        shortcuts.put("profesor", "prof.");
        shortcuts.put("inżynier","inż.");
        shortcuts.put("magister", "mgr");
    }
}

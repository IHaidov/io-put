package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * Klasa jest odpowiedzialna za utworzenie nowych wyrazów przy użyciu losowej permutacji
 *
 * @author Jakub Furs
 * @version 1.0
 */

public class Permutations extends TextTransformer {

    private final TextTransformer trans;

    /**
     * Konstruktor klasy
     *
     * @param trans - nadrzedny dekorator
     */

    public Permutations(TextTransformer trans) {
        this.trans = trans;
    }


    /**
     * metoda odpowiedzialna za zmienienie wyrazow poprzez losowa permutacje
     *
     * @return tekst po zamianie wyrazow
     */

    @Override
    public String transform() {
        String text = this.trans.transform();
        StringBuilder result = new StringBuilder();
        StringBuilder pom;
        HashMap<Integer, Character> non_ascii_signs = new HashMap<>();
        ArrayList<Integer> num_list = new ArrayList<>();

        // saving positions of signs that shouldn't be permutated
        for(int i = 0; i < text.length(); i++){
            // if it is non-alphanumeric or non acscii then save position
            if(!(
                    (text.charAt(i) >= 'a' &&  text.charAt(i) <= 'z' )
                    || (text.charAt(i) >= 'A' &&  text.charAt(i) <= 'Z')
                    || (text.charAt(i) >= '0' && text.charAt(i) <= '9')
                    || text.charAt(i) >= 127
                )
            ){
                non_ascii_signs.put(i, text.charAt(i));
            }
        }
        //System.out.println(non_ascii_signs);

        int result_index = 0;
        String[] devidedText = text.split("[^a-zA-Z0-9]+");
        for(String el : devidedText){
            num_list.clear();
            pom = new StringBuilder();

            for (int i = 0; i < el.length(); i++) {
                num_list.add(i);
            }

            Collections.shuffle(num_list);

            for (int j = 0; j < el.length(); j++) {
                pom.append(el.charAt(num_list.get(j)));
            }


            result.append(pom);
        }

        StringBuilder ans = new StringBuilder();
        int non_ascii_signs_counter = 0;
        for(int i = 0; i < text.length(); i++){
            if(non_ascii_signs.containsKey(i)){
                ans.append(non_ascii_signs.get(i));
                non_ascii_signs_counter += 1;
            }
            else{
                ans.append(result.charAt(i - non_ascii_signs_counter));
            }
        }

        return ans.toString();
    }
}


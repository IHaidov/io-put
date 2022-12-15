package pl.put.poznan.transformer.logic;

/**
 * Klasa odpoiedzialna za zamiane liczb w zapisie tekstowym na zapis liczbowy, zamiana działa dla liczb między 0 a 1000000.
 * Klasa posiada 3 atrybuty:
 *      - trans, przchowuje nadrzednego dekoratora.
 *      - NumberTextPL, tablica String-ow zawierajaca slowne zapisy skladowych liczb.
 *      - output, przechowuje wynik transformacji
 *
 * @author Piotr Wojsznis
 * @version 1.1
 */

public class TextToNumber extends TextTransformer{
    private final TextTransformer trans;

    private final String[] NumberTextPL = { "zero", "jeden", "dwa", "trzy", "cztery", "piec", "szesc", "siedem", "osiem", "dziewiec",
                                            "dziesiec", "jedenascie", "dwanascie", "trzynascie", "czternascie", "pietnascie", "szesnascie", "siedemnascie", "osiemnascie", "dziewietnascie",
                                            "dwadziescia", "trzydziesci", "czterdziesci", "piecdziesiat", "szescdziesiat", "sziedemdziesiat", "osiemdziesiat", "dziewiecdziesiat",
                                            "sto", "dwiescie", "trzysta", "czterysta", "piecset", "szescset", "siedemset", "osiemset", "dziewiecset",
                                            "tysiac", "tysiace", "tysiecy"};
    //0[0] 1[1] 2[2] 3[3] 4[4] 5[5] 6[6] 7[7] 8[8] 9[9] 10[10] 11[11] 12[12] 13[13] 14[14] 15[15] 16[16] 17[17] 18[18] 19[19] 20[20] 30[21] 40[22] 50[23] 60[24] 70[25] 80[26] 90[27]
    //100[28] 200[29] 300[30] 400[31] 500[32] 600[33] 700[34] 800[35] 900[36]
    //1000[37] 2000-4000[38] 5000-999999[39]

    private String output;

    /**
     * Konstruktor klasy.
     *
     * @param trans - nadrzedny dekorator
     */

    public TextToNumber(TextTransformer trans)
        {
            this.trans = trans;
        }

    /**
     * metoda odpowiedzialna za transformacje obiektu
     *
     * @return tekst po zamianie tekstu na liczby
     */

    public String transform()
    {
            boolean oneNumber = false;
            output = "";
            String[] splitted = trans.transform().split("\\s+");
            int Number = 0;
            int level = 0;
            for(int j = 0; j < splitted.length; j++)
            {
                int Index = getIndex(splitted[j].toLowerCase());
                if (Index != -1) {
                    if (!oneNumber)
                    {
                        oneNumber = true;
                        Number = 0;
                        level = levelCheck(Index, 10); // entry
                        Number = assignValueToNumber(Number, Index);
                    }
                    else
                    {
                        int newLevel = levelCheck(Index, level);
                        if(newLevel == 0)
                        {
                            output += String.valueOf(Number) + " ";

                            Number = 0;
                            level = levelCheck(Index, 10); // entry
                        }
                        else
                        {
                            level = newLevel;
                        }
                        Number = assignValueToNumber(Number, Index);
                    }
                }
                else
                {
                    if(oneNumber)
                    {
                        oneNumber = false;
                        output += String.valueOf(Number) + " ";
                    }
                    output += splitted[j] + " ";
                }
            }
            if(oneNumber)
            {
                output += String.valueOf(Number);
            }

        return output;
    }

    int levelCheck(int Index, int Level)
    {
        if (Index >= 0 && Index < 10 && ((Level > 2 && Level < 6) || (Level >= 8 && Level <= 10)))        //1     6    nie dla: 1,2,6,7,
            if(Level > 5) return 6;
            else return 1;
        if (Index >= 10 && Index < 20 && (Level == 4 || Level == 5 || Level == 9 || Level == 10))         //2     7    nie dla: 1,2,3,6,7,8
            if(Level > 5) return 7;
            else return 2;
        if (Index >= 20 && Index < 28 && (Level == 4 || Level == 5 || Level == 9 || Level == 10))         //3     8    nie dla: 1,2,3,6,7,8
            if(Level > 5) return 8;
            else return 3;
        if (Index >= 28 && Index < 37 && Level%5 == 0)                                                    //4     9    nie dla: 1,2,3,4,6,7,8,9
            if(Level > 5) return 9;
            else return 4;
        if (Index >= 37 && Level > 5) return 5;                                                           //5          nie dla: 1,2,3,4

        return 0;
    }

    int assignValueToNumber(int Number, int Index)
    {
        if (Index >= 0 && Index < 20) return Number + Index;
        if (Index >= 20 && Index < 28) return Number + 10 * (Index - 18);
        if (Index >= 28 && Index < 37) return Number + 100 * (Index - 27);
        if (Index >= 37) return Number * 1000;
        return -1;
    }

    int getIndex(String partOfNumber)
    {
        for(int i = 0; i < NumberTextPL.length; i++)
        {
            if (partOfNumber.equals(NumberTextPL[i])) return i;
        }
        return -1;
    }
}

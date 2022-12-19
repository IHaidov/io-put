package pl.put.poznan.transformer.logic;

/**
 * Klasa odpowiedzialna za zamiane liczb na tekst, zamiana dziala dla liczb miedzy 0 a 1000000.
 * Klasa posiada 3 atrybuty:
 *      - trans, przchowuje nadrzednego dekoratora.
 *      - NumberTextPL, tablica String-ow zawierajaca slowne zapisy skladowych liczb.
 *      - output, przechowuje wynik transformacji
 *
 * @author Piotr Wojsnis
 * @version 1.1
 */

public class NumberToText extends TextTransformer{
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

    public NumberToText(TextTransformer trans)
    {
        this.trans = trans;
    }

    /**
     * metoda odpowiedzialna za transformacje obiektu
     *
     * @return tekst po zamianie liczb na tekst
     */

    public String transform()
    {
        int[][] Numbers = GetNumbers();
        String[] NumStr = new String[Numbers.length];
        for(int i = 0; i < Numbers.length; i++) {
            int Number = extractFromString(trans.transform(), Numbers[i][0], Numbers[i][1]);

            if (Number == 0) NumStr[i] = NumberTextPL[0];
            else {
                NumStr[i] = TransformNumberToText(Number);
                NumStr[i] = NumStr[i].trim();
            }
        }
        Numbers = CorrectNumbers(Numbers, NumStr);
        for(int i = 0; i < Numbers.length; i++)
            output = InsertString(output, NumStr[i], Numbers[i][0]);
        return output;
    }

    private int[][] CorrectNumbers(int[][] Numbers, String[] NumStr)
    {
        for(int i = 0; i < Numbers.length; i++)
        {
            for(int j = i + 1; j < Numbers.length; j++)
            {
                int length = NumStr[i].length();
                Numbers[j][0] += length - Numbers[i][1];
            }
        }
        return Numbers;
    }

    private String InsertString(String Original, String Insertee, int at)
    {
        String NewString = "";
        for(int i = 0; i < Original.length(); i++)
        {
            if(i == at) NewString += Insertee;

            NewString += Original.charAt(i);
        }
        if (NewString.equals(Original)) NewString += Insertee;
        return NewString;
    }

    private String TransformNumberToText(int Number)
    {
        if (Number > 0 && Number < 20) return " " + NumberTextPL[Number];
        if (Number >= 20 && Number < 100) return " " + NumberTextPL[18 + Number/10] + TransformNumberToText(Number%10);
        if (Number >= 100 && Number < 1000) return " " + NumberTextPL[27 + Number/100] + TransformNumberToText(Number%100);
        if (Number >= 1000 && Number < 2000) return " " + NumberTextPL[37] + TransformNumberToText(Number%1000);
        if ((Number >= 2000 && Number < 1000000) && (Number%10000 >= 2000 && Number%10000 < 5000) && !(Number%100000 >= 11000 && Number < 15000))
            return TransformNumberToText(Number/1000) + " " + NumberTextPL[38] + TransformNumberToText(Number%1000);
        if (Number >= 5000 && Number < 1000000) return TransformNumberToText(Number/1000) + " " + NumberTextPL[39] + TransformNumberToText(Number%1000);
        return "";
    }

    private int extractFromString(String ContainingNumber, int begin, int end)
    {
        int Number = 0;
        int counter = 0;
        for(int i = begin + end - 1; i >= begin; i--)
        {
            Number += ((int)(ContainingNumber.charAt(i) - '0')) * (Math.pow(10,counter));
            counter++;
        }
        return Number;
    }

    private int[][] GetNumbers()
    {
        String text = trans.transform();
        int counter = 0;
        int[][] NumbersSpecs = new int[0][2];
        int[] NumSpecs = {0, 0};
        boolean Digit = false;
        output = "";
        for(int j = 0; j < text.length(); j++)
        {
            char Char = text.charAt(j);
            if(Char >= '0' && Char <= '9')
            {
                if(!Digit)
                {
                    Digit = true;
                    NumSpecs[0] = j;
                }
            }
            else
            {
                if(Digit) {
                    Digit = false;
                    NumSpecs[1] = j - NumSpecs[0];
                    NumbersSpecs = extendArray(NumbersSpecs);
                    NumbersSpecs[counter] = NumSpecs;
                    counter++;
                    NumSpecs = new int[] {0, 0};
                }
                output += Char;
            }
        }
        if(Digit)
        {
            NumSpecs[1] = text.length() - NumSpecs[0];
            NumbersSpecs = extendArray(NumbersSpecs);
            NumbersSpecs[counter] = NumSpecs;
            counter++;
        }
        return NumbersSpecs;
    }

    private int[][] extendArray(int[][] OldArray)
    {
        int[][] NewArray = new int[OldArray.length + 1][2];
        System.arraycopy(OldArray, 0, NewArray, 0, OldArray.length);

        return NewArray;
    }

}

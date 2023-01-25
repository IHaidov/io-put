package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CapitalizeTest {
    TextTransformer mock = null;
    @BeforeEach
    void  setUp(){
        mock = mock(TextTransformer.class);
    }

    @Test
    void testTransform() {
       when(mock.transform()).thenReturn("ja widziałem, tak wielu trzymało się za ręce");
       Capitalize trans = new Capitalize(mock);
       assertEquals("Ja widziałem, tak wielu trzymało się za ręce", trans.transform());
    }

    @Test
    void testTransform2() {
        when(mock.transform()).thenReturn("Mówią, jeśli się zgodzimy, Ty możesz zostać tu");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Mówią, jeśli się zgodzimy, Ty możesz zostać tu", trans.transform());
    }
    @Test
    void testTransform3() {
        when(mock.transform()).thenReturn("tylko Jeden z nas może tu zostać, to nie Będziesz Ty");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Tylko Jeden z nas może tu zostać, to nie Będziesz Ty", trans.transform());
    }

    @Test
    void testTransform4() {
        when(mock.transform()).thenReturn("A ja nie chcę stąd odchodzić, nie chcę Smucić się");
        Capitalize trans = new Capitalize(mock);
        assertEquals("A ja nie chcę stąd odchodzić, nie chcę Smucić się", trans.transform());
    }
    @Test
    void testTransform5() {
        when(mock.transform()).thenReturn("ja widziałem, tak wielu trzymało się za ręce");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Ja widziałem, tak wielu trzymało się za ręce", trans.transform());
    }
    @Test
    void testTransform6() {
        when(mock.transform()).thenReturn("ja lubię lato, bo nie mam szkoły");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Ja lubię lato, bo nie mam szkoły", trans.transform());
    }
    @Test
    void testTransform7() {
        when(mock.transform()).thenReturn("zamyka gniazdo do przesyłania danych\n");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Zamyka gniazdo do przesyłania danych\n", trans.transform());
    }
    @Test
    void testTransform8() {
        when(mock.transform()).thenReturn("zapisuje dane do gniazda");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Zapisuje dane do gniazda", trans.transform());
    }
    @Test
    void testTransform9() {
        when(mock.transform()).thenReturn("wysyła dane do gniazda i adresu zdalnego");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Wysyła dane do gniazda i adresu zdalnego", trans.transform());
    }
    @Test
    void testTransform10() {
        when(mock.transform()).thenReturn("konwertuje adres IPv4 z postaci tekstowej na postać binarną");
        Capitalize trans = new Capitalize(mock);
        assertEquals("Konwertuje adres IPv4 z postaci tekstowej na postać binarną", trans.transform());
    }
}
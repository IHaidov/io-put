package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpperCaseTest {
    TextTransformer mock = null;
    @BeforeEach
    void  setUp(){
        mock = mock(TextTransformer.class);
    }

    @Test
    void testTransform() {
       when(mock.transform()).thenReturn("W pokoju panny janki, drżą szyby i firanki, jak w szale, tam stale gramofon gra");
       UpperCase trans = new UpperCase(mock);
       assertEquals("W POKOJU PANNY JANKI, DRŻĄ SZYBY I FIRANKI, JAK W SZALE, TAM STALE GRAMOFON GRA", trans.transform());
    }

    @Test
    void testTransform2() {
        when(mock.transform()).thenReturn("albowiem panna Janka jest gramofona manka, od ranka do ranka wciąż gra");
        UpperCase trans = new UpperCase(mock);
        assertEquals("ALBOWIEM PANNA JANKA JEST GRAMOFONA MANKA, OD RANKA DO RANKA WCIĄŻ GRA", trans.transform());
    }

    @Test
    void testTransform3() {
        when(mock.transform()).thenReturn("syreny, płyty wciąż kupuje panna Janka,");
        UpperCase trans = new UpperCase(mock);
        assertEquals("SYRENY, PŁYTY WCIĄŻ KUPUJE PANNA JANKA,", trans.transform());
    }

    @Test
    void testTransform4() {
        when(mock.transform()).thenReturn("pieniądze wszystkie w to pakuje i tak śpiewa wciąż");
        UpperCase trans = new UpperCase(mock);
        assertEquals("PIENIĄDZE WSZYSTKIE W TO PAKUJE I TAK ŚPIEWA WCIĄŻ", trans.transform());
    }
}
package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LowerCaseTest {
    TextTransformer mock = null;
    @BeforeEach
    void  setUp(){
        mock = mock(TextTransformer.class);
    }

    @Test
    void testTransform() {
       when(mock.transform()).thenReturn("Więc nie bój się deszczu");
       LowerCase trans = new LowerCase(mock);
       assertEquals("więc nie bój się deszczu", trans.transform());
    }

    @Test
    void testTransform2() {
        when(mock.transform()).thenReturn("Bo ja jestem deszczem -05");
        LowerCase trans = new LowerCase(mock);
        assertEquals("bo ja jestem deszczem -05", trans.transform());
    }
    @Test
    void testTransform3() {
        when(mock.transform()).thenReturn("Co pada na Twoje włosy 9-9");
        LowerCase trans = new LowerCase(mock);
        assertEquals("co pada na twoje włosy 9-9", trans.transform());
    }

    @Test
    void testTransform4() {
        when(mock.transform()).thenReturn("I na Twoje ręce ^^");
        LowerCase trans = new LowerCase(mock);
        assertEquals("i na twoje ręce ^^", trans.transform());
    }
}
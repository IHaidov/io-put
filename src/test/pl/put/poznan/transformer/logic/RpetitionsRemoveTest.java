package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RpetitionsRemoveTest {
    TextTransformer mock = null;
    @BeforeEach
    void  setUp(){
        mock = mock(TextTransformer.class);
    }

    @Test
    void testTransform() {
       when(mock.transform()).thenReturn("Jak nie nie wiadomo o co co chodzi");
       RepetitionsRemove trans = new RepetitionsRemove(mock);
       assertEquals("Jak nie wiadomo o co chodzi", trans.transform());
    }

    @Test
    void testTransform2() {
        when(mock.transform()).thenReturn("To chodzi chodzi o o o pieniądze");
        RepetitionsRemove trans = new RepetitionsRemove(mock);
        assertEquals("To chodzi o pieniądze", trans.transform());
    }
    @Test
    void testTransform3() {
        when(mock.transform()).thenReturn("Jest Jest 0 0 0 0 do do 11 11");
        RepetitionsRemove trans = new RepetitionsRemove(mock);
        assertEquals("Jest 0 do 11", trans.transform());
    }

    @Test
    void testTransform4() {
        when(mock.transform()).thenReturn("Hello V V 0 - - There there");
        RepetitionsRemove trans = new RepetitionsRemove(mock);
        assertEquals("Hello V 0 - There there", trans.transform());
    }
}
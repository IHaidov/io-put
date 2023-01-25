package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignsInLatexTest {
    TextTransformer mock = null;
    @BeforeEach
    void  setUp(){
        mock = mock(TextTransformer.class);
    }

    @Test
    void testTransform() {
       when(mock.transform()).thenReturn("Mgr Piotr & Pan Paweł");
       SignsInLatex trans = new SignsInLatex(mock);
       assertEquals("Mgr Piotr \\& Pan Paweł", trans.transform());
    }

    @Test
    void testTransform2() {
        when(mock.transform()).thenReturn("23 _ 6");
        SignsInLatex trans = new SignsInLatex(mock);
        assertEquals("23 \\_ 6", trans.transform());
    }

}
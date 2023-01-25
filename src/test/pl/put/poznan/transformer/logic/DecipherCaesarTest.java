package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DecipherCaesarTest {

    @Test
    void transform() {
        TextTransformer mock = mock(TextTransformer.class);
        when(mock.transform()).thenReturn("WHVW V21IUX FH2DDDDUD 546A (^-^)");
        DecipherCaesar test = new DecipherCaesar(mock);
        assertEquals("TEST SZYFRU CEZAAAARA 2137 (^-^)",test.transform());
    }
}
package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CipherCaesarTest {

    @Test
    void transform() {
        TextTransformer mock = mock(TextTransformer.class);
        when(mock.transform()).thenReturn("Test Szyfru cezaaaara 2137 (^-^)");
        CipherCaesar test = new CipherCaesar(mock);
        assertEquals("WHVW V21IUX FH2DDDDUD 546A (^-^)",test.transform());
    }
}
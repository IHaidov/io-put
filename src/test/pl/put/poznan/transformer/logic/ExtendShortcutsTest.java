package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExtendShortcutsTest {
    TextTransformer mock = null;
    @BeforeEach
    void  setUp(){
        mock = mock(TextTransformer.class);
    }

    @Test
    void testTransform() {
        when(mock.transform()).thenReturn("MGr Inż. dR powiedzial.");
        ExtendShortcuts trans = new ExtendShortcuts(mock);
        assertEquals("MAgister Inżynier dOktor powiedzial.", trans.transform());
    }

    @Test
    void testTransform1() {
        when(mock.transform()).thenReturn("MgR chodzi noca po klubach");
        ExtendShortcuts trans = new ExtendShortcuts(mock);
        assertEquals("MaGister chodzi noca po klubach", trans.transform());
    }

    @Test
    void testTransformAdvanced() {
        when(mock.transform()).thenReturn("MGr DR habilitowany lubi oblewac studentow");
        CollapseToShortcuts collapser = new CollapseToShortcuts(mock);
        ExtendShortcuts trans = new ExtendShortcuts(collapser);
        assertEquals("MAgister DOktor habilitowany lubi oblewac studentow", trans.transform());
    }

    @Test
    void testTransformAdvanced2() {
        when(mock.transform()).thenReturn("Magister profesor habilitowany lubi oblewac studentow");
        CollapseToShortcuts collapser = new CollapseToShortcuts(mock);
        ExtendShortcuts trans = new ExtendShortcuts(collapser);
        assertEquals("Magister profesor habilitowany lubi oblewac studentow", trans.transform());
    }
}
package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CollapseToShortcutsTest {
    TextTransformer mock = null;
    @BeforeEach
    void  setUp(){
        mock = mock(TextTransformer.class);
    }

    @Test
    void testTransform() {
       when(mock.transform()).thenReturn("MAgiSter Inżynier dOktor powiedzial.");
       CollapseToShortcuts trans = new CollapseToShortcuts(mock);
       assertEquals("MGr Inż. dR powiedzial.", trans.transform());
    }

    @Test
    void testTransform1() {
        when(mock.transform()).thenReturn("MaGister chodzi noca po klubach");
        CollapseToShortcuts trans = new CollapseToShortcuts(mock);
        assertEquals("MgR chodzi noca po klubach", trans.transform());
    }

    @Test
    void testTransformAdvanced() {
        when(mock.transform()).thenReturn("MGr DR habilitowany lubi oblewac studentow");
        ExtendShortcuts extender = new ExtendShortcuts(mock);
        CollapseToShortcuts trans = new CollapseToShortcuts(extender);
        assertEquals("MGr DR habilitowany lubi oblewac studentow", trans.transform());
    }

    @Test
    void testTransformAdvanced2() {
        when(mock.transform()).thenReturn("Magister profesor habilitowany lubi oblewac studentow");
        ExtendShortcuts extender = new ExtendShortcuts(mock);
        CollapseToShortcuts trans = new CollapseToShortcuts(extender);
        assertEquals("Mgr prof. habilitowany lubi oblewac studentow", trans.transform());
    }
}
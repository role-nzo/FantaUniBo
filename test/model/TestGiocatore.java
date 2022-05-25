package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import beans.*;

public class TestGiocatore {
    
    private Giocatore giocatore;

    @Before
    public void SetUp(){
        giocatore = new Giocatore("pietro.ruffinelli@studio.unibo.it");

    }

    @Test
    public void testGetter(){
        assertEquals("pietro.ruffinelli@studio.unibo.it", giocatore.getEmail());
    }

    @Test
    public void testSetterOK(){
        giocatore.setEmail("pippo.pluto@studio.unibo.it");
        assertEquals("pippo.pluto@studio.unibo.it", giocatore.getEmail());
    }

    @Test
    public void testSetterKO(){
        assertThrows( IllegalArgumentException.class, () -> { giocatore.setEmail("ciccio.pippo@gmail.com"); });
        assertThrows( IllegalArgumentException.class, () -> { new Giocatore("non.istituzionale@libero.it"); });
    }
}
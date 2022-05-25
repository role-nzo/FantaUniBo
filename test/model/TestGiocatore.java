package test.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;

public class TestGiocatore {
    
    private Giocatore giocatore;

    private Set<Professore> commissione;
    private Set<Professore> professoriSeguiti;
    private CorsoDiLaurea ingInf;
    private Professore prof1;
    private Professore prof2;
    @Before
    public void SetUp(){

        // Creo il giocatore
        giocatore = new Giocatore("bianca.laverde@studio.unibo.it");

        // Creo il corso del giocatore
        ingInf = new CorsoDiLaurea("12345","2021/22","Ingegneria Informatica");

        // Creo i professori che comporranno commissione e professoriSeguiti del giocatore
        prof1 = new Professore("Pino", "Silvestre", "pino.silvestre@unibo.it");
        prof2 = new Professore("Massimo", "Voltaggio", "massimo.voltaggio@unibo.it");
        Professore prof3 = new Professore("Bianca", "Neve", "bianca.neve@unibo.it");
        Professore prof4 = new Professore("Domenica", "D'agosto", "domenica.dagosto@unibo.it");

        // Affinche' commissione e professoriSeguiti siano validi, i professori al loro interno devono insegnare (anche) nel corso del giocatore
        prof1.getCorsiDiLaurea().add(ingInf);
        prof2.getCorsiDiLaurea().add(ingInf);
        prof3.getCorsiDiLaurea().add(ingInf);
        prof4.getCorsiDiLaurea().add(ingInf);

        // Creo commissione e professori seguiti
        commissione = new HashSet<>();
        professoriSeguiti = new HashSet<>();

        commissione.add(prof1);
        commissione.add(prof2);
        commissione.add(prof3);
        commissione.add(prof4);

        professoriSeguiti.add(prof1);
        professoriSeguiti.add(prof3);
        professoriSeguiti.add(prof4);
    }

    @Test
    public void testGetter(){

        assertEquals("bianca.laverde@studio.unibo.it", giocatore.getEmail());
    }

    @Test
    public void testSetterOK(){

        // 
        giocatore.setEmail("lino.maia@studio.unibo.it");
        assertEquals("lino.maia@studio.unibo.it", giocatore.getEmail());

        //
        giocatore.setCorsoDiLaurea(ingInf);
        assertEquals(ingInf, giocatore.getCorsoDiLaurea());
        
        //
        giocatore.setCommissione(commissione);
        giocatore.setProfessoriSeguiti(professoriSeguiti);

        assertEquals(professoriSeguiti, giocatore.getProfessoriSeguiti());
        assertEquals(commissione, giocatore.getCommissione());

        //
        Classifica privata = new ClassificaPrivata("Questa e' una nuova classifica");
        giocatore.partecipaClassifica(privata);

        assertEquals(giocatore.getClassifichePrivate().iterator().next(), privata);

    }

    @Test
    public void testSetterKO(){

        // IllegalArgumentException se email non istituzionale per il giocatore
        assertThrows( IllegalArgumentException.class, () -> { giocatore.setEmail("guido.piano@gmail.com"); });
        assertThrows( IllegalArgumentException.class, () -> { new Giocatore("remo.labarca@libero.it"); });

        // IllegalArgumentException se la commissione non e' composta da esattamente 4 professori
        Set<Professore> newCommissione = new HashSet<>();
        newCommissione.add(prof1);

        assertThrows( IllegalArgumentException.class, () -> { giocatore.setCommissione(commissione); });

        // IllegalArgumentException se i professori seguiti non sono almeno 3
        Set<Professore> newProfSeguiti = new HashSet<>();
        newProfSeguiti.add(prof1);
        newProfSeguiti.add(prof2);

        assertThrows( IllegalArgumentException.class, () -> { giocatore.setProfessoriSeguiti(newProfSeguiti); });

        // IllegalArgumentException se almeno un professore di commissione o professoriSeguiti non insegna nel corso di laurea del partecipante
        CorsoDiLaurea dams = new CorsoDiLaurea("56789","2021/22","DAMS");
        prof1.setCorsiDiLaurea(new HashSet<>());
        prof1.getCorsiDiLaurea().add(dams); //adesso prof1 insegna solo al DAMS, e non piu' ad ingInf

        assertThrows( IllegalArgumentException.class, () -> { giocatore.setCommissione(commissione); });
        assertThrows( IllegalArgumentException.class, () -> { giocatore.setProfessoriSeguiti(professoriSeguiti); });
    }
}
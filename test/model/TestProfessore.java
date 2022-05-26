package model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;

public class TestProfessore {

    private Professore professore;
    private CorsoDiLaurea ingInf;
    private AzioneSignificativa azioneGlobale;
    private EventoAvvenuto evento;
    private Set<CorsoDiLaurea> corsiDiEster;

    @Before
    public void SetUp() {
        // Creo un corso dove insegna il professore
        ingInf = new CorsoDiLaurea("12345", "2021/22", "Ingegneria Informatica");
        corsiDiEster = new HashSet<>();
        corsiDiEster.add(ingInf);

        // Creo professore
        professore = new Professore("Ester", "Poli", "ester.poli@unibo.it", corsiDiEster);

        // Creo un evento avvenuto relativo al professore
        azioneGlobale = new AzioneSignificativa(-50, "Il professore arriva in ritardo di almeno 15 minuti");
        Set<Integer> risposteVincitrici = new HashSet<>();
        risposteVincitrici.add(ValoreRisposta.FALSO.value);
        evento = new EventoAvvenuto(Date.valueOf(LocalDate.now()), azioneGlobale, professore, risposteVincitrici);

    }

    @Test
    public void testGetter() {

        assertEquals("Ester", professore.getNome());
        assertEquals("Poli", professore.getCognome());
        assertEquals("ester.poli@unibo.it", professore.getEmail());

        assertEquals(ingInf, professore.getCorsiDiLaurea().iterator().next());

    }

    @Test
    public void testSetter() {

        professore.setNome("Lino");
        assertEquals("Lino", professore.getNome());

        //
        professore.setCognome("Sasso");
        assertEquals("Sasso", professore.getCognome());

        //
        professore.setEmail("lino.sasso@unibo.it");
        assertEquals("lino.sasso@unibo.it", professore.getEmail());

        //
        Set<CorsoDiLaurea> nuoviCorsiDiLino = new HashSet<>();
        CorsoDiLaurea dams = new CorsoDiLaurea("56789", "2021/22", "DAMS");
        nuoviCorsiDiLino.add(ingInf);
        nuoviCorsiDiLino.add(dams);

        professore.setCorsiDiLaurea(nuoviCorsiDiLino);
        assertEquals(nuoviCorsiDiLino, professore.getCorsiDiLaurea());

        // 
        professore.aggiungiEventoAvvenuto(evento);
        assertEquals(evento, professore.getEventiAvvenuti().iterator().next());
    }
}

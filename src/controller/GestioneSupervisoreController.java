package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import interfaces.IGestioneSupervisore;
import beans.*;

public class GestioneSupervisoreController extends DBController implements IGestioneSupervisore {

    private static final String AZIONI_SIGNIFICATIVE_TABLE = "azioniSignificative";
    private static final String EVENTO_AVVENUTO_TABLE = "eventoAvvenuto";

    @Override
    public void aggiornaAzioneSignificativa(AzioneSignificativa azione) {
        try {
            PreparedStatement statementAzioneSignificativa = super.getDBConnection().prepareStatement("UPDATE " + AZIONI_SIGNIFICATIVE_TABLE + " SET descrizione=? AND cfu=? AND corsoDiLaurea=? WHERE id=?");
            
            statementAzioneSignificativa.setString(1, azione.getDescrizione());
            statementAzioneSignificativa.setInt(2, azione.getCFU());
            statementAzioneSignificativa.setInt(3, azione.getCorsoDiLaurea().isPresent() ? azione.getCorsoDiLaurea().get().getId() : null);
            statementAzioneSignificativa.setInt(4, azione.getId());
            
            statementAzioneSignificativa.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void aggiornaEventoAvvenuto(EventoAvvenuto evento) {
        try {
            PreparedStatement statementEventoAvvenuto = super.getDBConnection().prepareStatement("UPDATE " + EVENTO_AVVENUTO_TABLE + " SET cfu=? AND descrizione=? AND risposteVincitrici=? AND professore=? WHERE id=?");
            
            //FIXME: evento avvenuto non ha CFU!!!
            statementEventoAvvenuto.setInt(1, 0);
            //FIXME: evento avvenuto non ha descrizione!!!
            statementEventoAvvenuto.setString(2, "");
            statementEventoAvvenuto.setString(3, String.join(", ", evento.getValoriRisposteVincitrici().toArray().toString()));
            statementEventoAvvenuto.setInt(4, evento.getProfessore().getId());
            
            statementEventoAvvenuto.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}

package controller;

import interfaces.IVotazioni;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.*;

/**
 * VotazioniController
 */
public class VotazioniController extends DBController implements IVotazioni{

    private static final String RISPOSTE_TABLE = "risposte";
    private static final String VOTAZIONI_TABLE = "votazioni";

    @Override
    public void aggiungiRisposta(Risposta risposta) {
        try {
            PreparedStatement statementRisposta = super.getDBConnection().prepareStatement("INSERT INTO " + RISPOSTE_TABLE + " (giocatore, votazione, valore) VALUES (?,?,?)");
            
            statementRisposta.setInt(1, risposta.getGiocatore().getId());
            statementRisposta.setInt(2, risposta.getVotazione().getId());
            statementRisposta.setInt(3, risposta.getValore());
            
            statementRisposta.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void aggiungiVotazione(Votazione newVotazione) {
        try {
            PreparedStatement statementVotazione;

            if(newVotazione instanceof VotazioneAzioneSignificativa) {
                statementVotazione = super.getDBConnection().prepareStatement("INSERT INTO " + VOTAZIONI_TABLE + " (timestamp, descrizione, cfu, corsoDiLaurea) VALUES (?,?,?,?)");

                statementVotazione.setDate(1, new Date(System.currentTimeMillis()));
                statementVotazione.setString(2, newVotazione.getDescrizione());
                statementVotazione.setInt(3, ((VotazioneAzioneSignificativa)newVotazione).getCFU());
                statementVotazione.setInt(4, ((VotazioneAzioneSignificativa)newVotazione).getCorsoDiLaurea().isPresent() ? ((VotazioneAzioneSignificativa)newVotazione).getCorsoDiLaurea().get().getId() : null);
            } else {
                statementVotazione = super.getDBConnection().prepareStatement("INSERT INTO " + VOTAZIONI_TABLE + " (timestamp, descrizione, azioneSignificativa, professore) VALUES (?,?,?,?)");

                statementVotazione.setDate(1, new Date(System.currentTimeMillis()));
                statementVotazione.setString(2, newVotazione.getDescrizione());
                statementVotazione.setInt(3, ((VotazioneEventoAvvenuto)newVotazione).getAzioneSignificativa().getId());
                statementVotazione.setInt(4, ((VotazioneEventoAvvenuto)newVotazione).getProfessore().getId());
            }
            
            statementVotazione.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
}
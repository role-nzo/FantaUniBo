package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import beans.CorsoDiLaurea;
import beans.Giocatore;
import beans.Risposta;
import beans.Votazione;
import beans.VotazioneAzioneSignificativa;
import beans.VotazioneEventoAvvenuto;
import interfaces.IVotazioni;

/**
 * VotazioniController
 */
public class VotazioniController extends DBController implements IVotazioni {

	private static final String RISPOSTE_TABLE = "risposte";
	private static final String VOTAZIONI_TABLE = "votazioni";

	@Override
	public void aggiungiRisposta(Risposta risposta) {
		try {
			PreparedStatement statementRisposta = super.getDBConnection().prepareStatement(
					"INSERT INTO " + RISPOSTE_TABLE + " (giocatore, votazione, valore) VALUES (?,?,?)");

			statementRisposta.setInt(1, risposta.getGiocatore().getId());
			statementRisposta.setInt(2, risposta.getVotazione().getId());
			statementRisposta.setInt(3, risposta.getValore());
			
			System.out.println(risposta.getGiocatore().getId());
			System.out.println(risposta.getVotazione().getId());
			System.out.println(risposta.getValore());

			statementRisposta.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public void aggiungiVotazione(Votazione newVotazione) {
		try {
			PreparedStatement statementVotazione;

			if (newVotazione instanceof VotazioneAzioneSignificativa) {
				statementVotazione = super.getDBConnection().prepareStatement("INSERT INTO " + VOTAZIONI_TABLE
						+ " (timestamp, descrizione, cfu, corsoDiLaurea) VALUES (?,?,?,?)");

				statementVotazione.setDate(1, new Date(System.currentTimeMillis()));
				statementVotazione.setString(2, newVotazione.getDescrizione());
				statementVotazione.setInt(3, ((VotazioneAzioneSignificativa) newVotazione).getCFU());
				statementVotazione.setInt(4,
						((VotazioneAzioneSignificativa) newVotazione).getCorsoDiLaurea().isPresent()
								? ((VotazioneAzioneSignificativa) newVotazione).getCorsoDiLaurea().get().getId()
								: null);
			} else {
				statementVotazione = super.getDBConnection().prepareStatement("INSERT INTO " + VOTAZIONI_TABLE
						+ " (timestamp, descrizione, azioneSignificativa, professore) VALUES (?,?,?,?)");

				statementVotazione.setDate(1, new Date(System.currentTimeMillis()));
				statementVotazione.setString(2, newVotazione.getDescrizione());
				statementVotazione.setInt(3, ((VotazioneEventoAvvenuto) newVotazione).getAzioneSignificativa().getId());
				statementVotazione.setInt(4, ((VotazioneEventoAvvenuto) newVotazione).getProfessore().getId());
			}

			statementVotazione.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public Votazione ottieniVotazione(int id) {
		Votazione v = null;

		try {
			PreparedStatement statementVotazione = super.getDBConnection()
					.prepareStatement("SELECT * FROM " + VOTAZIONI_TABLE + " WHERE id=?");
			
			statementVotazione.setInt(1, id);

			ResultSet resultVotazioni = statementVotazione.executeQuery();

			if (resultVotazioni.next()) {
				int azioneSignificativa = resultVotazioni.getInt("azioneSignificativa");

				GestioneGiocatoreController ggc = new GestioneGiocatoreController();
				
				if (resultVotazioni.wasNull()) {
					// VotazioneAzioneSignificativa
					v = new VotazioneAzioneSignificativa();
					
					v.setId(resultVotazioni.getInt("id"));
					v.setTimestamp(resultVotazioni.getTimestamp("timestamp"));
					v.setDescrizione(resultVotazioni.getString("descrizione"));
					
					((VotazioneAzioneSignificativa) v).setCFU(resultVotazioni.getInt("cfu"));
					
					int corsoDiLaureaId = resultVotazioni.getInt("corsoDiLaurea");

					Optional<CorsoDiLaurea> corsoDiLaurea;

					if (!resultVotazioni.wasNull())
						corsoDiLaurea = Optional.of(ggc.ottieniCorsoDiLaurea(corsoDiLaureaId));
					else
						corsoDiLaurea = Optional.ofNullable(null);

					((VotazioneAzioneSignificativa) v).setCorsoDiLaurea(corsoDiLaurea);
				} else {
					// VotazioneEventoAvvenuto
					v = new VotazioneEventoAvvenuto();
					v.setId(resultVotazioni.getInt("id"));
					v.setTimestamp(resultVotazioni.getTimestamp("timestamp"));
					((VotazioneEventoAvvenuto) v).setAzioneSignificativa(ggc.ottieniAzioneSignificativa(azioneSignificativa));
					((VotazioneEventoAvvenuto) v).setProfessore(ggc.ottieniProfessore(resultVotazioni.getInt("professore")));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return v;
	}
	
	public boolean checkRispostaGiocatore(int idGiocatore, int idVotazione) {
		try {
			PreparedStatement statementVotazione = super.getDBConnection()
					.prepareStatement("SELECT * FROM " + RISPOSTE_TABLE + " WHERE giocatore=? AND votazione=?");
			
			statementVotazione.setInt(1, idGiocatore);
			statementVotazione.setInt(2, idVotazione);
			
			ResultSet resultVotazione = statementVotazione.executeQuery();
			
			return resultVotazione.next();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}

	public Set<Votazione> ottieniVotazioniGiornaliere() {
		Set<Votazione> votazioni = new HashSet<Votazione>();

		try {
			PreparedStatement statementVotazione = super.getDBConnection()
					.prepareStatement("SELECT * FROM " + VOTAZIONI_TABLE);

			ResultSet resultVotazioni = statementVotazione.executeQuery();

			while (resultVotazioni.next()) {
				int azioneSignificativa = resultVotazioni.getInt("azioneSignificativa");

				GestioneGiocatoreController ggc = new GestioneGiocatoreController();

				Votazione v;
				
				if (resultVotazioni.wasNull()) {
					// VotazioneAzioneSignificativa
					v = new VotazioneAzioneSignificativa();
					
					v.setId(resultVotazioni.getInt("id"));
					v.setTimestamp(resultVotazioni.getTimestamp("timestamp"));
					v.setDescrizione(resultVotazioni.getString("descrizione"));
					
					((VotazioneAzioneSignificativa) v).setCFU(resultVotazioni.getInt("cfu"));
					
					int corsoDiLaureaId = resultVotazioni.getInt("corsoDiLaurea");

					Optional<CorsoDiLaurea> corsoDiLaurea;

					if (!resultVotazioni.wasNull())
						corsoDiLaurea = Optional.of(ggc.ottieniCorsoDiLaurea(corsoDiLaureaId));
					else
						corsoDiLaurea = Optional.ofNullable(null);

					((VotazioneAzioneSignificativa) v).setCorsoDiLaurea(corsoDiLaurea);
				} else {
					// VotazioneEventoAvvenuto
					v = new VotazioneEventoAvvenuto();
					v.setId(resultVotazioni.getInt("id"));
					v.setTimestamp(resultVotazioni.getTimestamp("timestamp"));
					((VotazioneEventoAvvenuto) v).setAzioneSignificativa(ggc.ottieniAzioneSignificativa(azioneSignificativa));
					((VotazioneEventoAvvenuto) v).setProfessore(ggc.ottieniProfessore(resultVotazioni.getInt("professore")));
				}
				
				votazioni.add(v);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return votazioni;
	}

	public Set<Risposta> ottieniRisposte(int id) {
		Set<Risposta> risposte = new HashSet<Risposta>();
		
		try {
			PreparedStatement statementRisposte = super.getDBConnection()
					.prepareStatement("SELECT * FROM " + RISPOSTE_TABLE + " WHERE votazione=?");

			statementRisposte.setInt(1, id);
			
			ResultSet resultRisposte = statementRisposte.executeQuery();
			
			while(resultRisposte.next()) {
				Risposta risposta = new Risposta();
				risposta.setId(resultRisposte.getInt("id"));
				risposta.setValore(resultRisposte.getInt("valore"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return risposte;
	}

	public Giocatore ottieniGiocatoreDaRisposta(int id) {
		try {
			PreparedStatement statementGiocatore = super.getDBConnection()
					.prepareStatement("SELECT * FROM " + RISPOSTE_TABLE + " WHERE id=?");
			
			statementGiocatore.setInt(1, id);
			
			ResultSet resultGiocatore = statementGiocatore.executeQuery();
			
			if(resultGiocatore.next()) {
				return new GestioneGiocatoreController().ottieniGiocatore(resultGiocatore.getInt("giocatore"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
	//FIXME: DA FARE??
	/*public Votazione ottieniVotazioneDaRisposta(int id) {
		try {
			PreparedStatement statementGiocatore = super.getDBConnection()
					.prepareStatement("SELECT * FROM " + RISPOSTE_TABLE + " WHERE id=?");
			
			statementGiocatore.setInt(1, id);
			
			ResultSet resultGiocatore = statementGiocatore.executeQuery();
			
			if(resultGiocatore.next()) {
				return this.ottieniVotazione(resultGiocatore.getInt("giocatore"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}*/
}
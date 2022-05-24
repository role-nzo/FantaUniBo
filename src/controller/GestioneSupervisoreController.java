package controller;

import java.sql.Connection;

import interfaces.IGestioneSupervisore;
import beans.*;

public class GestioneSupervisoreController extends DBController implements IGestioneSupervisore{

    @Override
    public void aggiornaAzioneSignificativa(AzioneSignificativa azione) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void aggiornaEventoAvvenuto(EventoAvvenuto evento) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Connection getDBConnection() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

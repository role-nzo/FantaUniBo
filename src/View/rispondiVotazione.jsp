<%@page import="javax.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.stream.Collectors" %>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Rispondi Votazione" />
</jsp:include>

<%
if (session.getAttribute("user") == null) {
	response.sendRedirect("login.jsp");
	return;
}

GestioneGiocatoreController ggc = new GestioneGiocatoreController();

Giocatore giocatore = (Giocatore) session.getAttribute("giocatore");

if (giocatore == null) {
	response.sendRedirect("homeAmministratore.jsp");
	return;
}

if (giocatore.getCorsoDiLaurea() == null) {
	response.sendRedirect("inserimentoDati.jsp");
	return;
}

if (request.getParameter("id") == null) {
	response.sendRedirect("homeGiocatore.jsp");
	return;
}

VotazioniController vo = new VotazioniController();

int id = Integer.parseInt(request.getParameter("id"));

Votazione v = vo.ottieniVotazione(id);

if(v == null) {
	response.sendRedirect("homeGiocatore.jsp");
	return;
}

if(vo.checkRispostaGiocatore((int) session.getAttribute("user"), v.getId())) {
	//l'utente ha già risposto
	response.sendRedirect("homeGiocatore.jsp");
	return;
}

if(v instanceof VotazioneAzioneSignificativa && ((VotazioneAzioneSignificativa) v).getCorsoDiLaurea().isPresent() && !((VotazioneAzioneSignificativa) v).getCorsoDiLaurea().get().equals(giocatore.getCorsoDiLaurea())) {
	//la votazione non appartiene al corso dell'utente
	response.sendRedirect("homeGiocatore.jsp");
	return;
}

if(v instanceof VotazioneEventoAvvenuto && !giocatore.getProfessoriSeguiti().contains(((VotazioneEventoAvvenuto) v).getProfessore())) {
	//l'utente non segue il professore
	response.sendRedirect("homeGiocatore.jsp");
	return;
}

//1 = VotazioneAzioneSignificativa, 0 = VotazioneEventoAvvenuto
int type = v instanceof VotazioneAzioneSignificativa ? 1 : 0;

if( request.getParameter("risposta") != null) {
	Risposta risposta = new Risposta();
	risposta.setVotazione(v);
	risposta.setGiocatore(giocatore);
	
	int r = Integer.parseInt(request.getParameter("risposta"));
	
	if(type == 0) {
		//evento avvenuto: -1,0,1 (rispostaValore: 1,2,...)
		if(r == 1) {
			r = Integer.parseInt(request.getParameter("rispostaValore"));
		}
	}
	
	risposta.setValore(r);
	vo.aggiungiRisposta(risposta);
	response.sendRedirect("homeGiocatore.jsp");
	return;
}
%>

    <!-- INIZIO -->
    <div class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
        <div class="col-4 row justify-content-center align-items-center">
            <form class="col-12 row justify-content-center">
            	<input type="hidden" name="id" value="<%= id %>"/>
                <h2>Rispondi alla votazione</h2>
                <div class="mb-3">
                    <%= (type == 1 ?
                    		((VotazioneAzioneSignificativa) v).getDescrizione() : 
                    		((VotazioneEventoAvvenuto) v).getAzioneSignificativa().getDescrizione())%>
                    <h5 class="mt-2">
					
					<%= (type == 1 ?
							((VotazioneAzioneSignificativa) v).getCFU() + " CFU" :
							((VotazioneEventoAvvenuto) v).getProfessore().getNome() + " " + ((VotazioneEventoAvvenuto) v).getProfessore().getCognome()) %>
					
					</h5>
                </div>
                <div class="mb-3">
                    <% if(type == 0) { %>
	                    <div class="form-check">
	                        <input class="form-check-input" type="radio" name="risposta" id="risposta-1" value="-1" required>
	                        <label class="form-check-label" for="risposta-1">
	                            Non so
	                        </label>
	                    </div>
                    <% } %>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="risposta" id="risposta0" value="0" required>
                        <label class="form-check-label" for="risposta0">
                            <% if(type == 1) { %> No <% } else { %> Falso <% } %>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="risposta" id="risposta1" value="1" required>
                        <label class="form-check-label" for="risposta1">
                            <% if(type == 1) { %>
                            	Sì
                            <% } else { %>
                            	<input type="number" class="form-control" name="rispostaValore" placeholder="Sì, è successo ... volta/e" min="1" step="1"/>
                            <% } %>
                        </label> 
                    </div>
                    <button type="submit" class="w-100 btn btn-success mt-3">Rispondi</button>
                </div>
            </form>
        </div>
    </div>
    <!-- FINE -->
<jsp:include page="footer.jsp"/>
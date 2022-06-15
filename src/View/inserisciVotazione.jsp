<%@page import="java.time.ZonedDateTime"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="javax.swing.plaf.synth.SynthOptionPaneUI"%>
<%@page import="javax.*"%>
<%@page import="java.util.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Aggiungi Votazione" />
</jsp:include>

<%
	boolean badInsert = false;
	
	
	if (session.getAttribute("user") == null) {
		//non è loggato
		response.sendRedirect("login.jsp");
		return;
	}

	if(request.getParameter("type") == null || request.getParameter("type").isBlank()){
		response.sendRedirect("homeGiocatore.jsp");
		return;
	}
	
	GestioneGiocatoreController ggc = new GestioneGiocatoreController();
	
	Giocatore giocatore = (Giocatore) session.getAttribute("giocatore");

	String type = request.getParameter("type");
	if(type.equals("azione")){
		
		if (request.getParameter("text") != null && request.getParameter("cfu") != null && request.getParameter("globaleCorso") != null) {
			if (request.getParameter("text").isBlank() || request.getParameter("cfu").isBlank() || request.getParameter("globaleCorso").isBlank()) {
				badInsert = true;
			} else {
								
				VotazioniController vc = new VotazioniController();
				VotazioneAzioneSignificativa newVotAz = new VotazioneAzioneSignificativa();
				newVotAz.setDescrizione(request.getParameter("text"));
				newVotAz.setCFU(Integer.parseInt(request.getParameter("cfu")));
				newVotAz.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));				
				
				if(request.getParameter("globaleCorso").equals("corso"))
					newVotAz.setCorsoDiLaurea(Optional.of(giocatore.getCorsoDiLaurea()));
				else
					newVotAz.setCorsoDiLaurea(Optional.empty());
								
				vc.aggiungiVotazione(newVotAz);
				response.sendRedirect("homeGiocatore.jsp");
			}
		}
		
	}else if (type.equals("evento")){
		
		if (request.getParameter("IDprofessore") != null && request.getParameter("IDazione") != null) {
			if (request.getParameter("IDprofessore").isBlank() || request.getParameter("IDazione").isBlank()) {
				badInsert = true;
			} else {
				
				
				VotazioniController vc = new VotazioniController();
				VotazioneEventoAvvenuto newVotEv = new VotazioneEventoAvvenuto();
				
				Professore prof = ggc.ottieniProfessore(Integer.parseInt(request.getParameter("IDprofessore")));
				AzioneSignificativa azione = ggc.ottieniAzioneSignificativa(Integer.parseInt(request.getParameter("IDazione")));
				
				newVotEv.setAzioneSignificativa(azione);
				newVotEv.setProfessore(prof);
				newVotEv.setDescrizione(prof.getNome() + " ha eseguito " + azione.getDescrizione());
				newVotEv.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
				
				vc.aggiungiVotazione(newVotEv);
				response.sendRedirect("homeGiocatore.jsp");
				
			}
		}
		
	}else{
		response.sendRedirect("homeGiocatore.jsp");
		return;
	}
	String testoTitolo = type.equals("evento") ? "evento avvenuto" : "azione significativa";
%>
    <!-- INIZIO -->
    <div class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
        <div class="col-4 row justify-content-center align-items-center">
            <form class="col-12 row justify-content-center" action="./inserisciVotazione.jsp?type=<%= type %>" method="POST">
            	
                <h2>Inserisci nuova votazione per <%= testoTitolo %></h2>
                <div class="mb-3">
                
               		<% if(type.equals("azione")){ %>
               		
                    <div class="mb-3">
                        <label for="email" class="form-label">Testo azione significativa<!-- (A) --></label>
                        <textarea class="form-control" id="text" name="text" style="height: 100px"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="customRange1" class="form-label">CFU<!-- (A) --></label>
                        <input type="range" min="-300" max="300" step="10" class="form-range" id="customRange1" name="cfu" oninput="this.nextElementSibling.value = this.value">
                        Valore:&nbsp;<output>0</output>
                    </div>
                    <div class="mb-3">
                        <label for="customRange1" class="form-label">Azione globale o del tuo corso di studi?<!-- (A) --></label>
                        <br/>
                        <input type="radio" id="radioUni" name="globaleCorso" value="globale">
						<label for="radioUni">Globale</label><br>
						<input type="radio" id="radioUni1" name="globaleCorso" value="corso">
						<label for="radioUni1"><%= 	giocatore.getCorsoDiLaurea().getDescrizione() %></label><br>

                    </div>
                    
                    <% } else { %>
                    
                    <div class="mb-3">
                        <label for="customRange1" class="form-label">Azione significativa<!-- (E) --></label>
                        <select class="form-select" id="IDazione" name="IDazione">
                            <option selected disabled hidden></option>
                            <%
                            	Set<AzioneSignificativa> azioni = ggc.ottieniAzioniSignificative(giocatore.getCorsoDiLaurea().getId());
                        		for(AzioneSignificativa a : azioni){
            
                            %>
                          		<option value="<%= a.getId() %>"><%= a.getDescrizione() %></option>
                         
                            <% } %>
                        </select>
                    </div>
         
                    <div class="mb-3">
                        <label for="customRange1" class="form-label">Professore<!-- (E) --></label>
                        <select class="form-select" id="IDprofessore" name="IDprofessore">
                            <option selected disabled hidden></option>
                            <%
                            	Set<Professore> seguiti = ggc.ottieniProfessoriSeguiti(giocatore.getId());
                        		for(Professore p : seguiti){
            
                            %>
                            	<option value="<%= p.getId() %>"><%= p.getNome() %> <%= p.getCognome() %></option>
                            <% } %>
                        </select>
                    </div>
                    
                    <% } %>
                    
                    <button type="submit" class="w-100 btn btn-success mt-3">Inserisci</button>
                </div>
            </form>
        </div>
    </div>
<!-- FINE -->

<jsp:include page="footer.jsp" />
<%@page import="javax.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.stream.Collectors" %>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Home Giocatore" />
</jsp:include>

<%
if (session.getAttribute("user") == null) {
	response.sendRedirect("login.jsp");
	return;
}

GestioneGiocatoreController ggc = new GestioneGiocatoreController();

Giocatore giocatore = ggc.ottieniGiocatore((int) session.getAttribute("user"));

if (giocatore == null) {
	response.sendRedirect("homeAmministratore.jsp");
	return;
}

if (giocatore.getCorsoDiLaurea() == null) {
	response.sendRedirect("inserimentoDati.jsp");
	return;
}

if (giocatore.getRuolo() != Ruolo.SUPERVISORE) {
	response.sendRedirect("homeGiocatore.jsp");
	return;
}


%>


    <!-- INIZIO --> 
    <div class="d-flex flex-column container-fluid align-items-center vh-100 mt-5">
        <div class="col-12 row justify-content-center mt-5">
            <div class="col-12 d-flex flex-row justify-content-center align-items-center mb-5">
                <div class="col-12 col-lg-8">
                    <h1>Ciao, <%= giocatore.getEmail() %></h1>
                    <h4 class="fw-normal">Supervisore di Ingegneria Informatica T</h4>
                </div>
            </div>
            <div class="col-12 col-lg-4">
                <h2 class="mb-3">
                    Eventi Avvenuti
                </h2>
                <ul class="list-group">
                <%
                for(Professore p : ggc.ottieniProfessoriDaCorsiDiLaurea(giocatore.getCorsoDiLaurea().getId())){
                	for(EventoAvvenuto e : ggc.ottieniEventiAvvenutiDaProfessore(p.getId())){
                %>
                    <a href="#" class="list-group-item list-group-item-action" aria-current="true">
                        <div class="d-flex w-100 justify-content-between"> 
                            <h5 class="mb-1"><%= p.getNome() %> <%= p.getCognome() %></h5>
                            <small>17-06-2022</small>
                        </div>  
                        <div class="d-flex w-100 justify-content-between"> 
	                        <p class="mb-0"> <%= e.getAzioneSignificativa().getDescrizione() %>.</p>
	                
                        </div>
                        <div class="d-flex w-100 justify-content-between"> 
                            <p class="mb-0">Risposte vincitrici:</p>
	                        <%
	                        Iterator<Integer> risp = e.getValoriRisposteVincitrici().iterator();
	                        while( risp.hasNext() ) {   %>
	                        
	                        <span class="badge bg-primary rounded-pill"><%= ValoreRisposta.labelOf(risp.next()) %></span>
	                        <% } %>
                        </div>
                    </a>
                    <%
                    	} 
                    }
                    %>
                </ul>
            </div>
            <div class="col-12 col-lg-4 justify-content-center align-items-center mt-5 mt-lg-0">
                <h2 class="mb-3">
                    Azioni Significative
                </h2>
                <ul class="list-group">
                <%
                Set<AzioneSignificativa> azioni = ggc.ottieniAzioniSignificative(giocatore.getCorsoDiLaurea().getId());
                for(AzioneSignificativa a : azioni) {
                %>
                    <a href="#" class="list-group-item d-flex justify-content-between align-items-center">
                        <%= a.getDescrizione() %>
                        <span class="badge bg-primary rounded-pill"><%= a.getCFU() %> CFU</span>
                    </a>
                <% } %>
                </ul>
            </div>
        </div>
    </div>

<!-- FINE -->

<jsp:include page="footer.jsp" />
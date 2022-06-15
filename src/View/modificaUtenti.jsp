<%@page import="javax.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>
<%@page import="java.util.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Home Amministratore" />
</jsp:include>

<%
	if(session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}

	GestioneGiocatoreController ggc = new GestioneGiocatoreController();

	Giocatore giocatore = ggc.ottieniGiocatore((int) session.getAttribute("user"));
	
	if(giocatore != null) {
		//non è amministratore
		response.sendRedirect("homeGiocatore.jsp");
	}
	
	Utente utente = ggc.ottieniUtente((int) session.getAttribute("user"));
	
	List<Utente> utenti = ggc.ottieniUtenti();
%>

    <!-- INIZIO -->
    <div class="d-flex flex-column container-fluid justify-content-center align-items-center mt-5">
        <div class="col-6 row justify-content-center align-items-center mt-5">
            <form class="col-12 row justify-content-center">
                <h2>Modifica utenti</h2>
                <div class="list-group">
                <% for(Utente u : utenti) { %>
                    <li class="list-group-item list-group-item-action">
                        <form>
                            <div class="d-flex w-100 justify-content-between">
                                <div class="d-flex flex-grow-1 me-3">
                                    <input type="email" class="form-control me-3" id="email" value="<%= u.getEmail() %>">
                                    <select class="form-select" id="ruolo">
                                        <option value="partecipante" <%= u.getRuolo().equals(Ruolo.from("PARTECIPANTE")) ? "selected" : "" %>>Partecipante</option>
                                        <option value="supervisore" <%= u.getRuolo().equals(Ruolo.from("SUPERVISORE")) ? "selected" : "" %>>Supervisore</option>
                                        <option value="amministratore" <%= u.getRuolo().equals(Ruolo.from("AMMINISTRATORE")) ? "selected" : "" %>>Amministratore</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-success">Aggiorna</button>
                            </div>
                        </form>
                    </li>
                    <% } %>
                </div>
            </form>
        </div>
    </div>
    <!-- FINE -->
<jsp:include page="footer.jsp"/>
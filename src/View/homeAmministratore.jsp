<%@page import="javax.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


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
%>


    <!-- INIZIO -->
    <div class="d-flex flex-column container-fluid vh-100 mt-5">
        <div class="col-12 row justify-content-center mt-5">
            <div class="col-12 d-flex flex-row justify-content-center align-items-center mb-5">
                <div class="col-8">
                    <h1>Ciao, <%= utente.getEmail() %></h1>
                    <h4 class="fw-normal">Amministratore</h4>
                </div>
            </div>
            <div class="col-4">
                <h2 class="mb-3 d-flex justify-content-between">
                    Seleziona il corso di laurea
                </h2>
                <div class="mb-3">
                    <label for="corsoDiLaurea" class="form-label">Corso di laurea</label>
                    <select class="form-select" id="corsoDiLaurea">
                        <option selected disabled hidden></option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">Modifica</button>
            </div>
            <div class="col-4 justify-content-center align-items-center">
                <div class="d-flex justify-content-between mb-3">
                    <h2 class="d-flex justify-content-between">
                        Modifica utenti
                    </h2>
                    <a class="btn btn-success" href="modificaUtenti.jsp">Modifica</a>
                </div>
                <div class="d-flex justify-content-between mb-3">
                    <h2 class="d-flex justify-content-between">
                        Visualizza log
                    </h2>
                    <button type="submit" class="btn btn-primary">Visualizza</button>
                </div>
            </div>
        </div>
    </div>
    <!-- FINE -->

<jsp:include page="footer.jsp"/>
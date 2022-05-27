<%@page import="javax.swing.plaf.synth.SynthOptionPaneUI"%>
<%@page import="javax.*"%>
<%@page import="java.util.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Aggiungi Classifica" />
</jsp:include>

<%
boolean badInsert = false;

if (session.getAttribute("user") == null) {
	//non è loggato
	response.sendRedirect("login.jsp");
	return;
}

//FIXME: è se l'utente è amministratore può inserire nuove classifiche?
//se sì: problema, non puo partecipare --> la classifica avrebbe 0 giocatori --> inaccessibile
//se no: aggiungere controllo qui

if (request.getParameter("name") != null && request.getParameter("password") != null) {
	if (request.getParameter("name").isBlank() || request.getParameter("password").isBlank()) {
		badInsert = true;
	} else {
		ClassificheController cc = new ClassificheController();

		ClassificaPrivata c = new ClassificaPrivata();
		c.setNome(request.getParameter("name"));

		cc.aggiungiClassifica(c, request.getParameter("password"));
		cc.partecipaClassifica((int) session.getAttribute("user"), c, request.getParameter("password"));

		response.sendRedirect("homeGiocatore.jsp");
	}
}
%>


<!-- INIZIO -->
<div
	class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
	<div class="col-3 row justify-content-center align-items-center">
		<div class="col justify-content-center align-items-center">
			<form>
				<h2>Aggiungi classifica</h2>
				<%
				if (badInsert) {
				%>
				<div class="alert alert-danger" role="alert">
					Parametri non validi
				</div>
				<%
				}
				%>
				<div class="mb-3">
					<label for="nomeClassifica" class="form-label">Nome
						Classifica</label> <input type="text" class="form-control" id="name"
						name="name">
				</div>
				<div class="mb-3">
					<label for="nomeClassifica" class="form-label">Chiave
						Classifica</label> <input type="password" class="form-control"
						id="password" name="password">
				</div>
				<button type="submit" class="w-100 btn btn-primary">Aggiungi</button>
			</form>
		</div>
	</div>
</div>
<!-- FINE -->

<jsp:include page="footer.jsp" />
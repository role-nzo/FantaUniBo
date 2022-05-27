<<%@page import="javax.*"%> <%@page import="beans.*"%>
	<%@page import="controller.*"%> <%@page import="java.util.*"%>
	<%@page import="java.util.stream.Collectors"%><jsp:include page="header.jsp">
	<jsp:param name="title" value="Partecipa Classifica" />
</jsp:include>

<%
	String errMsg = null;	

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

	if (request.getParameter("id") != null && request.getParameter("password") != null) {
		if (request.getParameter("id").isBlank() || request.getParameter("password").isBlank()) {
			errMsg = "Parametri non validi";
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(giocatore.getClassifichePrivate().stream().filter(c -> ((ClassificaPrivata) c).getId() == id).collect(Collectors.toSet()).size() > 0) {
				errMsg = "Sei gi&agrave; iscritto a questa classifica";	
			} else {
				ClassificheController cc = new ClassificheController();
	
				ClassificaPrivata c = (ClassificaPrivata) cc.ottieniClassifica(id);
				if(c != null) {
					cc.partecipaClassifica((int) session.getAttribute("user"), c, request.getParameter("password"));
					response.sendRedirect("homeGiocatore.jsp");
				} else {
					errMsg = "Classifica non trovata";
				}
			}
		}
	}%>

    <!-- INIZIO -->
<div
	class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
	<div class="col-3 row justify-content-center align-items-center">
		<div class="col justify-content-center align-items-center">
			<form>
				<h2>Partecipa classifica</h2>
				<%
				if (errMsg != null) {
				%>
				<div class="alert alert-danger" role="alert">
					<%= errMsg %>
				</div>
				<%
				}
				%>
				<div class="mb-3">
					<label for="idClassifica" class="form-label">ID Classifica</label>
					<input type="text" class="form-control" id="id" name="id">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Chiave segreta</label> <input
						type="password" class="form-control" id="password" name="password">
				</div>
				<button type="submit" class="w-100 btn btn-primary">Partecipa</button>
			</form>
		</div>
	</div>
</div>
<!-- FINE -->
<jsp:include page="footer.jsp" />
<%@page import="javax.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.stream.Collectors"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Visualizza Classifica" />
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

if (request.getParameter("id") == null) {
	response.sendRedirect("homeGiocatore.jsp");
	return;
}

if (giocatore.getCorsoDiLaurea() == null) {
	response.sendRedirect("inserimentoDati.jsp");
	return;
}

int id = Integer.parseInt(request.getParameter("id"));

if (giocatore.getClassifichePrivate().stream().filter(c -> ((ClassificaPrivata) c).getId() == id).collect(Collectors.toSet()).size() == 0) {
	response.sendRedirect("homeGiocatore.jsp");
	return;
}

ClassificaPrivata c = (ClassificaPrivata) new ClassificheController().ottieniClassifica(id);
%>


<!-- INIZIO -->
<div class="d-flex flex-column container-fluid mt-5 align-items-center">
	<div class="col-4 row justify-content-center mt-5">
		<div class="dropdown w-100">
			<button
				class="mb-3 btn btn-link dropdown-toggle fs-4 ms-0 ps-0 text-muted text-decoration-none"
				type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
				aria-expanded="false"><%= c.getNome() %></button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

				<li><a class="dropdown-item btn btn-danger" href="#">Abbandona</a></li>
			</ul>
		</div>
		<div class="mb-3">
			<ul class="list-group list-group-numbered">
				<%
				List<Giocatore> giocatori = c.getGiocatori().stream().sorted().collect(Collectors.toList());
				for(Giocatore g : giocatori) {
					%>
					<li
						class="list-group-item d-flex justify-content-between align-items-start <%/* fs-3 */ %>">
						<div class="ms-2 me-auto">
							<div class=""><%= g.getEmail() %></div>
						</div> <span class="badge bg-primary rounded-pill"><%= g.getPunteggio() %> CFU</span>
					</li>
					<%
				}
				%>
			</ul>
		</div>
	</div>
</div>
<!-- FINE -->
<jsp:include page="footer.jsp" />
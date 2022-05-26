<%@page import="javax.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Home Giocatore" />
</jsp:include>

<%
	if(session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}

	GestioneGiocatoreController ggc = new GestioneGiocatoreController();

	Giocatore giocatore = ggc.ottieniGiocatore((int) session.getAttribute("user"));
	
	if(giocatore == null) {
		response.sendRedirect("homeAmministratore.jsp");
		return;
	}
	
	if(giocatore.getCorsoDiLaurea() == null) {
		response.sendRedirect("inserimentoDati.jsp");
		return;
	}
%>

<!-- INIZIO -->
<div class="d-flex flex-column container-fluid vh-100 mt-5">
	<div class="col-12 row justify-content-center mt-5">
		<div
			class="col-12 d-flex flex-row justify-content-center align-items-center mb-5">
			<div class="col-9">
				<h1>Ciao, <%= giocatore.getEmail() %></h1>
				<h4 class="fw-normal"><%= giocatore.getCorsoDiLaurea().getDescrizione() %></h4>
			</div>
		</div>
		<div class="col-3 justify-content-center align-items-center">
			<h2 class="mb-3">La tua commissione</h2>
			<ul class="list-group">
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					Professore uno <span class="badge bg-primary rounded-pill">100
						CFU</span>
				</li>
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					A second list item <span class="badge bg-primary rounded-pill">2
						CFU</span>
				</li>
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					A third list item <span class="badge bg-primary rounded-pill">0
						CFU</span>
				</li>
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					Professore uno <span class="badge bg-primary rounded-pill">10000
						CFU</span>
				</li>
			</ul>
			<h2 class="mb-3 mt-4">I tuoi professori seguiti</h2>
			<ul class="list-group">
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					Professore uno</li>
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					A second list item</li>
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					A third list item</li>
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					Professore uno</li>
			</ul>
		</div>
		<div class="col-3">
			<h2 class="mb-3">Le tue classifiche</h2>
			<ul class="list-group">
				<a href="#"
					class="list-group-item d-flex justify-content-between align-items-start">
					<div class="me-auto">
						<div class="fw-bold">Classifica uno</div>
						14 partecipanti
					</div> <span class="badge bg-primary rounded-pill">#1</span>
				</a>
				<a href="#"
					class="list-group-item d-flex justify-content-between align-items-start">
					<div class="me-auto">
						<div class="fw-bold">Onu acifissalc</div>
						2 partecipanti
					</div> <span class="badge bg-primary rounded-pill">#10</span>
				</a>
				<a href="#"
					class="list-group-item d-flex justify-content-between align-items-start">
					<div class="me-auto">
						<div class="fw-bold">Classifica uno</div>
						999 partecipanti
					</div> <span class="badge bg-primary rounded-pill">#3</span>
				</a>
				<a href="#"
					class="list-group-item d-flex justify-content-between align-items-start">
					<div class="me-auto">
						<div class="fw-bold">Classifica uno</div>
						14 partecipanti
					</div> <span class="badge bg-primary rounded-pill">#4</span>
				</a>
			</ul>
			<button type="submit" class="mt-3 w-100 btn btn-success">Aggiungi
				nuova classifica</button>
			<button type="submit" class="mt-3 w-100 btn btn-primary">Partecipa
				ad una classifica</button>
		</div>
		<div class="col-3 justify-content-center align-items-center">
			<h2 class="mb-3">Votazioni eventi avvenuti</h2>
			<ul class="list-group">
				<a href="#" class="list-group-item list-group-item-action"
					aria-current="true">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">Professore uno</h5>
						<small>magari ci mettiamo una data</small>
					</div>
					<p class="mb-1">Some placeholder content in a paragraph.</p>
				</a>
				<a href="#" class="list-group-item list-group-item-action"
					aria-current="true">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">Professore uno</h5>
						<small>magari ci mettiamo una data</small>
					</div>
					<p class="mb-1">Some placeholder content in a paragraph.</p>
				</a>
				<a href="#" class="list-group-item list-group-item-action"
					aria-current="true">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">Professore uno</h5>
						<small>magari ci mettiamo una data</small>
					</div>
					<p class="mb-1">Some placeholder content in a paragraph.</p>
				</a>
			</ul>
			<button type="submit" class="mt-3 w-100 btn btn-success">Aggiungi
				nuovo evento</button>
			<h2 class="mb-3  mt-4">Votazioni azioni significative</h2>
			<ul class="list-group">
				<a href="#"
					class="list-group-item d-flex justify-content-between align-items-center">
					Breve descrizione dell'azione. <span
					class="badge bg-primary rounded-pill">100 CFU</span>
				</a>
				<a href="#"
					class="list-group-item d-flex justify-content-between align-items-center">
					Breve descrizione dell'azione. <span
					class="badge bg-primary rounded-pill">100 CFU</span>
				</a>
				<a href="#"
					class="list-group-item d-flex justify-content-between align-items-center">
					Breve descrizione dell'azione. <span
					class="badge bg-primary rounded-pill">100 CFU</span>
				</a>
			</ul>
			<button type="submit" class="mt-3 w-100 btn btn-success">Aggiungi
				nuova azione</button>
		</div>
	</div>
</div>
<!-- FINE -->

<jsp:include page="footer.jsp" />
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

Giocatore giocatore = (Giocatore) session.getAttribute("giocatore");

if (giocatore == null) {
	response.sendRedirect("homeAmministratore.jsp");
	return;
}

if (giocatore.getCorsoDiLaurea() == null) {
	response.sendRedirect("inserimentoDati.jsp");
	return;
}

VotazioniController vo = new VotazioniController();

Set<Votazione> votazioni = vo.ottieniVotazioniGiornaliere();
Set<Votazione> votazioniAzioniSignificative = votazioni.stream().filter(v -> v instanceof VotazioneAzioneSignificativa).collect(Collectors.toSet());
Set<Votazione> votazioniEventiAvvenuti = votazioni.stream().filter(v -> v instanceof VotazioneEventoAvvenuto).collect(Collectors.toSet());

%>

<!-- INIZIO -->
<div class="d-flex flex-column container-fluid align-items-center mt-5 mb-5">
	<div class="col-12 row justify-content-center mt-5">
		<div class="col-12 d-flex flex-row justify-content-center align-items-center mb-5">
			<div class="col-12 col-xl-9">
				<h1>
					Ciao,
					<%=giocatore.getEmail()%></h1>
				<h4 class="fw-normal"><%=giocatore.getCorsoDiLaurea().getDescrizione()%></h4>
			</div>
		</div>
		<div class="col-12 col-lg-4 col-xl-3 justify-content-center align-items-center">
			<h2 class="mb-3">Le tue classifiche</h2>
			<ul class="list-group">
				<%
				for (Classifica classifica : giocatore.getClassifichePrivate()) {
				%>
				<a href="visualizzaClassifica.jsp?id=<%= ((ClassificaPrivata) classifica).getId() %>"
					class="list-group-item d-flex justify-content-between align-items-start">
					<div class="me-auto">
						<div class="fw-bold"><%= ((ClassificaPrivata) classifica).getNome() %></div>
						<%= ((ClassificaPrivata) classifica).getGiocatori().size() %> partecipant<%= ((ClassificaPrivata) classifica).getGiocatori().size() > 1 ? "i" : "e" %>
					</div> <span class="badge bg-primary rounded-pill">#1</span>
				</a>
				<%
				}
				%>
			</ul>
			<a type="submit" class="mt-3 w-100 btn btn-success" href="aggiungiClassifica.jsp">Aggiungi
				nuova classifica</a>
			<a type="submit" class="mt-3 w-100 btn btn-primary" href="partecipaClassifica.jsp">Partecipa
				ad una classifica</a>
			<h2 class="mt-4"><a class="btn btn-light fs-4 p-2 w-100" style="font-weight: 500" href="#commissione" data-bs-toggle="collapse">La tua commissione</a></h2>
			<ul class="list-group collapse" id="commissione">
				<%
				for (Professore prof : giocatore.getCommissione()) {
				%>

				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					<%=prof.getNome() + " " + prof.getCognome()%><span
					class="badge bg-primary rounded-pill"><%= prof.getPunteggio() %> CFU</span>
				</li>

				<%
				}
				%>
			</ul>
			<h2 class="mt-4"><a class="btn btn-light fs-4 p-2 w-100" style="font-weight: 500" href="#professoriSeguiti" data-bs-toggle="collapse">I tuoi professori seguiti</a></h2>
			<ul class="list-group collapse" id="professoriSeguiti">
				<%
				for (Professore prof : giocatore.getProfessoriSeguiti()) {
				%>
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					<%=prof.getNome() + " " + prof.getCognome()%></li>

				<%
				} 
				%>
			</ul>
		</div>
		<div class="col-12 col-sm-6 col-lg-4 col-xl-3 mt-5 mt-lg-0">
			<h2 class="mb-3">Votazioni eventi avvenuti</h2>
			<ul class="list-group">
				<%
				VotazioniController vc = new VotazioniController();
				
					for(Votazione v : votazioniEventiAvvenuti) {
						VotazioneEventoAvvenuto vea = (VotazioneEventoAvvenuto) v;
						// l'utente ha gia' risposto
						if(vc.checkRispostaGiocatore(giocatore.getId(), vea.getId()))
							continue;
						// il prof non è tra i seguiti
						if(!giocatore.getProfessoriSeguiti().contains((vea.getProfessore())))
							continue;
						
						%>
						<a href="rispondiVotazione.jsp?id=<%= v.getId() %>" class="list-group-item list-group-item-action"
							aria-current="true">
							<div class="d-flex w-100 justify-content-between">
								<h5 class="mb-1"><%= vea.getProfessore().getNome() + " " + vea.getProfessore().getCognome() %></h5>
								<small>2022-06-<%= vea.getTimestamp().getDate() %></small>
							</div>
							<p class="mb-1"><%= vea.getAzioneSignificativa().getDescrizione() %></p>
						</a>
						<%
					}
				%>
			</ul>
			<a type="submit" class="mt-3 w-100 btn btn-success" href="inserisciVotazione.jsp?type=evento">Aggiungi
				nuovo evento</a>
		</div>
		<div class="col-12 col-sm-6 col-lg-4 col-xl-3 mt-5 mt-lg-0 justify-content-center align-items-center">
			<h2 class="mb-3">Votazioni azioni significative</h2>
			<ul class="list-group">
				<%
					for(Votazione v : votazioniAzioniSignificative) {
						VotazioneAzioneSignificativa vas = (VotazioneAzioneSignificativa) v;
						// l'utente ha gia' risposto
						if(vc.checkRispostaGiocatore(giocatore.getId(), vas.getId()))
							continue;
						// il corso di laurea non e' quello dell'utente
						if(vas.getCorsoDiLaurea().isPresent() && !vas.getCorsoDiLaurea().get().equals(giocatore.getCorsoDiLaurea())) {
							continue;
						}
						%>
						<a href="rispondiVotazione.jsp?id=<%= v.getId() %>"
							class="list-group-item d-flex justify-content-between align-items-center">
							<%= vas.getDescrizione() %> <span
							class="badge bg-primary rounded-pill"><%= vas.getCFU() %> CFU</span>
						</a>
						<%
					}
				%>
			</ul>
			<a type="submit" class="mt-3 w-100 btn btn-success" href="inserisciVotazione.jsp?type=azione">Aggiungi
				nuova azione</a>
		</div>
	</div>
</div>
<!-- FINE -->

<jsp:include page="footer.jsp" />
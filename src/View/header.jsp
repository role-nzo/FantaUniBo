<%@page import="beans.*"%>
<%@page import="controller.*"%>

<!DOCTYPE html>
<html lang="it">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${param.title}</title>
<link href="./css/bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>

<body>

	<nav class="navbar navbar-expand-lg bg-light fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="login.jsp">Fanta UniBo</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="login.jsp">Home</a></li>
						<% if(session.getAttribute("user") != null) {
							if(!new GestioneGiocatoreController().ottieniUtente((int) session.getAttribute("user")).getRuolo().equals(Ruolo.AMMINISTRATORE) ) {
								%>
								<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Classifiche </a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item" href="aggiungiClassifica.jsp">Aggiungi</a></li>
									<li><a class="dropdown-item" href="partecipaClassifica.jsp">Partecipa</a></li>
								</ul></li>
								<%
							}
						}
						%>
				</ul>
				<div class="d-flex" role="search">
				<% if(session.getAttribute("user") == null) { %>
					<a class="btn btn-primary me-2" href="login.jsp">Login</a> <a
						class="btn btn-secondary" href="register.jsp">Registrati</a>
				<% } else { %>
					<a class="btn btn-primary me-2" href="login.jsp?logout">Logout</a>
				<% } %>
				</div>
			</div>
		</div>
	</nav>
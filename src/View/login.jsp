<%@page import  = "javax.*"%>
<%@page import  = "beans.*"%>
<%@page import  = "controller.*"%>

<%
	if(request.getParameter("logout") != null) {
		session.setAttribute("user", null);
	}
%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Login" />
</jsp:include>

<%

	boolean wrongCredentials = false;

	if(session.getAttribute("user") != null) {
		response.sendRedirect("homeGiocatore.jsp");
	}

	if(request.getParameter("email") != null && request.getParameter("password") != null) {
		Ruolo ruolo = new LoginController().verificaCredenziali(request.getParameter("email"), request.getParameter("password"));
		//FIXME: login controller restituisce il ruolo ma poi devo recuperarmi l'id utente... ha senso?
		if(ruolo == null) {
			wrongCredentials = true;
		} else {
			int id = new GestioneGiocatoreController().ottieniUtente(request.getParameter("email"));
			
			if(id >= 0) {
				session.setAttribute("user", id);
				response.sendRedirect("homeGiocatore.jsp");
			}
		}
	}
%>

<!-- INIZIO -->
<div
	class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
	<div class="col-3 row justify-content-center align-items-center">
		<div class="col justify-content-center align-items-center">
			<form>
				<h2>Login</h2>
				<%
					if(wrongCredentials) {
						%>
						<div class="alert alert-danger" role="alert">
						  Credenziali errate
						</div>
						<%
					}
				%>
				<div class="mb-3">
					<label for="email" class="form-label">Email</label> <input
						type="email" class="form-control" id="email" name="email" required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" id="password" name="password" required>
				</div>
				<div class="mb-3 form-check">
					<input type="checkbox" class="form-check-input" id="rememberMe">
					<label class="form-check-label" for="rememberMe">Ricordami</label>
				</div>
				<button type="submit" class="w-100 btn btn-primary">Login</button>
			</form>
		</div>
	</div>
</div>
<!-- FINE -->


<jsp:include page="footer.jsp" />
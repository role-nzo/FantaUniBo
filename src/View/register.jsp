<%@page import="javax.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Registrati" />
</jsp:include>

<%

	boolean badRegistration = false;

	if(session.getAttribute("user") != null) {
		response.sendRedirect("homeGiocatore.jsp");
	}

	if(request.getParameter("email") != null && request.getParameter("password") != null) {
		new RegistrazioneController().registraUtente(request.getParameter("email"), request.getParameter("password"));
		response.sendRedirect("login.jsp");
	}
%>

<!-- INIZIO -->
<div
	class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
	<div class="col-3 row justify-content-center align-items-center">
		<div class="col justify-content-center align-items-center">
			<form>
				<h2>Registrati</h2>
				<%
					if(badRegistration) {
						%>
						<div class="alert alert-danger" role="alert">
						  Registrazione fallita
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
					<input type="checkbox" class="form-check-input" id="terms" required>
					<label class="form-check-label" for="terms">Accetto i
						termini e le condizioni</label>
				</div>
				<button type="submit" class="w-100 btn btn-primary">Registrati</button>
			</form>
		</div>
	</div>
</div>
<!-- FINE -->

<jsp:include page="footer.jsp" />
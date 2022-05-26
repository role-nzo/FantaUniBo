<%@page import="javax.*"%>
<%@page import="java.util.*"%>
<%@page import="beans.*"%>
<%@page import="controller.*"%>


<jsp:include page="header.jsp">
	<jsp:param name="title" value="Inserimento Dati" />
</jsp:include>

<%

	boolean badInsert = false; 

	if(session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
	}

	if(session.getAttribute("corsiDiLaurea") == null) {
		session.setAttribute("corsiDiLaurea", new GestioneGiocatoreController().ottieniCorsiDiLaurea());
	}
	
	/*if(request.getParameter("email") != null && request.getParameter("password") != null) {
		new RegistrazioneController().registraUtente(request.getParameter("email"), request.getParameter("password"));
		response.sendRedirect("login.jsp");
	}*/
%>


    <!-- INIZIO -->
    <div class="d-flex flex-column container-fluid vh-100 justify-content-center align-items-center">
        <div class="col-6 row justify-content-center align-items-center">
            <form class="col-12 row justify-content-center">
                <h2>Inserimento dati</h2>
                <div class="col-6 justify-content-center align-items-center">
                    <div class="mb-3">
                        <label for="corsoDiLaurea" class="form-label">Corso di laurea</label>
                        <select class="form-select" id="corsoDiLaurea" name="corsoDiLaurea">
                            <option selected disabled hidden></option>
                            <%
                            	Set<CorsoDiLaurea> corsiDiLaurea = (HashSet<CorsoDiLaurea>) session.getAttribute("corsiDiLaurea");
                            	
                        		for(CorsoDiLaurea corsoDiLaurea : corsiDiLaurea) {
                        			%> <option value="<%= corsoDiLaurea.getId() %>"><%= corsoDiLaurea.getDescrizione() %></option> <%
                        		}
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Commissione</label>
                        <select class="form-select professori" id="commissione1" name="commissione1">
                            <option selected disabled hidden></option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                        <select class="form-select professori mt-2" id="commissione2" name="commissione2">
                            <option selected disabled hidden></option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                        <select class="form-select professori mt-2" id="commissione3" name="commissione3">
                            <option selected disabled hidden></option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                        <select class="form-select professori mt-2" id="commissione4" name="commissione4">
                            <option selected disabled hidden></option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>
                </div>
                <div class="col-6 justify-content-center align-items-center">
                    <div class="mb-3" id="professoreSeguiti">
                        <label class="form-label">Professori seguiti</label>
                        <button class="w-100 btn btn-success" type="button" id="buttonAdd">+</button>
                        <div class="input-group mt-2 professoreSeguito" id="professoreSeguito1">
                            <select class="form-select professori"  name="professoreSeguito">
                                <option selected disabled hidden></option>
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                            <button class="btn btn-danger buttonDelete" type="button" target="professoreSeguito1" disabled>-</button>
                        </div>
                        <div class="input-group mt-2 professoreSeguito" id="professoreSeguito2">
                            <select class="form-select professori" name="professoreSeguito">
                                <option selected disabled hidden></option>
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                            <button class="btn btn-danger buttonDelete" type="button" target="professoreSeguito2" disabled>-</button>
                        </div>
                        <div class="input-group mt-2 professoreSeguito" id="professoreSeguito3">
                            <select class="form-select professori" name="professoreSeguito">
                                <option selected disabled hidden></option>
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                            <button class="btn btn-danger buttonDelete" type="button" target="professoreSeguito3" disabled>-</button>
                        </div>
                    </div>
                </div>
                <button type="submit" class="w-50 btn btn-primary mt-3">Inserisci</button>
            </form>
        </div>
    </div>
    <script src="js/inserimentoDati.js"></script>
    <!-- FINE -->
<jsp:include page="footer.jsp" />
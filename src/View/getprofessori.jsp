<%@ page trimDirectiveWhitespaces="true" %>

<%@page import="com.google.gson.Gson"%>
<%@page import="controller.*"%>
<%@page import="beans.*"%>

<%= new Gson().toJson(new GestioneGiocatoreController().ottieniProfessoriDaCorsiDiLaurea(Integer.parseInt(request.getParameter("id")))) %>

<%
   response.setContentType("application/json");
%>
<%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 22/03/2025
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String message = "";
    if(request.getAttribute("message") != null) {
        message += (String) request.getAttribute("message");
    }
%>


<div class="row">
    <div class="col-12">
        <form action="${pageContext.request.contextPath}/config/conf" method="post">
            <div class="form-control mb-3">
                <input type="number" step="0.01" name="val" class="form-control" placeholder="Remise" aria-label="Taux de remise global">
                <br>
                <button class="btn btn-primary" type="submit">Valider</button>
            </div>
        </form>
    </div>

    <% if(!message.isEmpty() && !message.contains("inférieur à 100")) { %>
        <div class="col-md-12 md-auto">
            <div class="alert alert-success m-4"><%=message.replace("\"", "")%></div>
        </div>
    <% } else if (!message.isEmpty() && message.contains("inférieur à 100")) { %>
        <div class="col-md-12 md-auto">
            <div class="alert alert-danger m-4"><%=message.replace("\"", "")%></div>
        </div>
    <% } %>
</div>

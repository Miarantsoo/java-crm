<%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 26/03/2025
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <form action="${pageContext.request.contextPath}/import/imp" method="post" enctype="multipart/form-data">
        <div class="form-control m-3">
            <input type="file" name="file" id="file">
            <label for="file">Votre fichier</label>
            <br>
            <button class="btn btn-primary mt-3" type="submit">Valider</button>
        </div>
    </form>

    <% if(request.getAttribute("success") != null) { %>
        <div class="alert alert-success">
            <%=request.getAttribute("success")%>
        </div>
    <% } %>
</div>
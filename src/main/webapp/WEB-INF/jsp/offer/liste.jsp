<%@ page import="com.project.javacrm.offer.Offer" %>
<%@ page import="com.project.javacrm.utils.Pagination" %><%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 23/03/2025
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Pagination<Offer> paiements = (Pagination<Offer>) request.getAttribute("offer");
%>

<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="table-responsive">
                <table
                        id="add-row"
                        class="display table table-striped table-hover"
                >
                <thead>
                    <tr>
                        <th>Sent At</th>
                        <th>Source Type</th>
                        <th>Source ID</th>
                        <th>Client ID</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Deleted At</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Sent At</th>
                        <th>Source Type</th>
                        <th>Source ID</th>
                        <th>Client ID</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Deleted At</th>
                    </tr>
                </tfoot>
                <tbody>
                    <% for(int i = 0; i < paiements.getData().size(); i++) { %>
                    <% Offer paiement = paiements.getData().get(i); %>
                    <tr>
                        <td><%= paiement.getSent_at()!=null ? paiement.getSent_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
                        <td><%= paiement.getSource_type() %></td>
                        <td><%= paiement.getSource_id() %></td>
                        <td><%= paiement.getClient_id() %></td>
                        <td><%= paiement.getStatus() %></td>
                        <td><%= paiement.getCreated_at()!=null ? paiement.getCreated_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
                        <td><%= paiement.getUpdated_at()!=null ? paiement.getUpdated_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : ""%></td>
                        <td><%= paiement.getDeleted_at()!=null ? paiement.getDeleted_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
                    </tr>
                    <% } %>
                </tbody>
                </table>
            </div>
            <div class="pagination-wrapper">
                <nav aria-label="Page navigation">
                    <ul class="pagination" id="pagination"></ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/core/jquery-3.7.1.min.js"></script>

<script>
    $(document).ready(function () {
        var currentPage = <%= paiements.getCurrent_page() %>;
        var totalPages = <%= paiements.getTotal_pages() %>;
        var pagination = $("#pagination");

        var prevClass = (currentPage <= 1) ? "disabled" : "";
        pagination.append('<li class="page-item ' + prevClass + '"><a class="page-link" href="${pageContext.request.contextPath}/offer/liste?page=' + (currentPage - 1) + '">Previous</a></li>');

        for (var i = 1; i <= totalPages; i++) {
            var active = (i === currentPage) ? "active" : "";
            pagination.append('<li class="page-item ' + active + '"><a class="page-link" href="${pageContext.request.contextPath}/offer/liste?page=' + i + '">' + i + '</a></li>');
        }

        var nextClass = (currentPage >= totalPages) ? "disabled" : "";
        pagination.append('<li class="page-item ' + nextClass + '"><a class="page-link" href="${pageContext.request.contextPath}/offer/liste?page=' + (currentPage + 1) + '">Next</a></li>');
    });
</script>

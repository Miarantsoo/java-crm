<%@ page import="com.project.javacrm.invoice.Invoice" %>
<%@ page import="com.project.javacrm.utils.Pagination" %>
<%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 23/03/2025
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Pagination<Invoice> invoices = (Pagination<Invoice>) request.getAttribute("invoice");
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
                    <% for(int i = 0; i < invoices.getData().size(); i++) { %>
                    <% Invoice invoice = invoices.getData().get(i); %>
                    <tr>
                        <td><%= invoice.getSent_at() %></td>
                        <td><%= invoice.getSource_type() %></td>
                        <td><%= invoice.getSource_id() %></td>
                        <td><%= invoice.getClient_id() %></td>
                        <td><%= invoice.getStatus() %></td>
                        <td><%= invoice.getCreated_at()!=null ? invoice.getCreated_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
                        <td><%= invoice.getUpdated_at()!=null ? invoice.getUpdated_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : ""%></td>
                        <td><%= invoice.getDeleted_at()!=null ? invoice.getDeleted_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
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
        var currentPage = <%= invoices.getCurrent_page() %>;
        var totalPages = <%= invoices.getTotal_pages() %>;
        var pagination = $("#pagination");

        var prevClass = (currentPage <= 1) ? "disabled" : "";
        pagination.append('<li class="page-item ' + prevClass + '"><a class="page-link" href="${pageContext.request.contextPath}/invoice/liste?page=' + (currentPage - 1) + '">Previous</a></li>');

        for (var i = 1; i <= totalPages; i++) {
            var active = (i === currentPage) ? "active" : "";
            pagination.append('<li class="page-item ' + active + '"><a class="page-link" href="${pageContext.request.contextPath}/invoice/liste?page=' + i + '">' + i + '</a></li>');
        }

        var nextClass = (currentPage >= totalPages) ? "disabled" : "";
        pagination.append('<li class="page-item ' + nextClass + '"><a class="page-link" href="${pageContext.request.contextPath}/invoice/liste?page=' + (currentPage + 1) + '">Next</a></li>');
    });
</script>

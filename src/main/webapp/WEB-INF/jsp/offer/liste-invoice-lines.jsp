<%@ page import="com.project.javacrm.invoice.Invoice" %>
<%@ page import="com.project.javacrm.utils.Pagination" %>
<%@ page import="com.project.javacrm.invoice.InvoiceLine" %>
<%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 23/03/2025
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Pagination<InvoiceLine> invoices = (Pagination<InvoiceLine>) request.getAttribute("invoice-line");
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
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantité</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Deleted At</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantité</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Deleted At</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <% for(int i = 0; i < invoices.getData().size(); i++) { %>
                    <%
                        InvoiceLine invoice = invoices.getData().get(i);
                        double prix = invoice.getPrice()/100;
                    %>
                    <tr>
                        <td><%= invoice.getTitle() %></td>
                        <td><%= String.format("%,.2f", prix) %></td>
                        <td><%= invoice.getQuantity() %></td>
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
        pagination.append('<li class="page-item ' + prevClass + '"><a class="page-link" href="${pageContext.request.contextPath}/offer/liste-lines?page=' + (currentPage - 1) + '">Previous</a></li>');

        for (var i = 1; i <= totalPages; i++) {
            var active = (i === currentPage) ? "active" : "";
            pagination.append('<li class="page-item ' + active + '"><a class="page-link" href="${pageContext.request.contextPath}/offer/liste-lines?page=' + i + '">' + i + '</a></li>');
        }

        var nextClass = (currentPage >= totalPages) ? "disabled" : "";
        pagination.append('<li class="page-item ' + nextClass + '"><a class="page-link" href="${pageContext.request.contextPath}/offer/liste-lines?page=' + (currentPage + 1) + '">Next</a></li>');
    });
</script>

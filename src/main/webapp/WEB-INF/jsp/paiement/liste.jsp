<%@ page import="com.project.javacrm.utils.Pagination" %>
<%@ page import="com.project.javacrm.paiement.Paiement" %><%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 23/03/2025
  Time: 06:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Pagination<Paiement> paiements = (Pagination<Paiement>) request.getAttribute("paiement");
    String message = "";
    if(request.getParameter("message") != null) {
        message += request.getParameter("message");
    }
%>

<script src="${pageContext.request.contextPath}/assets/js/plugin/sweetalert/sweetalert.min.js"></script>
<script>
    <% if (!message.isEmpty()) { %>
    console.log("<%= message %>")
        if("<%= message %>".includes("err")) {
            swal("Erreur!", "<%=message.split(":")[1]%>", {
                icon: "error",
                buttons: {
                    confirm: {
                        className: "btn btn-danger",
                    },
                },
            });

        }
        else if("<%= message %>".includes("suc")) {
            swal("Succès!", "<%=message.split(":")[1]%>", {
                icon: "success",
                buttons: {
                    confirm: {
                        className: "btn btn-success",
                    },
                },
            });

        }
    <% } %>
</script>


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
                        <th>Amount</th>
                        <th>Description</th>
                        <th>Payment Source</th>
                        <th>Payment Date</th>
                        <th>Invoice ID</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Deleted At</th>
                        <th style="width: 10%">Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Amount</th>
                        <th>Description</th>
                        <th>Payment Source</th>
                        <th>Payment Date</th>
                        <th>Invoice ID</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Deleted At</th>
                        <th style="width: 10%">Action</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <% for(int i = 0; i < paiements.getData().size(); i++) { %>
                    <% Paiement paiement = paiements.getData().get(i); %>
                        <tr>
                            <td><%= paiement.getAmount() / 100%></td>
                            <td><%= paiement.getDescription() %></td>
                            <td><%= paiement.getPayment_source() %></td>
                            <td><%= paiement.getPayment_date()!=null ? paiement.getPayment_date().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
                            <td><%= paiement.getInvoice_id() %></td>
                            <td><%= paiement.getCreated_at()!=null ? paiement.getCreated_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
                            <td><%= paiement.getUpdated_at()!=null ? paiement.getUpdated_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : ""%></td>
                            <td><%= paiement.getDeleted_at()!=null ? paiement.getDeleted_at().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "" %></td>
                            <td>
                                <div class="form-button-action">
                                <button
                                                     type="button"
                                                     data-bs-toggle="tooltip"
                                                     title=""
                                                     class="btn btn-link btn-primary btn-lg"
                                                     data-original-title="Edit Task"
                                                     onclick="transformToForm(this)"
                                             >
                                        <i class="fa fa-edit"></i>
                                    </button>
                                <a href="${pageContext.request.contextPath}/paiement/delete?external_id=<%=paiement.getExternal_id()%>">
                                    <button
                                            type="button"
                                            data-bs-toggle="tooltip"
                                            title=""
                                            class="btn btn-link btn-danger"
                                            data-original-title="Remove"
                                    >
                                        <i class="fa fa-times"></i>
                                    </button>
                                </a>
                                </div>
                            </td>
                            <input type="hidden" name="" value="<%= paiement.getExternal_id()%>">
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
        pagination.append('<li class="page-item ' + prevClass + '"><a class="page-link" href="${pageContext.request.contextPath}/paiement/liste?page=' + (currentPage - 1) + '">Previous</a></li>');

        for (var i = 1; i <= totalPages; i++) {
            var active = (i === currentPage) ? "active" : "";
            pagination.append('<li class="page-item ' + active + '"><a class="page-link" href="${pageContext.request.contextPath}/paiement/liste?page=' + i + '">' + i + '</a></li>');
        }

        var nextClass = (currentPage >= totalPages) ? "disabled" : "";
        pagination.append('<li class="page-item ' + nextClass + '"><a class="page-link" href="${pageContext.request.contextPath}/paiement/liste?page=' + (currentPage + 1) + '">Next</a></li>');
    });
</script>


<script>
    function transformToForm(button) {
        var row = $(button).closest('tr');
        var cells = row.find('td');
        console.log(hidden)
        var hidden = row.find('input[type="hidden"]').val();

        var initialContent = row.html();

        cells.each(function(index, cell) {
            if (index === 0) { // Only apply to the first cell
                var cellContent = $(cell).text().trim();
                var form = $('<form>', {
                    method: 'post',
                    class: 'd-flex justify-content-center align-items-center',
                    action: '${pageContext.request.contextPath}/paiement/update'
                });
                var input = $('<input>', {
                    type: 'text',
                    class: 'form-control',
                    name: 'amount',
                    value: cellContent
                });
                var inputHidden = $('<input>', {
                    type: 'hidden',
                    value: hidden,
                    name: 'external_id'
                });
                var saveButton = $('<button>', {
                    type: 'submit',
                    class: 'btn btn-link btn-success btn-lg',
                    html: '<i class="fa fa-save"></i>',
                });
                $(cell).html(form.append(input).append(inputHidden).append(saveButton));
            }
        });

        var cancelButton = $('<button>', {
            type: 'button',
            class: 'btn btn-link btn-secondary btn-lg',
            html: '<i class="fa fa-times"></i>',
            click: function() {
                row.html(initialContent);
            }
        });

        var actionCell = cells.last();
        actionCell.html('');
        actionCell.append(cancelButton);
    }
</script>


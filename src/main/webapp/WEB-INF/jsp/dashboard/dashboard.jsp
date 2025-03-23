<%@ page import="com.project.javacrm.dashboard.ChartOffer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.javacrm.dashboard.ChartPayment" %>
<%@ page import="com.project.javacrm.dashboard.ChartTask" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 21/03/2025
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    double totalPaiement = (double) request.getAttribute("totalPaiement");
    int totalOffre = (int) request.getAttribute("totalOffer");
    int totalInvoice = (int) request.getAttribute("totalInvoice");
    List<ChartOffer> chartOffer = (List<ChartOffer>) request.getAttribute("dashOffer");
    List<ChartPayment> chartPayment = (List<ChartPayment>) request.getAttribute("dashPayment");
    ChartTask chartTask = (ChartTask) request.getAttribute("dashTask");
%>

<div class="row">

    <div class="row">
        <div class="col-6 col-sm-4 col-lg-4">
            <a href="${pageContext.request.contextPath}/paiement/liste?page=1">
                <div class="card">
                    <div class="card-body p-3 text-center">
                        <div class="h1 m-0"><%=totalPaiement%> USD</div>
                        <div class="text-muted mb-3">Total paiement </div>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-6 col-sm-4 col-lg-4">
            <a href="${pageContext.request.contextPath}/invoice/liste?page=1">
                <div class="card">
                    <div class="card-body p-3 text-center">
                        <div class="h1 m-0"><%=totalOffre%></div>
                        <div class="text-muted mb-3">Total invoice</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-6 col-sm-4 col-lg-4">
            <a href="${pageContext.request.contextPath}/offer/liste?page=1">
                <div class="card">
                    <div class="card-body p-3 text-center">
                        <div class="h1 m-0"><%=totalInvoice%></div>
                        <div class="text-muted mb-3">Total offre</div>
                    </div>
                </div>
            </a>
        </div>

    </div>

</div>

<div class="row">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <div class="card-title">Nombre de paiements par jour les 14 derniers jours</div>
            </div>
            <div class="card-body">
                <div class="chart-container">
                    <canvas id="lineChart"></canvas>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <div class="card-title">Tâches du mois</div>
            </div>
            <div class="card-body">
                <div class="chart-container">
                    <canvas
                            id="pieChart"
                            style="width: 50%; height: 50%"
                    ></canvas>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <div class="card-title">Les status des offres par mois durant l'année</div>
            </div>
            <div class="card-body">
                <div class="chart-container">
                    <canvas id="barChart"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/plugin/chart.js/chart.min.js"></script>
<script>
    var lineChart = document.getElementById("lineChart").getContext("2d"),
        barChart = document.getElementById("barChart").getContext("2d"),
        pieChart = document.getElementById("pieChart").getContext("2d");

    var myLineChart = new Chart(lineChart, {
        type: "line",
        data: {
            labels: <%= chartPayment.stream().map(ChartPayment::getDate).map(date -> "\"" + date + "\"").collect(java.util.stream.Collectors.joining(", ", "[", "]")) %>,
            datasets: [
                {
                    label: "Nbr paiements",
                    borderColor: "#1d7af3",
                    pointBorderColor: "#FFF",
                    pointBackgroundColor: "#1d7af3",
                    pointBorderWidth: 2,
                    pointHoverRadius: 4,
                    pointHoverBorderWidth: 1,
                    pointRadius: 4,
                    backgroundColor: "transparent",
                    fill: true,
                    borderWidth: 2,
                    data: <%= chartPayment.stream().map(ChartPayment::getNbr).collect(java.util.stream.Collectors.toList()) %>,
                },
            ],
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            legend: {
                position: "bottom",
                labels: {
                    padding: 10,
                    fontColor: "#1d7af3",
                },
            },
            tooltips: {
                bodySpacing: 4,
                mode: "nearest",
                intersect: 0,
                position: "nearest",
                xPadding: 10,
                yPadding: 10,
                caretPadding: 10,
            },
            layout: {
                padding: { left: 15, right: 15, top: 15, bottom: 15 },
            },
            scales: {
                xAxes: [{
                    ticks: {
                        maxRotation: 90,
                        minRotation: 45
                    }
                }]
            }
        },
    });

    var myBarChart = new Chart(barChart, {
        type: "bar",
        data: {
            labels: [
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec",
            ],
            datasets: [
                {
                    label: "Offres faites",
                    backgroundColor: "rgb(23, 125, 255)",
                    borderColor: "rgb(23, 125, 255)",
                    data: <%= chartOffer.stream().map(ChartOffer::getTotalOffre).collect(java.util.stream.Collectors.toList()) %>
                },
                {
                    label: "Offres gagnées",
                    backgroundColor: "rgb(75, 192, 192)",
                    borderColor: "rgb(75, 192, 192)",
                    data: <%= chartOffer.stream().map(ChartOffer::getTotalGagnee).collect(java.util.stream.Collectors.toList()) %>,
                },
                {
                    label: "Offres perdues",
                    backgroundColor: "rgb(255, 99, 132)",
                    borderColor: "rgb(255, 99, 132)",
                    data: <%= chartOffer.stream().map(ChartOffer::getTotalPerdue).collect(java.util.stream.Collectors.toList()) %>,
                },
            ],
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [
                    {
                        ticks: {
                            beginAtZero: true,
                        },
                    },
                ],
            },
        },
    });

    var myPieChart = new Chart(pieChart, {
        type: "pie",
        data: {
            datasets: [
                {
                    data: <%= chartTask.getData().stream().map(ChartTask.TaskElement::getNbr).collect(java.util.stream.Collectors.toList()) %>,
                    backgroundColor: ["#4bc0c0", "#1d7af3", "#fdaf4b", "#ffcd56", "#f00", "#f3545d"],
                    borderWidth: 0,
                },
            ],
            labels: <%= chartTask.getData().stream().map(ChartTask.TaskElement::getStatus).map(status -> "\"" + status + "\"").collect(java.util.stream.Collectors.joining(", ", "[", "]")) %>,
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            legend: {
                position: "bottom",
                labels: {
                    fontColor: "rgb(154, 154, 154)",
                    fontSize: 11,
                    usePointStyle: true,
                    padding: 20,
                },
            },
            pieceLabel: {
                render: "percentage",
                fontColor: "white",
                fontSize: 14,
            },
            tooltips: false,
            layout: {
                padding: {
                    left: 20,
                    right: 20,
                    top: 20,
                    bottom: 20,
                },
            },
        },
    });


</script>
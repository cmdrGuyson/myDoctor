<%@page import="Model.BarChartView"%>
<%@page import="Model.PieChartView"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="images/logo.png" />
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
            integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css"
            integrity="sha512-/zs32ZEJh+/EO2N1b0PEdoA10JkdC3zJ8L5FTiQu82LR9S/rOQNfQN7U59U9BC12swNeRAz3HSzIL2vpp4fv3w=="
            crossorigin="anonymous"
            />

        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
            integrity="sha512-s+xg36jbIujB2S2VKfpGmlC3T5V2TF3lY48DX7u2r9XzGzgPsa6wTpOQA7J9iffvdeBN0q9tKzRxVxw1JviZPg=="
            crossorigin="anonymous"
        ></script>

        <link rel="stylesheet" href="css/home.css" />
        <title>myDoctor</title>
    </head>
    <body style="background: rgb(203,255,227);
          background: radial-gradient(circle, rgba(203,255,227,1) 0%, rgba(142,255,227,1) 50%, rgba(75,255,202,1) 100%);">
        <div class="container">
            <div class="sidenav">
                <div class="row" style="padding: 30px;">
                    <div class="col" style="padding-right: 0;">
                        <img src="images/logo.png" class="logo-image" />
                    </div>
                    <div
                        class="col-7"
                        style="padding-left: 0; display: flex; align-items: center;"
                        >
                        <h3 class="logo-text">myDoctor</h3>
                    </div>
                </div>
                <div class="row" style="margin-top: 30px;">
                    <div class="col text-center">
                        <button
                            type="button"
                            class="btn btn-primary"
                            style="align-items: center;"
                            onclick="location.href = 'SelectDoctorController'"
                            >
                            <i
                                class="fas fa-plus-circle"
                                style="font-size: 1.2rem; margin-right: 10px;"
                                ></i
                            >Make Appointment
                        </button>
                    </div>
                </div>

                <div style="margin-top: 50px; padding-left: 40px;">
                    <a href="SendHomeController"><i class="fas fa-home selected-nav"></i>Home</a>
                    <a href="ViewMyAppointmentsController"><i class="far fa-calendar-alt"></i>Appointments</a>
                    <a href="ViewAllDoctorsController"><i class="fas fa-user-md"></i>Doctors</a>
                    <a href="ViewAllHospitalsController"><i class="fas fa-hospital"></i>Hospitals</a>
                    <a href="LogoutController"><i class="fas fa-sign-out-alt"></i>Logout</a>
                </div>
                <div class="row" style="margin-top: 30px; bottom: 0;">
                    <div
                        class="col text-center"
                        style="margin-top: 280px; font-size: 0.8rem;"
                        >
                        <p>
                            Made by Gayanga Kuruppu
                        </p>
                    </div>
                </div>
            </div>

            <div class="main">
                <div class="card">
                    <div class="card-header">
                        Covid-19 Statistics - (<%= (String) request.getAttribute("country")%>)
                    </div>
                    <div class="card-body" style="padding:10px">
                        <blockquote class="blockquote mb-0">
                            <canvas id="barChart" width="500px" ></canvas>
                            <footer
                                class="blockquote-footer"
                                style="font-size: 0.8rem; margin: 30px; text-align: center;"
                                >
                                <cite title="Source Title"
                                      >Percentage of cases by status</cite
                                >
                            </footer>
                            <canvas id="pieChart" width="500px" ></canvas>
                            <footer
                                class="blockquote-footer"
                                style="font-size: 0.8rem; margin: 30px; text-align: center;"
                                >
                                <cite title="Source Title"
                                      >Number of cases by status</cite
                                >
                            </footer>
                        </blockquote>
                    </div>
                    <div class="card-header">
                        <form method="GET" action="GetCovidStatsController">
                            <div class="row">
                                <div class="col-10">
                                    <select id="inputCountry" class="form-control" name="country">
                                        <option value="global" selected>Global</option>
                                        <option value="sri-lanka">Sri Lanka</option>
                                        <option value="united-states">USA</option>
                                        <option value="china">China</option>
                                        <option value="india">India</option>
                                    </select>
                                </div>
                                <div class="col-2" style="padding-left: 0px;">
                                    <button
                                        class="btn-primary fluid"
                                        style="height: 38px; width: 100%; font-size: 0.7rem;"
                                        type="submit"
                                        value="submit"
                                        >
                                        Get Data
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <% PieChartView pieChartView = (PieChartView) request.getAttribute("pieChartView");
        BarChartView barChartView = (BarChartView) request.getAttribute("barChartView");
    %>

    <script>
        //Get the <canvas> element to be replaced by the cart
        var ctx = document.getElementById("pieChart").getContext("2d");
        var myChart = new Chart(ctx, {
            type: "bar",
            data: {
                labels: ["Total Cases", "Active Cases", "Total Deaths", "Total Recovered"],
                datasets: [
                    {
                        label: "Dataset",
                        data: [<%= barChartView.getTotal()%>, <%= barChartView.getActive()%>, <%= barChartView.getDeaths()%>, <%= barChartView.getRecovered()%>],
                        backgroundColor: [
                            "rgba(153, 102, 255)",
                            "rgba(54, 162, 235)",
                            "rgba(255, 99, 132)",
                            "rgba(255, 206, 86)",
                        ],
                        borderColor: [
                            "rgba(153, 102, 255)",
                            "rgba(54, 162, 235)",
                            "rgba(255, 99, 132)",
                            "rgba(255, 206, 86)",
                        ],
                        borderWidth: 1,
                    }
                ]
            },
            options: {
                scales: {
                    yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                }
            }
        });
    </script>

    <script>
        //Get the <canvas> element to be replaced by the cart
        var ctx = document.getElementById("barChart").getContext("2d");
        var myChart = new Chart(ctx, {
            type: "doughnut",
            data: {
                labels: ["Total Deaths", "Total Recovered", "Active Cases"],
                datasets: [
                    {
                        label: "Percentage of Cases",
                        data: [<%= pieChartView.getDeathsPercentage()%>, <%= pieChartView.getRecoveredPercentage()%>, <%= pieChartView.getActivePercentage()%>],
                        backgroundColor: [
                            "rgba(255, 99, 132)",
                            "rgba(54, 162, 235)",
                            "rgba(255, 206, 86)",
                        ],
                        borderColor: [
                            "rgba(255, 99, 132, 1)",
                            "rgba(54, 162, 235, 1)",
                            "rgba(255, 206, 86, 1)",
                        ],
                        borderWidth: 1,
                    },
                ],
            },

        });
    </script>

    <script
        src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://kit.fontawesome.com/b9bf971455.js"
        crossorigin="anonymous"
    ></script>
</html>

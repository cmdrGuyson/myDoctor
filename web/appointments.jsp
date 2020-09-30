<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
        <link rel="stylesheet" href="./css/home.css" />
        <link rel="stylesheet" href="./css/doctors.css" />
        <link rel="icon" href="./images/logo.png" />

        <script type="text/javascript" charset="utf8" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css" />
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
        <title>myDoctor</title>

        <style>
            th{
                height: 50px;
            }
        </style>
    </head>

    <% String userType = (String) session.getAttribute("typeOfUser"); %>

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
                        style="
                        padding-left: 0;
                        display: flex;
                        align-items: center;
                        position: relative;
                        "
                        >
                        <h3 class="logo-text">myDoctor</h3>
                        <% if (userType.equals("admin")) {%>
                        <p style="position: absolute; bottom: -20%;">Administrator</p>
                        <% } %>
                    </div>
                </div>

                <% if (!userType.equals("admin")) {%>
                <div class="row" style="margin-top: 30px;">
                    <div class="col text-center">
                        <button
                            type="button"
                            class="btn btn-primary"
                            style="align-items: center; color: white"
                            onclick="location.href = 'SelectDoctorController'"
                            >
                            <i
                                class="fas fa-plus-circle"
                                style="font-size: 1.2rem; margin-right: 10px; color: white"
                                ></i
                            >Make Appointment
                        </button>
                    </div>
                </div>
                <%}%>

                <div style="margin-top: 50px; padding-left: 40px;">
                    <a href="SendHomeController"><i class="fas fa-home selected-nav"></i>Home</a>
                    <% if (!userType.equals("admin")) {%>
                    <a href="ViewMyAppointmentsController"><i class="far fa-calendar-alt"></i>Appointments</a>
                    <%} else {%>
                    <a href="ViewAllAppointmentsController"><i class="far fa-calendar-alt"></i>Appointments</a>
                    <% } %>
                    <a href="ViewAllDoctorsController"><i class="fas fa-user-md"></i>Doctors</a>
                    <a href="ViewAllHospitalsController"><i class="fas fa-hospital"></i>Hospitals</a>
                    <% if (userType.equals("admin")) {%>
                    <a href="ViewAllAvailabilitySlotsController"><i class="far fa-clock"></i>Availability Slots</a>
                    <%}%>
                    <a href="LogoutController"><i class="fas fa-sign-out-alt"></i>Logout</a>
                </div>
                <div class="row" style="margin-top: 30px; bottom: 0;">
                    <div
                        class="col text-center"
                        style="margin-top: 370px; font-size: 0.8rem;"
                        >
                        <p>
                            Made by Gayanga Kuruppu
                        </p>
                    </div>
                </div>
            </div>

            <div class="main">

                <div class="card text-center" style="margin-bottom: 20px; position: relative;">
                    <% if (!userType.equals("admin")) {%>
                    <div class="card-header">My Appointments</div>
                    <%} else {%>
                    <div class="card-header">Appointments</div>
                    <%}%>
                    <form action="SortAppointmentsController">
                        <input hidden name="order" value="ascending" />
                        <button type="submit" class="btn btn-outline-primary" style="margin-top:10px; margin-bottom: 5px; margin-left:50px; margin-right: 50px; top: 10%; right: 5%;" title="Sort in Ascending" type="submit">
                            <i class="fas fa-sort-amount-up-alt" style="margin-right: 5px"></i> Sort by Date Ascending
                        </button>
                    </form>

                    <form action="SortAppointmentsController">
                        <input hidden name="order" value="descending" />
                        <button type="submit" class="btn btn-outline-primary" style="margin-top:5px; margin-bottom: 10px; margin-left:50px; margin-right: 50px; top: 10%; right: 32%;" title="Sort in Descending" type="submit">
                            <i class="fas fa-sort-amount-down" style="margin-right: 5px"></i> Sort by Date Descending
                        </button>
                    </form>
                    
                </div>


                <!--Table to display pending users -->
                <div class="card" style="padding: 20px;">
                    <!--table class="table table-striped"-->
                    <table id="table_id" class="table table-striped" style="width:100%">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Appointment ID</th>
                                    <% if (userType.equals("admin")) {%>
                                <th scope="col">Patient Name</th>
                                    <% } %>
                                <th scope="col">Doctor Name</th>
                                <th scope="col">Hospital Name</th>
                                <th scope="col">Date</th>
                                <th scope="col">Time</th>
                            </tr>
                        </thead>

                        <tbody>

                            <c:forEach var="appointment" items="${appointments}" >
                                <tr>
                                    <th scope="row">${appointment.getAppointmentID()}</th>
                                        <% if (userType.equals("admin")) {%>
                                    <td scope="row">${appointment.getPatient()}</th>
                                        <% }%>
                                    <td>${appointment.getAvailabilitySlot().getDoctorName()}</td>
                                    <td>${appointment.getAvailabilitySlot().getHospitalName()}</td>
                                    <td>${appointment.getAvailabilitySlot().getDate()}</td>
                                    <td>${appointment.getAvailabilitySlot().getTime()}</td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </body>

    <script>
        //Script used to change the availabilitySlotID hidden input field inside the dialog's form according to selected slot
        function change(value) {

            document.getElementById("modalAvailabilitySlotID").value = value;
            console.log(document.getElementById("modalAvailabilitySlotID").value);
        }

    </script>

    <script>

        $(document).ready(function () {
            $('#table_id').DataTable({"ordering": false});
        });
    </script>

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

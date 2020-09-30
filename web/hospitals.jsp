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
                    <div class="card-header">Hospitals</div>
                </div>


                <!--Table to display pending users -->
                <div class="card" style="padding: 20px;">
                    <table class="table table-striped" id="table_id">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Hospital ID</th>
                                <th scope="col">Hospital Name</th>
                                <th scope="col">Address</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>

                            <c:forEach var="hospital" items="${hospitals}" >
                                <tr>
                                    <th scope="row">${hospital.getHospitalID()}</th>
                                    <td>${hospital.getName()}</td>
                                    <td>${hospital.getAddress()}</td>
                                    <td>
                                        <!-- Only show remove button to admins -->
                                        <% String user = (String) session.getAttribute("typeOfUser");
                                            if (user.equals("admin")) {%>
                                        <a title="Remove Hospital" href="" data-toggle="modal" data-target="#removeHospitalModal" onclick="change(${hospital.getHospitalID()})">
                                            <i class="fas fa-trash-alt"></i>
                                        </a>
                                        <%}%>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

                <!-- Confirmation modal for removing a doctor -->
                <div
                    class="modal fade"
                    id="removeHospitalModal"
                    tabindex="-1"
                    role="dialog"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">
                                    Remove Hospital
                                </h5>
                                <button
                                    type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close"
                                    >
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>



                            <div class="modal-body">
                                <p>Are you sure you want to remove this hospital?</p>
                                <form action="RemoveHospitalController">
                                    <input hidden name="hospitalID" id="modalHospitalID">
                                    <button type="button" class="btn btn-outline-primary" data-dismiss="modal">
                                        Close
                                    </button>
                                    <button class="btn btn-outline-primary" type="submit" value="submit">
                                        Remove Hospital
                                    </button>
                                </form>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>

    <script>
        //Script used to change the hospitalID hidden input field inside the dialog's form according to selected hospital
        function change(value) {

            document.getElementById("modalHospitalID").value = value;
            console.log(document.getElementById("modalHospitalID").value);
        }

    </script>

    <script>

        $(document).ready(function () {
            $('#table_id').DataTable();
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

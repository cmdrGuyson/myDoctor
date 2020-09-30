<%-- 
    Document   : doctos
    Created on : Aug 12, 2020, 9:14:26 PM
    Author     : Ebisu
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Doctor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
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
        <link rel="stylesheet" href="css/home.css" />
        <link rel="stylesheet" href="css/doctors.css" />
        <link rel="icon" href="images/logo.png" />
        <title>myDoctor</title>
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
                        style="margin-top: 300px; font-size: 0.8rem;"
                        >
                        <p>
                            Made by Gayanga Kuruppu
                        </p>
                    </div>
                </div>
            </div>



            <div class="main">

                <% if (request.getAttribute("select").toString().equals("select")) { %>
                <div class="alert alert-warning" role="alert">
                    Please select the doctor you wish to channel!
                </div>
                <%}%>

                <div class="card" style="margin-bottom: 20px; position: relative;">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-md-8" style="padding: 10px;">Filter & Search</div>
                            <div class="col-md-4">
                                <form class="form-inline" action="SearchDoctorController" method="GET">
                                    <div class="input-group">
                                        <input
                                            class="form-control"
                                            type="search"
                                            placeholder="Search"
                                            aria-label="Search"
                                            name="searchKey"
                                            required
                                            />
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-primary" type="submit">
                                                <i class="fas fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div
                        class="card-body"
                        style="padding-right: 1.25rem !important; align-items: center;"
                        >
                        <form class="form-inline" action="SortDoctorController" method="GET">
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">
                                                <i class="fas fa-user-md"></i>
                                            </span>
                                        </div>
                                        <select
                                            class="custom-select form-control"
                                            id="inputType"
                                            name="type"
                                            aria-placeholder="Type: "
                                            >
                                            <option value='general' selected>General</option>
                                            <option value='childDoctor'>Children's Doctor</option>
                                            <option>Allergist</option>
                                            <option>Dermatologist</option>
                                            <option>Gynecologist</option>
                                            <option>Cardiologist</option>
                                            <option>Urologist</option>
                                            <option>Psychiatrist</option>
                                            <option>Radiologist</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">
                                                <i class="fas fa-filter"></i>
                                            </span>
                                        </div>
                                        <select
                                            class="custom-select form-control"
                                            id="filter"
                                            name="order"
                                            >
                                            <option selected>Ascending</option>
                                            <option>Descending</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2" style="padding-left: 50px;">
                                    <button
                                        type="submit"
                                        class="btn btn-outline-primary"
                                        style="width: 100px;"
                                        >
                                        Filter
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <% List<Doctor> doctors_list = (List<Doctor>) request.getAttribute("doctors");
                    if (doctors_list.size() != 0) {
                        //If there are doctors
                %>

                <c:forEach var="doctor" items="${doctors}">

                    <% Doctor doctor = (Doctor) pageContext.getAttribute("doctor"); %>

                    <div class="card" style="margin-bottom: 20px; position: relative;">
                        <div class="card-body" style="padding-bottom: 20px; padding-top: 10px">
                            <div class="row" style="position: relative;">
                                <div class="col">
                                    <img
                                        src="${doctor.getImageURL()}"
                                        alt="Avatar"
                                        class="doctor-image"
                                        />
                                </div>
                                <div class="col-9 doc-card">
                                    <h5 class="card-title">${doctor.getFirstName()} ${doctor.getLastName()}</h5>
                                    <% if (doctor.getSpecialization() == null) { %>
                                    <p class="posted-time-p" style="margin-bottom: 5px">General Doctor <% if (doctor.isChildDoctor()) { %>(Children's Doctor)<%}%></p>
                                    <%} else {%>
                                    <p class="posted-time-p" style="margin-bottom: 5px">${doctor.getSpecialization()}</p>
                                    <%}%>
                                    <% if (userType.equals("admin")) { %>
                                    <span class="badge badge-pill badge-info" style="padding: 5px">${doctor.getContactNumber()}</span>
                                    <% } %>
                                </div>

                            </div>
                            <!-- Only show remove button to admins -->
                            <% String user = (String) session.getAttribute("typeOfUser");

                                if (user.equals("admin")) {%>
                            <button class="btn btn-outline-primary" data-toggle="modal" data-target="#removeDoctorModal" onclick="change(${doctor.getDoctorID()})" style="position: absolute; top: 60%; right: 5%" title="Remove Doctor">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                            <%} else {%>
                            <!-- Show channel button to registered users -->
                            <form action="ViewAvailableSlotsController">
                                <input hidden name="doctorID" value="${doctor.getDoctorID()}" />
                                <button class="btn btn-outline-primary" style="position: absolute; top: 10%; right: 5%;" title="Make Appointment" type="submit">
                                    <i class="fas fa-plus-circle" style="margin-right: 5px"></i> Channel Doctor
                                </button>
                            </form>
                            <% }%>
                        </div>
                    </div>
                </c:forEach>

                <%} else {%>
                <div class="alert alert-danger" role="alert">
                    No doctors found!
                </div>
                <%}%>

                <!-- Confirmation modal for removing a doctor -->
                <div
                    class="modal fade"
                    id="removeDoctorModal"
                    tabindex="-1"
                    role="dialog"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">
                                    Remove Doctor
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
                                <p>Are you sure you want to remove this doctor?</p>
                                <form action="RemoveDoctorController">
                                    <input hidden name="doctorID" id="modalDoctorID">
                                    <button type="button" class="btn btn-outline-primary" data-dismiss="modal">
                                        Close
                                    </button>
                                    <button class="btn btn-outline-primary" type="submit" value="submit">
                                        Remove Doctor
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
        //Script used to change the doctorID hidden input field inside the dialog's form according to selected doctor
        function change(value) {

            document.getElementById("modalDoctorID").value = value;
            console.log(document.getElementById("modalDoctorID").value);
        }

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

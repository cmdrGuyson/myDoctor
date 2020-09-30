<%-- 
    Document   : add-slot
    Created on : Sep 7, 2020, 10:00:28 PM
    Author     : Ebisu
--%>

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
        <link rel="stylesheet" href="css/add-doc.css" />
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
                        onclick="location.href='SelectDoctorController'"
                        >
                        <h3 class="logo-text">myDoctor</h3>

                        <p style="position: absolute; bottom: -20%;">Administrator</p>
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
                <div class="card" style="margin-bottom: 20px; position: relative;">
                    <div class="card-header">
                        Add Availability Slot
                    </div>
                    <div class="card-body">
                        <div class="form-body">
                            <form method="POST" action="AddAvailabilityController">
                                <div class="form-row">
                                    <label for="inputDoctor" style="margin-top: 20px; margin-bottom: 5px">Doctor</label>

                                    <select id="inputDoctor" class="form-control" name="doctor">
                                        <c:forEach var="doctor" items="${doctors}">
                                            <option value='${doctor.getDoctorID()}'>${doctor.getFirstName()} ${doctor.getLastName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <label for="inputHospital" style="margin-top: 20px; margin-bottom: 0px">Hospital</label>
                                    <select id="inputHospital" class="form-control" name="hospital">
                                        <c:forEach var="hospital" items="${hospitals}">
                                            <option value='${hospital.getHospitalID()}'>${hospital.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <label for="example-date-input">Date</label>
                                    <input
                                        class="form-control"
                                        type="date"
                                        value="2020-09-11"
                                        id="example-date-input"
                                        required
                                        name="date"
                                        />
                                </div>
                                <div class="form-row">
                                    <label for="example-date-input">Time</label>
                                    <input
                                        class="form-control"
                                        type="time"
                                        id="time-input"
                                        required
                                        value="08:30"
                                        name="time"
                                        />
                                </div>
                                <hr />
                                <button class="btn btn-primary" type="submit" value="submit">
                                    Add Availability Slot
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

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
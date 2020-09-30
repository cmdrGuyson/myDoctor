<%-- 
    Document   : add-doctor
    Created on : Aug 11, 2020, 3:45:07 PM
    Author     : Ebisu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        Add A Doctor
                    </div>
                    <div class="card-body">
                        <div class="form-body">
                            <form method="POST" action="AddDoctorController" enctype="multipart/form-data" oninput='
                                  contactNumber.setCustomValidity(!contactNumber.value.match(/^\d{10}$/) ? "Please input valid contact number." : "");
                                  '
                                  >
                                <div class="form-row">
                                    <label for="inputType" style="margin-top: 20px; margin-bottom: 5px">Type of Doctor</label>
                                    <select id="inputType" class="form-control" name="type" onchange="hide()">
                                        <option value='general' selected>General Doctor</option>
                                        <option value='special'>Specialist</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <label for="firstName">First Name</label>
                                    <input
                                        class="form-control"
                                        id="firstName"
                                        name="firstName"
                                        required
                                        />
                                    <label for="lastName">Last Name</label>
                                    <input
                                        class="form-control"
                                        id="lastName"
                                        name="lastName"
                                        required
                                        />
                                    <label for="contactNumber">Contact Number</label>
                                    <input
                                        class="form-control"
                                        id="contactNumber"
                                        name="contactNumber"
                                        required
                                        />
                                </div>
                                <label for="inputImageFile">Upload Image</label>
                                <div class="custom-file" id="inputImageFile">
                                    <input
                                        type="file"
                                        accept=".png, .jpeg, .jpg"
                                        class="custom-file-input"
                                        id="inputImage"
                                        name="postImage"
                                        required
                                        />
                                    <label class="custom-file-label" for="inputGroupFile01"
                                           >Select Image</label
                                    >
                                </div>
                                <div id='childDiv'>
                                    <label for="isChildDoctor" style="margin-top: 20px; margin-bottom: 0px">Children's Doctor</label>
                                    <select id="isChildDoctor" class="form-control" name="isChildDoctor">
                                        <option selected value="no">No</option>
                                        <option value="yes">Yes</option>
                                    </select>
                                </div>
                                <div id='specialDiv' style='display:none'>
                                    <label for="inputSpecialization" style="margin-top: 20px; margin-bottom: 0px">Specialization</label>
                                    <select id="inputSpecialization" class="form-control" name="specialization">
                                        <option selected>Allergist</option>
                                        <option>Dermatologist</option>
                                        <option>Gynecologist</option>
                                        <option>Cardiologist</option>
                                        <option>Urologist</option>
                                        <option>Psychiatrist</option>
                                        <option>Radiologist</option>
                                    </select>
                                </div>
                                <hr />
                                <button class="btn btn-primary" type="submit" value="submit">
                                    Add Doctor
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script>
        //Script to change form based on type of doctor
        function hide() {
            var e = document.getElementById("inputType");
            var type = e.options[e.selectedIndex].value;


            if (type === "general") {
                // If doctor is a genenral doctor show child doctor field and hide specialization field
                document.getElementById("childDiv").style.display = 'block';
                document.getElementById("specialDiv").style.display = 'none';
            } else {
                // Else do the opposite
                document.getElementById("childDiv").style.display = 'none';
                document.getElementById("specialDiv").style.display = 'block';
            }
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

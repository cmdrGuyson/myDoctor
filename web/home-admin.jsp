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
        <link rel="icon" href="images/logo.png" />
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

                <div style="margin-top: 50px; padding-left: 40px;">
                    <a href="SendHomeController"><i class="fas fa-home selected-nav"></i>Home</a>
                    <a href="ViewAllAppointmentsController"><i class="far fa-calendar-alt"></i>Appointments</a>
                    <a href="ViewAllDoctorsController"><i class="fas fa-user-md"></i>Doctors</a>
                    <a href="ViewAllHospitalsController"><i class="fas fa-hospital"></i>Hospitals</a>
                    <a href="ViewAllAvailabilitySlotsController"><i class="far fa-clock"></i>Availability Slots</a>
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
                <!--Manage Doctors Card-->
                <div class="card" style="margin-bottom: 20px; position: relative;">
                    <div class="card-header">
                        Manage Doctors
                    </div>
                    <a
                        href="add-doctor.jsp"
                        type="button"
                        class="btn btn-primary"
                        style="
                        align-items: center;
                        position: absolute;
                        top: 20%;
                        right: 10%;
                        width: 200px;
                        padding-top: 14px;
                        "
                        >
                        <i
                            class="fas fa-plus-circle"
                            style="font-size: 1.2rem; margin-right: 10px;"
                            ></i
                        >Add Doctor
                    </a>
                    <a
                        href="ViewAllDoctorsController"
                        type="button"
                        class="btn btn-primary"
                        style="
                        align-items: center;
                        position: absolute;
                        top: 35%;
                        right: 10%;
                        width: 200px;
                        "
                        >
                        <i
                            class="fas fa-minus-circle"
                            style="font-size: 1.2rem; margin-right: 10px; position: relative; margin-top: 7px;"
                            ></i
                        >Remove Doctor
                    </a>
                    <img
                        src="images/back_1.jpg"
                        style="object-fit: cover; height: 400px;"
                        />
                </div>

                <!-- Manage Hospitals Card -->
                <div class="card" style="margin-bottom: 20px; position: relative;">
                    <div class="card-header">
                        Manage Hospitals
                    </div>
                    <button
                        type="button"
                        class="btn btn-primary"
                        style="
                        align-items: center;
                        position: absolute;
                        top: 20%;
                        left: 10%;
                        width: 200px;
                        "
                        data-toggle="modal" data-target="#addHospitalModal"
                        >
                        <i
                            class="fas fa-plus-circle"
                            style="font-size: 1.2rem; margin-right: 10px;"
                            ></i
                        >Add Hospital
                    </button>
                    <a
                        href="ViewAllHospitalsController"
                        type="button"
                        class="btn btn-primary"
                        style="
                        align-items: center;
                        position: absolute;
                        top: 35%;
                        left: 10%;
                        width: 200px;
                        "
                        >
                        <i
                            class="fas fa-minus-circle"
                            style="font-size: 1.2rem; margin-right: 10px; position: relative; margin-top: 7px;"
                            ></i
                        >Remove Hospital
                    </a>
                    <img
                        src="images/back_3.jpg"
                        style="object-fit: cover; height: 400px;"
                        />
                </div>

                <!-- Manage Availability Card -->
                <div class="card" style="margin-bottom: 20px; position: relative;">
                    <div class="card-header">
                        Manage Availability
                    </div>
                    <a
                        type="button"
                        class="btn btn-primary"
                        href="DirectToAddSlotController"
                        style="
                        align-items: center;
                        position: absolute;
                        top: 20%;
                        right: 10%;
                        width: 250px;
                        "
                        >
                        <i
                            class="fas fa-plus-circle"
                            style="font-size: 1.2rem; margin-right: 10px; position: relative; margin-top: 7px;"
                            ></i
                        >Add Availability Slot
                    </a>
                    <a
                        type="button"
                        href="ViewAllAvailabilitySlotsController"
                        class="btn btn-primary"
                        style="
                        align-items: center;
                        position: absolute;
                        top: 35%;
                        right: 10%;
                        width: 250px;
                        "
                        >
                        <i
                            class="fas fa-minus-circle"
                            style="font-size: 1.2rem; margin-right: 10px; position: relative; margin-top: 7px;"
                            ></i
                        >Remove Availability Slot
                    </a>
                    <img
                        src="images/back_4.jpg"
                        style="object-fit: cover; height: 400px;"
                        />
                </div>
            </div>
        </div>

        <!-- Model/Dialog for adding Hospitals -->
        <div
            class="modal fade"
            id="addHospitalModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="manageGroupModalLabel"
            aria-hidden="true"
            >
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">
                            Add a hospital
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
                        <div class="card-body">
                            <div class="form-body">
                                <form method="POST" action="AddHospitalController">
                                    <div class="form-row">
                                        <label for="firstName">Hospital Name</label>
                                        <input
                                            class="form-control"
                                            id="name"
                                            name="name"
                                            required
                                            />
                                        <label for="address" style="margin-top: 10px">Address</label>
                                        <textarea
                                            class="form-control"
                                            id="postBodyTextArea"
                                            rows="4"
                                            required
                                            name="address"
                                            maxlength="255"
                                            ></textarea>
                                    </div>
                                    <hr />
                                    <button class="btn btn-primary" type="submit" value="submit">
                                        Add Hospital
                                    </button>
                                </form>
                            </div>
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

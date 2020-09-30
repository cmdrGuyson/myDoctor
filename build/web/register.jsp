
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap"
            rel="stylesheet"
            />
        <link rel="stylesheet" type="text/css" href="css/index.css" />
        <title>myDoctor</title>
    </head>
    <body style="background: rgb(203,255,227);
          background: radial-gradient(circle, rgba(203,255,227,1) 0%, rgba(142,255,227,1) 50%, rgba(75,255,202,1) 100%);">
        <div
            class="container"
            style="padding-left: 300px; padding-right: 300px; margin-bottom: 20px;"
            >
            <div class="card align-self-center" style="margin-top: 20px; width: 80%;">
                <div class="card-body" style="padding: 50px;">
                    <div style="text-align: center;">
                        <img
                            src="images/logo.png"
                            class="card-img-top"
                            alt=""
                            style="width: 50%;"
                            />
                        <h1 class="display-4" style="font-size: 3rem; margin: 30px;">
                            Register
                        </h1>
                    </div>
                    <form
                        class="form-signin"
                        method="POST"
                        action="RegisterController"
                        oninput='confirmPassword.setCustomValidity(confirmPassword.value != password.value ? "Passwords do not match." : ""); 
                        contactNumber.setCustomValidity(!contactNumber.value.match(/^\d{10}$/) ? "Please input valid contact number." : "");
                        NIC.setCustomValidity(!NIC.value.match(/^\d{9}(v|V)$/) ? "Please input valid NIC number" : "");
                        '
                        >
                        <br />
                        <div class="text-center">
                            <label for="inputEmail" class="sr-only">Email</label>
                            <input
                                type="email"
                                id="inputEmail"
                                class="form-control"
                                name="email"
                                placeholder="Email"
                                maxlength="50"
                                required
                                autofocus
                                />
                            <br />
                            <label for="inputPassword" class="sr-only">Password</label>
                            <input
                                type="password"
                                maxlength="255"
                                id="inputPassword"
                                class="form-control"
                                name="password"
                                placeholder="Password"
                                required
                                />
                            <br />
                            <label for="inputConfirmPassword" class="sr-only"
                                   >Confirm Password</label
                            >
                            <input
                                type="password"
                                id="inputConfirmPassword"
                                maxlength="255"
                                name="confirmPassword"
                                class="form-control"
                                placeholder="Confirm Password"
                                required
                                />
                            <br />
                            <label for="inputFirstName" class="sr-only">First Name</label>
                            <input
                                type="text"
                                id="inputFirstName"
                                class="form-control"
                                maxlength="50"
                                placeholder="First Name"
                                name="firstName"
                                required
                                autofocus
                                />
                            <br />
                            <label for="inputLastName" class="sr-only">Last Name</label>
                            <input
                                type="text"
                                id="inputLastName"
                                class="form-control"
                                maxlength="30"
                                name="lastName"
                                placeholder="Last Name"
                                autofocus
                                />
                            <br />
                            <label for="inputNumber" class="sr-only">Contact Number</label>
                            <input
                                type="text"
                                id="inputNumber"
                                class="form-control"
                                maxlength="30"
                                name="contactNumber"
                                placeholder="Contact Number"
                                autofocus
                                />
                            <br />
                            <label for="NIC" class="sr-only">NIC Number</label>
                            <input
                                type="text"
                                id="NIC"
                                class="form-control"
                                maxlength="30"
                                name="NIC"
                                placeholder="NIC Number"
                                autofocus
                                />
                            <br />

                            <button class="btn btn-md btn-primary btn-block" type="submit">
                                Register
                            </button>
                            <p class="mt-5 mb-3 text-muted">
                                Already have an account? <a href="index.jsp" style="color: #32bea6;">Login!</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>

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
    </body>
</html>

<%-- 
    Document   : index
    Created on : Jul 30, 2020, 11:48:14 AM
    Author     : Ebisu
--%>

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
        <div class="container" style="padding-left: 300px; padding-right: 300px;">
            <div class="card align-self-center" style="margin-top: 70px; width: 80%;">
                <div class="c   ard-body" style="padding: 50px;">
                    <div style="text-align: center;">
                        <img
                            src="images/logo.png"
                            class="card-img-top"
                            alt=""
                            style="width: 50%;"
                            />
                        <h1 class="display-4" style="font-size: 3rem; margin: 30px;">
                            Login
                        </h1>
                    </div>
                    <form class="form-signin" method="POST" action="LoginController">
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

                            <button class="btn btn-md btn-primary btn-block" type="submit">
                                Login
                            </button>
                            <p class="mt-5 mb-3 text-muted">
                                Don't have an account? <a href="register.jsp" style="color: #32bea6;">Register!</a>
                            </p>
                        </div>
                    </form>

                    <% if ((String) request.getAttribute("registered") != null) { %>

                    <div class="alert alert-warning" role="alert">
                        Successfully Registered! Please Login!
                    </div>

                    <% }%>

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


<%-- 
    Document   : general_error
    Created on : Sep 8, 2020, 7:34:50 PM
    Author     : Ebisu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>myDoctor | Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />
    <link rel="stylesheet" type="text/css" href="css/index.css" />
    <link rel="stylesheet" type="text/css" href="css/errors.css" />
    <link rel="icon" href="images/logo.png" />
</head>

<body style="background-color: #a3d7f9;">

    <!--Content-->
    <div class="container container-home content">
        <div class="row">
            <div class="col-sm-12 col-md-12 col-xs-12 centerAlign">
                <div class="image-hack">
                    <img src="images/female-doc.gif" height="400" />
                </div>
            </div>
            <div class="col-sm-12 col-md-12 col-xs-12" style="text-align: center;background-color: #dee1e4;padding-top: 10px;border: solid 1px #dcdcd9;border-radius: 5px;font-weight: 400;color: #537186;">
                
                <%String message = (String) request.getAttribute("message"); %>
                
                <p style="font-size: 1.5rem;"><%=message%></p>
                <p> <a href="SendHomeController">Go back home?</a> </p>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>
</body>

</html>


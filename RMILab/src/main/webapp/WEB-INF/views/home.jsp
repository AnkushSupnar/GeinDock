<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="notification.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GeinDock</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.6.0/jszip.min.js"></script>

    <style>
        html, body {
            height: 100%;
            margin: 0;
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa; /* Light grey background */
            color: #333; /* Darker text for better readability */
        }

        .container-fluid {    
            padding: 20px;
            background-color: #E0EFFF; /* Light blue background */
        }

        .row.flex-fill {
            display: flex;
            flex-wrap: wrap; /* Allows columns to wrap on smaller screens */
        }

        .carousel-item img {
            width: 100%;
            height: auto;
            border-radius: 0.25rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .col-md-3, .col-md-6 {
            padding: 15px;
        }

        @media (max-width: 768px) {
            .col-12 {
                flex: 0 0 100%; /* Forces elements to take full width on smaller screens */
            }
        }

        @media (max-width: 576px) {
            .navbar-collapse {
                background-color: white;
            }
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }
    </style>
</head>
<body>
    <%@ include file="navbar.jsp"%>

    <div class="container-fluid">
        <div class="row flex-fill">
            <!-- Left Column -->
            <div class="col-12 col-md-3 d-flex flex-column">
                <h2>Welcome to <span class="highlight">Geinforce!</span></h2>
                <p>Dedicated to advancing scientific exploration, Geinforce provides innovative software solutions designed to accelerate drug discovery and research. Our suite of tools, including the acclaimed GeinDock Suite and forthcoming products like ForceADME and GeinPredictor, is crafted to enhance efficiency and drive breakthroughs in the scientific community. Join us in our journey to transform the landscape of scientific research, empowering you to achieve remarkable results.</p>
            </div>

            <!-- Middle Column -->
            <div class="col-12 col-md-6 d-flex flex-column">
                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="${pageContext.request.contextPath}/images/4.png" alt="Slide 1">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

            <!-- Right Column -->
            <div class="col-12 col-md-3 d-flex flex-column">
                <div class="row justify-content-start">
                    <div class="col-auto">
                        <p>Not registered? <a href="${pageContext.request.contextPath}/registerForm">Register here</a></p>
                    </div>
                </div>
                <h2>Login</h2>
                <form id="loginForm" action="${pageContext.request.contextPath}/api/login" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input type="email" class="form-control" id="email" name="username" placeholder="Enter email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Password" required>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="rememberMe">
                        <label class="form-check-label" for="rememberMe">Remember me</label>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="login()">Login</button>
                    <a href="#">Forgot password?</a>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp"%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script defer src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>

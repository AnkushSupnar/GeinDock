<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>

    <html>

    <head>
        <title>Registration Form </title>
        <style>
            body {
                background-color: #f1f1f1;
                font-family: Arial, sans-serif;
            }

            .container {
                max-width: 400px;
                background-color: #ffffff;
                margin: 0 auto;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input[type="text"],
            input[type="email"],
            input[type="password"] {
                width: 90%;
                padding: 10px;
                margin-bottom: 15px;
                border: none;
                border-radius: 3px;
            }

            input[type="submit"] {
                background-color: #007bff;
                color: #ffffff;
                font-weight: bold;
                padding: 12px;
                width: 100%;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #0056b3;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h1>Registration Form</h1>
            <form action="register" method="post">
                <label for="username">Enter Your Fullname:</label>
                <input type="text" id="username" name="username" required>
                <label for="email">Enter Your Email:</label>
                <input type="email" id="email" name="email" required>
                <input type="submit" value="Register">
            </form>
        </div>
    </body>

    </html>
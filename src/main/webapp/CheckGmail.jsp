<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify Password</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url('https://img.nagradion.ru/tune/small/newsline/95/02/29/newsi3evjyfs36.jpeg') no-repeat center center fixed;
            background-size: cover;
            color: white;
        }
        .card {
            background-color: rgba(0, 0, 0, 0.8);
            color: white;
            border: none;
        }
        .form-control {
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
            border: 1px solid #ccc;
        }
        .form-control:focus {
            background-color: rgba(255, 255, 255, 0.2);
            color: white;
            border: 1px solid #28a745;
        }
        .btn-custom {
            background-color: #28a745;
            color: white;
            border: none;
        }
        .btn-custom:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container vh-100 d-flex justify-content-center align-items-center">
    <div class="card p-4 shadow" style="width: 25rem;">
        <h3 class="text-center mb-4">Verification</h3>
        <form action="/verifyPassword" method="post">
            <div class="form-group">
                <label for="verificationCode">Enter Verification Code</label>
                <input type="text" class="form-control" id="verificationCode" name="verificationCode" placeholder="Enter the code" required>
            </div>
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
            <div class="alert alert-danger mt-3">
                <%= errorMessage %>
            </div>
            <% } %>
            <button type="submit" class="btn btn-custom btn-block mt-3">Verify</button>
        </form>
    </div>
</div>

</body>
</html>


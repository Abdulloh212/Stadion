<%--
  Created by IntelliJ IDEA.
  User: mastu
  Date: 08.12.2024
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url('https://fistf.com/wp-content/uploads/2017/08/cropped-UEFA-Champions-League-Stadium-Wallpaper.jpg') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
        }
        .login-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-card {
            padding: 30px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="login-card">
        <h3 class="text-center mb-4">Login</h3>
        <form action="/checkandsend" method="post">
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
            </div>
            <%
                String errorMessage = request.getParameter("error");
                if (errorMessage != null && errorMessage.equals("true")) {
            %>
            <div class="error-message">Incorrect email or password!</div>
            <%
                }
            %>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
        </form>
    </div>
</div>

</body>
</html>

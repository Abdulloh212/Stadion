<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.stadium.Entity.Stadium" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<%@ page import="uz.pdp.stadium.Repo.StadiumRepo" %><%--
  Created by IntelliJ IDEA.
  User: mastu
  Date: 08.12.2024
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stadiums</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="/Admin/AddStadium.jsp">
    <button class="btn btn-success">Add Stadium</button>
</form>
<form action="/Admin/Report.jsp">
    <button class="btn btn-dark">Report</button>
</form>
<div class="container mt-5">
    <h1 class="text-center mb-4">Stadiums</h1>
    <div class="row">
        <%
            StadiumRepo stadiumRepo=new StadiumRepo();
            List<Stadium> stadiums = stadiumRepo.findAll();
            for (Stadium stadium : stadiums) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="/file/get?id=<%=stadium.getCover().getId()%>" alt="rasm Topilmadi">
                <div class="card-body">
                    <h5 class="card-title"><%= stadium.getName() %></h5>
                    <h5 class="card-title"><%= stadium.getRegion() %></h5>
                    <h5 class="card-title"><%= stadium.getHourlyPrice() %></h5>
                    <p class="card-text"><%= stadium.getDescription() %></p>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>



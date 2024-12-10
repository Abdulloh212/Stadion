<%@ page import="uz.pdp.stadium.Repo.StadiumRepo" %>
<%@ page import="uz.pdp.stadium.Entity.Stadium" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.stadium.Repo.HoursRepo" %>
<%@ page import="uz.pdp.stadium.Repo.DaysRepo" %>
<%@ page import="uz.pdp.stadium.Entity.Days" %>
<%@ page import="uz.pdp.stadium.Entity.Hours" %><%--
  Created by IntelliJ IDEA.
  User: mastu
  Date: 09.12.2024
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StadiumRepo stadiumRepo = new StadiumRepo();
    List<Stadium> stadiumList = stadiumRepo.findAll();

    HoursRepo hoursRepo = new HoursRepo();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stadium Booking Report</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Stadium Booking Report</h2>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Stadium Name</th>
            <th>Image</th>
            <th>Occupied Hours</th>
            <th>Total Income (so'm)</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Stadium stadium : stadiumList) {
                DaysRepo daysRepo = new DaysRepo();
                Days days = daysRepo.findByStadiumId(stadium.getId());

                if (days != null) {
                    List<Hours> occupiedHours = hoursRepo.findByDataId(days.getId());
                    int occupiedCount = 0;
                    int totalIncome = 0;

                    for (Hours hour : occupiedHours) {
                        if (hour.isOccupied()) {
                            occupiedCount++;
                            totalIncome += stadium.getHourlyPrice();
                        }
                    }
        %>
        <tr>
            <td><%= stadium.getName() %></td>
            <td>
                <img src="/file/get?id=<%= stadium.getCover().getId() %>" alt="<%= stadium.getName() %>" class="img-fluid" style="max-width: 150px;">
            </td>
            <td><%= occupiedCount %> hours</td>
            <td><%= totalIncome %> so'm</td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>

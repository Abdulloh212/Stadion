<%@ page import="uz.pdp.stadium.Repo.StadiumRepo" %>
<%@ page import="uz.pdp.stadium.Entity.Stadium" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.stadium.Repo.RegionRepo" %>
<%@ page import="uz.pdp.stadium.Entity.Region" %><%--
  Created by IntelliJ IDEA.
  User: mastu
  Date: 08.12.2024
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Region's Stadiums</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .sidebar {
            background-color: #343a40;
            color: white;
            padding: 15px;
            border-radius: 10px;
        }
        .sidebar h2 {
            color: #28a745;
        }
        .region-list-item {
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .region-list-item button {
            text-align: left;
        }
        .stadium-card {
            border: 1px solid #e0e0e0;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .stadium-card img {
            border-radius: 10px 10px 0 0;
            height: 200px;
            object-fit: cover;
        }
        .stadium-card-body {
            padding: 15px;
        }
        .stadium-card-title {
            font-size: 1.2rem;
            color: #007bff;
        }
        .stadium-card-price {
            font-size: 1rem;
            color: #28a745;
            margin-bottom: 10px;
        }
        .stadium-card-btn {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            width: 100%;
            padding: 10px;
        }
        .stadium-card-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <div class="sidebar">
                <h2>Regions</h2>
                <ul class="list-group">
                    <%
                        RegionRepo regionRepo = new RegionRepo();
                        List<Region> allRegions = regionRepo.findAll();
                        if (allRegions != null && !allRegions.isEmpty()) {
                            for (Region region : allRegions) {
                    %>
                    <li class="list-group-item region-list-item">
                        <form action="/stadiumid.jsp" method="get">
                            <input type="hidden" name="id" value="<%= region.getId() %>">
                            <button class="btn btn-link text-white text-left w-100"><%= region.getName() %></button>
                        </form>
                    </li>
                    <%
                        }
                    } else {
                    %>
                    <li class="list-group-item text-danger">No regions available</li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>

        <div class="col-md-9">
            <h2 class="text-center mb-4">Region's Stadiums</h2>
            <div class="row">
                <%
                    String regionId = request.getParameter("id");
                    if (regionId != null) {
                        StadiumRepo stadiumRepo = new StadiumRepo();
                        List<Stadium> stadiums = stadiumRepo.findByRegionId(Integer.parseInt(regionId));
                        if (stadiums != null && !stadiums.isEmpty()) {
                            for (Stadium stadium : stadiums) {
                %>
                <div class="col-md-6 mb-4">
                    <div class="card stadium-card">
                        <img src="/file/get?id=<%= stadium.getCover().getId() %>" class="card-img-top" alt="<%= stadium.getName() %>">
                        <div class="card-body stadium-card-body">
                            <h5 class="card-title stadium-card-title"><%= stadium.getName() %></h5>
                            <p class="card-text stadium-card-price">
                                Price: <%= stadium.getHourlyPrice() %> so'm
                            </p>
                            <form action="/stadium.jsp" method="post">
                                <input type="hidden" name="id" value="<%= stadium.getId() %>">
                                <button class="btn stadium-card-btn">View Details</button>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <p class="text-danger text-center w-100">No stadiums available for this region</p>
                <%
                    }
                } else {
                %>
                <p class="text-danger text-center w-100">Please select a region to view its stadiums</p>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
</html>


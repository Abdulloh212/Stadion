<%@ page import="uz.pdp.stadium.Entity.Region" %>
<%@ page import="uz.pdp.stadium.Repo.RegionRepo" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Create Stadium</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center mb-4">Create Stadium</h1>
  <form action="/createStadium" method="POST" enctype="multipart/form-data" class="row g-3">
    <div class="col-md-6">
      <label for="name" class="form-label">Stadium Name</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>

    <div class="col-md-6">
      <label for="startTime" class="form-label">Start Time</label>
      <input type="time" class="form-control" id="startTime" name="startTime" required>
    </div>

    <div class="col-md-6">
      <label for="endTime" class="form-label">End Time</label>
      <input type="time" class="form-control" id="endTime" name="endTime" required>
    </div>

    <div class="col-md-12">
      <label for="description" class="form-label">Description</label>
      <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
    </div>

    <div class="col-md-6">
      <label for="hourlyPrice" class="form-label">Hourly Price</label>
      <input type="number" class="form-control" id="hourlyPrice" name="hourlyPrice" required>
    </div>
    <%
      RegionRepo regionRepo=new RegionRepo();
      List<Region> all = regionRepo.findAll();
    %>
    <div class="col-md-6">
      <label for="region" class="form-label">Region</label>
      <select class="form-select" id="region" name="region" required>
        <option value="" disabled selected>Select Region</option>
        <%
          for (Region region : all) {
        %>
        <option value="<%= region.getId() %>"><%= region.getName() %></option>
        <%
          }
        %>
      </select>
    </div>
    <div class="col-md-12">
      <label for="cover" class="form-label">Cover Image</label>
      <input type="file" class="form-control" id="cover" name="cover" accept="image/*" required>
    </div>

    <div class="col-12">
      <button type="submit" class="btn btn-primary">Create Stadium</button>
    </div>
  </form>
</div>

</body>
</html>


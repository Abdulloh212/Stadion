<%@ page import="uz.pdp.stadium.Entity.Stadium" %>
<%@ page import="uz.pdp.stadium.Repo.StadiumRepo" %>
<%@ page import="uz.pdp.stadium.Repo.HoursRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.stadium.Entity.Hours" %>
<%@ page import="uz.pdp.stadium.Repo.DaysRepo" %>
<%@ page import="java.util.Date" %>
<%@ page import="uz.pdp.stadium.Entity.Days" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    StadiumRepo stadiumRepo = new StadiumRepo();
    Stadium stadium = stadiumRepo.findById(id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= stadium != null ? stadium.getName() : "Stadium Booking" %></title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center"><%= stadium.getName() %> - Booking Page</h2>
    <div class="card">
        <div class="card-body">
            <div class="text-center mb-3">
                <img src="/file/get?id=<%=stadium.getCover().getId()%>" alt="<%= stadium.getName() %>" class="img-fluid" style="max-width: 500px;">
            </div>
            <div class="text-center mb-3">
                <p><strong>Description:</strong> <%= stadium.getDescription() %></p>
            </div>
            <form action="/bookStadium" method="post">
                <input type="hidden" name="stadiumId" value="<%= stadium.getId() %>">
                <div class="row">
                    <%
                        DaysRepo daysRepo = new DaysRepo();
                        Days days = daysRepo.findByStadiumId(stadium.getId());
                        HoursRepo hoursRepo = new HoursRepo();
                        List<Hours> hoursList = hoursRepo.findByDataId(days.getId());
                        for (Hours hour : hoursList) {
                            boolean isOccupied = hour.isOccupied();
                    %>
                    <div class="col-md-2 mb-2">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="hours" value="<%= hour.getId() %>"
                                   id="hour_<%= hour.getId() %>" <%= isOccupied ? "disabled" : "" %>>
                            <label class="form-check-label <%= isOccupied ? "text-danger" : "" %>" for="hour_<%= hour.getId() %>">
                                <%= hour.getDate().getHours() %>:<%= hour.getDate().getMinutes() %>
                                <%= isOccupied ? "(Occupied)" : "" %>
                            </label>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <hr>
                <h4>Total Price: <span id="totalPrice">0</span> so'm</h4>
                <button type="submit" class="btn btn-primary btn-lg">Book Selected</button>
            </form>
        </div>
    </div>
</div>

<script>
    const hourlyPrice = <%= stadium.getHourlyPrice() %>;
    const checkboxes = document.querySelectorAll('input[type="checkbox"][name="hours"]');
    const totalPriceElement = document.getElementById('totalPrice');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', () => {
            let total = 0;
            checkboxes.forEach(box => {
                if (box.checked) {
                    total += hourlyPrice;
                }
            });
            totalPriceElement.textContent = total;
        });
    });
</script>

</body>
</html>


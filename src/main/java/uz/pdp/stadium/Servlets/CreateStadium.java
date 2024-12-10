package uz.pdp.stadium.Servlets;

import uz.pdp.stadium.Entity.*;
import uz.pdp.stadium.Repo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@MultipartConfig
@WebServlet("/createStadium")
public class CreateStadium extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String startTimeStr = req.getParameter("startTime");
        String endTimeStr = req.getParameter("endTime");
        String hourlyPriceStr = req.getParameter("hourlyPrice");
        String regionIdStr = req.getParameter("region");

        if (name == null || description == null || startTimeStr == null || endTimeStr == null ||
                hourlyPriceStr == null || regionIdStr == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
            return;
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date startTime, endTime;
        try {
            startTime = timeFormat.parse(startTimeStr);
            endTime = timeFormat.parse(endTimeStr);
        } catch (ParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid time format.");
            return;
        }


        int hourlyPrice = Integer.parseInt(hourlyPriceStr);
        int regionId = Integer.parseInt(regionIdStr);

        RegionRepo regionRepo = new RegionRepo();
        Region region = regionRepo.findById(regionId);

        if (region == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Region not found.");
            return;
        }


        Part filePart = req.getPart("cover");
        if (filePart == null || filePart.getSize() == 0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cover image is required.");
            return;
        }
        InputStream fileContent = filePart.getInputStream();
        byte[] imageBytes = fileContent.readAllBytes();


        Attachement attachment = new Attachement();
        attachment.setName(filePart.getSubmittedFileName());

        AttachementRepo attachmentRepo = new AttachementRepo();
        attachmentRepo.save(attachment);

        AttachementContent attachmentContent = new AttachementContent();
        attachmentContent.setAttachement(attachment);
        attachmentContent.setContent(imageBytes);

        AttachementContentRepo attachmentContentRepo = new AttachementContentRepo();
        attachmentContentRepo.save(attachmentContent);


        Stadium stadium = new Stadium();
        stadium.setName(name);
        stadium.setDescription(description);
        stadium.setRegion(region);
        stadium.setStartTime(startTime);
        stadium.setEndTime(endTime);
        stadium.setHourlyPrice(hourlyPrice);
        stadium.setCover(attachment);

        StadiumRepo stadiumRepo = new StadiumRepo();
        stadiumRepo.save(stadium);

        Days day = new Days();
        day.setDay(new Date());
        day.setStadium(stadium);

        DaysRepo daysRepo = new DaysRepo();
        daysRepo.save(day);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endTime);

        while (calendar.before(endCalendar) || calendar.equals(endCalendar)) {
            Hours hour = new Hours();
            hour.setDate(calendar.getTime());
            hour.setDay(day);

            HoursRepo hoursRepo = new HoursRepo();
            hoursRepo.save(hour);

            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }

        resp.sendRedirect("/Admin/AdminPage.jsp");
    }
}


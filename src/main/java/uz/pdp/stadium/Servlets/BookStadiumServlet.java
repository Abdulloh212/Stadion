package uz.pdp.stadium.Servlets;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import uz.pdp.stadium.Data.MyListener;
import uz.pdp.stadium.Entity.Hours;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookStadium")
public class BookStadiumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selectedHoursIds = req.getParameterValues("hours");
        String stadiumIdStr = req.getParameter("stadiumId");

        if (selectedHoursIds == null || stadiumIdStr == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
            return;
        }

        int stadiumId = Integer.parseInt(stadiumIdStr);

        try (EntityManager entityManager = MyListener.getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();
            for (String hourIdStr : selectedHoursIds) {
                int hourId = Integer.parseInt(hourIdStr);
                Hours hour = entityManager.find(Hours.class, hourId);
                if (hour != null && !hour.isOccupied()) {
                    hour.setOccupied(true);
                    entityManager.merge(hour);
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Booking failed");
            return;
        }

        resp.sendRedirect("/stadium.jsp?id=" + stadiumId);
    }
}



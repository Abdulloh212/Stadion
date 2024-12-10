package uz.pdp.stadium.Servlets;

import uz.pdp.stadium.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/verifyPassword")
public class VerifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer password = Integer.parseInt(req.getParameter("verificationCode"));
            HttpSession session = req.getSession();
            Integer password1 = (Integer) session.getAttribute("Password");
            User user =(User) session.getAttribute("user");
            if (password.equals(password1)) {
                if (user.getRole().equals("ADMIN")) {
                    resp.sendRedirect("/Admin/AdminPage.jsp");
                }else {
                resp.sendRedirect("/Main.jsp");
                }
            } else {
                session.removeAttribute("user");
                resp.sendRedirect("/login.jsp");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/login.jsp?error=invalidPasswordFormat");
        }

    }
}

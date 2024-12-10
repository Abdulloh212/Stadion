package uz.pdp.stadium.Servlets;

import uz.pdp.stadium.Data.SendMessage;
import uz.pdp.stadium.Entity.User;
import uz.pdp.stadium.Repo.UserRepo;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

@WebServlet("/checkandsend")
public class CheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserRepo userRepo = new UserRepo();
        User user = Objects.requireNonNullElse(userRepo.find(email, password),null);
        if (user == null) {
            resp.sendRedirect("/login.jsp");
        }else {
            try {
                Random rand = new Random();
                int random = rand.nextInt(9000) + 1000;
                HttpSession session = req.getSession();
                session.setAttribute("Password",random);
                session.setAttribute("user",user);
                SendMessage.sendMessage(email,random);
                resp.sendRedirect("/CheckGmail.jsp");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

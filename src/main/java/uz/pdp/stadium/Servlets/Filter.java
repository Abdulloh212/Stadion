package uz.pdp.stadium.Servlets;

import uz.pdp.stadium.Entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebFilter("/*")
public class Filter extends HttpFilter {
    static List<String> AdminPages=new ArrayList<>(List.of(
            "/Admin/AddStadium.jsp",
            "/Admin/AdminPage.jsp",
            "/Admin/Report.jsp",
            "/createStadium"
            ));
    static List<String> FreePages=new ArrayList<>(List.of(
            "/login.jsp",
            "/checkandsend",
            "/CheckGmail.jsp",
            "/verifyPassword"
            ));
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String currentURI = req.getRequestURI();
        if (FreePages.contains(currentURI)) {
            chain.doFilter(req, res);
            return;
        }

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }



        if (AdminPages.contains(currentURI)) {
            if ("ADMIN".equals(user.getRole())) {
                chain.doFilter(req, res);
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, res);
            }
            return;
        }

        chain.doFilter(req, res);
    }

}

package uz.pdp.stadium.Servlets;

import uz.pdp.stadium.Entity.AttachementContent;
import uz.pdp.stadium.Repo.AttachementContentRepo;
import uz.pdp.stadium.Repo.AttachementRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/file/get")
public class GetFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id =Integer.parseInt(req.getParameter("id"));
        AttachementContentRepo attachementContentRepo = new AttachementContentRepo();
        AttachementContent byId = attachementContentRepo.findById(id);
        resp.getOutputStream().write(byId.getContent());
    }
}

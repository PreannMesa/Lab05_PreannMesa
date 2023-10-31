package CRUD;

import dao.UserDAO;
import pojo.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "register", value = "/register")
public class Register extends HttpServlet {
    private List<User> users = new ArrayList<>();
    private boolean isExists = false;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Phuong thuc cua request " + req.getMethod());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        users = UserDAO.getInstance().selectAll();
        if (users.size() != 0) {
            users.forEach(user -> {
                if (user.getEmail().equals(email) || user.getName().equals(username))
                    isExists = true;
            });
        }

        if (isExists) {
            req.setAttribute("status", "failure");
            req.setAttribute("message", "Account already exists");
            req.getRequestDispatcher("/jsp/register.jsp?").forward(req, resp);
        } else {
            if(UserDAO.getInstance().insert(new User(username, email, password)))
                resp.sendRedirect("./login?status=success&message=Registration success");
            else
                resp.sendRedirect("./login?status=failure&message=Registration failed");
        }
    }
}

package CRUD;

import dao.UserDAO;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    List<User> users = new ArrayList<>();
    boolean isAccess = false;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // TODO Auto-generated method stub
        super.service(req, resp);
        System.out.println("Phuong thuc cua request " + req.getMethod());// tar về phuong thức tương ứng
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        String message = req.getParameter("message");
        if (status != null && message != null && !status.equals("") && !message.equals("")) {
            req.setAttribute("status", status);
            req.setAttribute("message", message);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        User user = UserDAO.getInstance().selectUser(username, password);
        if (user != null){
            Cookie cookieUsername = new Cookie("cookieUsername", null);
            if (remember != null) {
                cookieUsername.setValue(user.getName());
                cookieUsername.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(cookieUsername);
            }else {
                cookieUsername.setMaxAge(0);
            }
            resp.sendRedirect("./homeProducts?username="+username);
        } else{
            req.setAttribute("status", "failure");
            req.setAttribute("message", "Username or Password is not correct");
            req.getRequestDispatcher("/index.jsp?").forward(req, resp);
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

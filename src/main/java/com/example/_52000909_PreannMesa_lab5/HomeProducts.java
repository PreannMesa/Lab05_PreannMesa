package com.example._52000909_PreannMesa_lab5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "HomeProducts", value = "/homeProducts")
public class HomeProducts extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/homeProducts.jsp").forward(req, resp);
//        if (req.getParameter("delete") == null) {
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        }else {
//            int id = Integer.parseInt(req.getParameter("id"));
//            if (StudentDAO.getInstance().delete(id)) {
//                req.setAttribute("status", "success");
//                req.setAttribute("message", "Delete successful students with id:" + id);
//            } else {
//                req.setAttribute("status", "failure");
//                req.setAttribute("message", "Delete failed students");
//            }
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        String course = req.getParameter("course");
//        int fee = Integer.parseInt(req.getParameter("fee"));
//        Student student = new Student(name, course, fee);
//        if (StudentDAO.getInstance().insert(student)) {
//            req.setAttribute("status", "success");
//            req.setAttribute("message", "Add successful students");
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        }
    }

//    HTTP ko nhan method Delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//        if (StudentDAO.getInstance().delete(id)) {
//            req.setAttribute("status", "success");
//            req.setAttribute("message", "Delete successful students with id:" + id);
//        } else {
//            req.setAttribute("status", "failure");
//            req.setAttribute("message", "Delete failed students");
//        }
//        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

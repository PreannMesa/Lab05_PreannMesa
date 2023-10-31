package CRUD;

import dao.ProductDAO;
import pojo.Product;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "updateProduct", value = "/updateProduct")
public class UpdateProduct extends HttpServlet {
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
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        req.getRequestDispatcher("./jsp/updateProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status, message;
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        Product product = new Product(id, name, price);

        if (ProductDAO.getInstance().update(product)) {
            status = "success";
            message = "Update successful product with id:" + id;
        }else {
            status = "failure";
            message = "Update failed product";
        }
        resp.sendRedirect("./homeProducts?status=" + status + "&message=" + message);
//        req.getRequestDispatcher("./index.jsp").forward(req, resp);
    }
}

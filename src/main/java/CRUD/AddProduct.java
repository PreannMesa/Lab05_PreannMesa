package CRUD;

import dao.ProductDAO;
import pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addProduct", value = "/addProduct")
public class AddProduct extends HttpServlet {

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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status, message;
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Product product = new Product(name, price);

        if (ProductDAO.getInstance().insert(product)) {
            status = "success";
            message = "Add successful product with name=" + name;
        }else {
            status = "failure";
            message = "Add failed product with name=" + name;
        }
        resp.sendRedirect("./homeProducts?status=" + status + "&message=" + message);
    }
}

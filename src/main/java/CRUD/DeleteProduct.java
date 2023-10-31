package CRUD;

import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteProduct", value = "/deleteProduct")
public class DeleteProduct extends HttpServlet {

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
        String id = req.getParameter("id");
        String status, message;
        boolean isDelete = false;
        if (id != null) {
            isDelete = ProductDAO.getInstance().delete(Integer.parseInt(id));
        }

        if (isDelete) {
            status = "success";
            message = "Successfully deleted product with ID:" + id;
        } else {
            status = "failure";
            message = "Fail to delete iphone product with ID:" + id;
        }
        resp.sendRedirect("./homeProducts?status=" + status + "&message=" + message);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

<%--
  Created by IntelliJ IDEA.
  User: conghiale
  Date: 2/27/2023
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.ProductDAO" %>
<%@ page import="pojo.Product" %>
<%
    int id = Integer.parseInt(request.getAttribute("id").toString());
    Product product = ProductDAO.getInstance().selectById(id);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <title>Update</title>
</head>
<body>
    <div class="container">
        <h2 class="text-md-center">JSP servlet hibernate CRUD Student</h2>
        <div class="row mt-3">
            <div class="col-md-4" style="margin: auto">
                <form action="./updateProduct" method="POST">
                    <div class="mb-3">
                        <label for="id" class="form-label">ID</label>
                        <input type="text" class="form-control" id="id" name="id" readonly value="<%= product.getId() %>">
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter student's name" value="<%= product.getName() %>">
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="text" class="form-control" id="price" name="price" placeholder="Enter product's price" value="<%= product.getPrice() %>">
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                    <button class="btn btn-warning">Reset</button>
                </form>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function() {
            $('#btnReset').click(function() {
                $('#name').val('')
                $('#course').val('')
                $('#fee').val('')
            })
        })
    </script>
</body>
</html>

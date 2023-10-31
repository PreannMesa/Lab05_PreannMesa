<%--
  Created by IntelliJ IDEA.
  User: conghiale
  Date: 3/1/2023
  Time: 7:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="pojo.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDAO" %>

<% List<Product> products = ProductDAO.getInstance().selectAll();%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <title>HomeServlet</title>
</head>
<body>
    <div class="container-fluid">
        <h2 class="text-md-center">Product Management</h2>
        <div class="row mt-3">
            <div class="offset-1 col-md-4 pb-2 me-2" style="box-shadow: 3px 3px 30px 2px #888888; border-radius: 5px">
                <h2 class="text-md-center" style="color: chocolate">Add new product</h2>
                <form action="./addProduct" method="POST">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter product's name">
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="text" class="form-control" id="price" name="price" placeholder="Enter product's price">
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
            <div class="col-6">
                <h2 class="text-md-center" style="color: forestgreen">List products</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Edit</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody id="tableBody">
                        <% for (Product product : products) { %>
                            <tr>
                                <th scope="row"><%= product.getId() %></th>
                                <td><%= product.getName() %></td>
                                <td><%= product.getPrice() %></td>
<%--                                <td><a type="button" class="btn btn-sm btn-outline-success btnUpdate" href="./updateProduct?id=<%= product.getId()%>">Edit</a></td>--%>
                                <td><button class="btn btn-sm btn-outline-success btnUpdate">Edit</button></td>
                                <td><button class="btn btn-sm btn-outline-danger btnDelete">Delete</button></td>
                            </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
            <div class="offset-1 col-md-4">
                <div class="alert alert-success mt-3 fadeout" style="display: none">
                    <strong>Logged in successfully!</strong>
                </div>
                <div class="alert alert-danger mt-3 fadeout"style="display: none">
                    <strong>Login failed!</strong>
                </div>
            </div>
        </div>
    </div>

    <!-- Confirm delete -->
    <div class="modal" id="modalDelete">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Delete Product</h4>
                    <button type="button" class="close" data-bs-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body" id="bodyModalDelete">
                    Are you sure you want to delete the product
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" id="btnSendDelete">Delete</button>
                    <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
    <!-- End Confirm delete -->

    <!-- form send delete -->
    <form action="./deleteProduct" method="GET" class="formSendDelete" style="display: none">
        <input name="id" value="" id="idDeleteProduct">
    </form>

    <!-- Update user -->
    <div class="modal" id="modalUpdate">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action='./updateProduct' method="POST">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">UpdateProduct</h4>
                        <button type="button" class="close" data-bs-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="idUpdateProduct">ID</label>
                            <input value="000000" class="form-control" type="text" id="idUpdateProduct" name="id" readonly>
                        </div>
                        <div class="form-group">
                            <label for="nameUpdateProduct">Name</label>
                            <input value="" class="form-control" type="text" placeholder="Enter product's name" id="nameUpdateProduct" name="name">
                        </div>
                        <div class="form-group">
                            <label for="priceUpdateProduct">Price</label>
                            <input value="" class="form-control" type="text" placeholder="Enter product's price" id="priceUpdateProduct" name="price">
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" id="btnSendUpdate">Update</button>
                        <button type="button" class="btn btn-warning" id="btnReset">Reset</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- End Update user -->

    <script>
        let status, message, id, name, price, row
        $(document).ready(function() {
            function loadPage() {
                status = "<%= request.getAttribute("status")%>";
                message = "<%= request.getAttribute("message")%>";
                if (status === "null" || message === "null") {
                    status = "<%= request.getParameter("status")%>";
                    message = "<%= request.getParameter("message")%>";
                }
                console.log(status);
                console.log(message);

                if (status === 'success') {
                    $('.alert-success').text(message)
                    $('.alert-success').fadeIn(2000)
                    setTimeout(function() {$('.alert-success').fadeOut(2000)}, 3000)
                }
                if (status === 'failure') {
                    $('.alert-danger').text(message)
                    $('.alert-danger').fadeIn(2000)
                    setTimeout(function () {$('.alert-danger').fadeOut(2000)}, 3000)
                }
            }
            loadPage()
            $("#name").focus();

            $('.btnDelete').click(function (event) {
                // event.preventDefault()
                row = this.parentElement.parentElement.children
                id = row[0].innerText
                name = row[1].innerText

                let messBodyDelate = $('#bodyModalDelete').text() + name + " with ID:" + id
                $('#bodyModalDelete').text(messBodyDelate)
                $('#modalDelete').modal('show')
            })

            $('#btnSendDelete').off('click')
            $('#btnSendDelete').click(function (event) {
                // event.preventDefault()
                $('#idDeleteProduct').val(id)
                $('.formSendDelete').submit()
            })

            $('#btnReset').click(function(event) {
                event.preventDefault()
                $('#nameUpdateProduct').val('')
                $('#priceUpdateProduct').val('')
            })

            $('.btnUpdate').click(function (event) {
                // event.preventDefault()
                row = this.parentElement.parentElement.children
                id = row[0].innerText
                name = row[1].innerText
                price = row[2].innerText

                $('#idUpdateProduct').val(id)
                $('#nameUpdateProduct').val(name)
                $('#priceUpdateProduct').val(price)
                $('#modalUpdate').modal('show')
            })
        })
    </script>
</body>
</html>

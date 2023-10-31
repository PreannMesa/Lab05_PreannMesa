<%--
  Created by IntelliJ IDEA.
  User: conghiale
  Date: 3/1/2023
  Time: 7:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Register</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <h3 class="text-center text-secondary mt-5 mb-3">User Register</h3>
        <form class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light" action="./register" method="POST" id="formRegister" role="form">
          <div class="form-group mt-3">
            <label for="username">Username</label>
            <input id="username" type="text" class="form-control" placeholder="Your username" name="username">
          </div>
          <div class="form-group mt-3">
            <label for="email">Email</label>
            <input id="email" type="email" class="form-control" placeholder="Email" name="email">
          </div>
          <div class="form-group mt-3">
            <label for="password">Password</label>
            <input id="password" type="password" class="form-control" placeholder="Password" name="password">
          </div>
          <div class="form-group mt-3">
            <label for="confirmPassword">Confirm Password</label>
            <input id="confirmPassword" type="password" class="form-control" placeholder="Password" name="confirmPassword">
          </div>
          <div class="form-group mt-3">
            <button class="btn btn-success px-5" onclick="checkValid()">Register</button>
          </div>
          <div class="form-group mt-3">
            <p>Have an account? <a href="./login">an account</a></p>
          </div>
        </form>

        <div class="alert alert-success mt-3 fadeout" style="display: none">
          <strong>Logged in successfully!</strong>
        </div>
        <div class="alert alert-danger mt-3 fadeout"style="display: none">
          <strong>Login failed!</strong>
        </div>
        <div class="alert alert-warning mt-3 fadeout"style="display: none">
          <strong>Login failed!</strong>
        </div>
      </div>
    </div>
  </div>

  <style>
    span.has-error {
      color:red;
      font-size: small;
    }
  </style>

  <script>
    let status, message
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
        if (status === 'notFound') {
          $('.alert-warning').text(message)
          $('.alert-warning').fadeIn(2000)
          setTimeout(function () {$('.alert-warning').fadeOut(2000)}, 3000)
        }
      }
      loadPage()
      $("#name").focus();
    })
    $.validator.setDefaults({
      errorElement: 'span',
      errorClass: 'has-error',
    });
    function checkValid() {
      $("#formRegister").validate({
        rules: {
          username: {
            required: true,
          },
          email: {
            required: true,
            email: true
          },
          password: {
            required: true,
            minlength: 6
          },
          confirmPassword: {
            required: true,
            minlength: 6,
            equalTo: "#password"
          }
        },
        messages: {
          username: {
            required: "Please enter your username"
          },
          email: {
            required: "Please enter your email",
            email: "Email invalid"
          },
          password: {
            required: "Please enter your password",
          },
          confirmPassword: {
            required: "Please enter your confirm password",
            equalTo: "Invalid password"
          }
        },
        submitHandler: function(form) {
          form.submit();
        }
      })
    }
  </script>
</body>
</html>

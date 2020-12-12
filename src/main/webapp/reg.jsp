<%@ page import="store.Store" %>
<%@ page import="store.HbmStore" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">

<!------ Include the above in your HEAD tag ---------->

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="./job4j_dreamjob/src/main/webapp/style/reg.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>To Do List</title>

    <script>
        function validate() {
            var name = document.forms["reg-form"]["Name"].value;
            var email = document.forms["reg-form"]["Email Address"].value;
            var pwd = document.forms["reg-form"]["Password"].value;
            if (name == "") {
                alert("Please enter your name");
                return false;
            }
            if (email == "") {
                alert("Please enter your e-mail");
                return false;
            }
            if (pwd == "") {
                alert("Please enter your password");
                return false;
            }
            return true;
        }
    </script>

</head>
<div class="row justify-content-center align-items-center" id="login">
    <section id="inner-wrapper" class="login">
        <article>
            <form action="<c:url value='/reg.do'/>" method="post" name="reg-form">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"> </i></span>
                        <input type="text" class="form-control" placeholder="Name" name="Name">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope"> </i></span>
                        <input type="email" class="form-control" placeholder="Email Address" name="Email Address">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-key"> </i></span>
                        <input type="password" class="form-control" placeholder="Password" name="Password">
                    </div>
                </div>
                <button type="submit" class="btn btn-success btn-block" onclick="return validate()";>Submit</button>
            </form>
        </article>
    </section>
</div>
</body>
</html>
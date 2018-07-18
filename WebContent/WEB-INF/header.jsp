<%@ page import="dao.UserDao" %>
<%@ page import="Bean.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Bzklink - Shorts URL</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <link href="css/main.css" rel="stylesheet" type="text/css">

</head>
<body>
<!-- Navigation -->
<%
    String loginError = (String) request.getAttribute("login_error");

    if (loginError != null && !loginError.isEmpty()) {
%>
<div class="alert alert-danger"><%= loginError %></div>
<%
    }
%>

<nav class="navbar navbar-light bg-light static-top">
    <div class="container">
        <a class="navbar-brand" href="/">Bzklink</a>
        <div>
            <%
                User user = (User) session.getAttribute("user");

                if (user == null) {
            %>
            <form class="navbar-form navbar-left" action="/login" method="post" style="display:inline-block;">
                <input type="email" name="email" class="" placeholder="email">
                <input type="password" name="password" class="" placeholder="password">
                <button type="submit" class="btn btn-success side-margins">Login</button>
            </form>
            <a href="/sign_up" class="btn btn-primary side-margins">Sign Up</a>
            <%
                } else {
            %>
                Hello <a href="#"><%= user.getLogin() %></a>
                <div class="btn-group">
                    <button type="button" class="btn btn-outline-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        My account
                    </button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a href="/account" class="dropdown-item disabled">Account</a>
                        <a href="/links" class="dropdown-item disabled">My URLs</a>
                    </div>
                </div>
                <a href="/logout" class="btn btn-outline-danger">Logout</a>
            <%
                }
            %>
        </div>

    </div>
</nav>

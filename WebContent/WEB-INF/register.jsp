<jsp:include page="header.jsp"/>

<!-- Masthead -->
<header class="masthead text-white text-center">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-xl-9 mx-auto">
                <h1 class="mb-5">Register</h1>
            </div>
            <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
                <%
                    String error = (String) request.getAttribute("error");
                    String success = (String) request.getAttribute("success");

                    if (error != null && !error.isEmpty() && error == "Form errors") {
                %>
                <div class="alert alert-danger text-left">
                    <p><%= error %></p>
                    <ul>
                        <li>Pseudo > 6 char</li>
                        <li>Email > 8 char</li>
                        <li>Password > 6 char</li>
                        <li>Type : not empty</li>
                    </ul>
                </div>
                <%
                } else if (error != null && !error.isEmpty()) {
                %>
                <div class="alert alert-warning text-left"><%= error %></div>
                <%
                } else if (success != null && !success.isEmpty()) {
                %>
                <div class="alert alert-success text-left"><%= success %></div>
                <%
                    }
                %>

                <form action='/sign_up' method='POST'>
                    <div class="form-row">
                        <div class="form-group col-12">
                            <input type="text" name="login" class="form-control form-control-lg" placeholder="pseudo">
                        </div>
                        <div class="form-group col-12">
                            <%
                                String email = (String) request.getAttribute("email");

                                if (email != null && !email.isEmpty()) {
                            %>
                            <input type="email" name="email" class="form-control form-control-lg" placeholder="john@doe.com" value="<%= email %>">
                            <%
                                } else {
                            %>
                            <input type="email" name="email" class="form-control form-control-lg" placeholder="john@doe.com">
                            <%
                                }
                            %>
                        </div>
                        <div class="form-group col-12">
                            <input type="password" name="password" class="form-control form-control-lg" placeholder="*********">
                        </div>

                        <div class="form-group col-12">
                            <select class="form-control" name="type">
                                <option value="person">Particulier</option>
                                <option value="company">Entreprise</option>
                                <option value="association">Association</option>
                            </select>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary">Create my account</button>
                        </div>
                    </div>
                </form>


                <%
                    String link = (String) request.getAttribute("link");

                    if (link != null && !link.isEmpty()) { %>
                <div>
                    <p>Great your link is ready ! <br>
                        <a href="<%= link %>"><%= link %>
                        </a>
                    </p>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
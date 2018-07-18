<%@ page import="Bean.links.Complex" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="header.jsp"/>

<!-- Masthead -->
<header class="masthead text-white text-center">
    <% ArrayList<Complex> complex = (ArrayList) request.getAttribute("complexe_link"); %>

    <div class="container">
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Origin</th>
                    <th scope="col">Target</th>
                    <th scope="col">Enabled</th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
                </tr>
                </thead>
                <tbody>

                <% for (int i=0; i < complex.size(); i++) { %>
                <tr>
                    <td><%=complex.get(i).getOrigin()%></td>
                    <td>http://localhost:8080/<%=complex.get(i).getTarget()%></td>
                    <td><%=complex.get(i).getEnabled()%></td>
                    <td><%=complex.get(i).getDateAvailable()%></td>
                    <td><%=complex.get(i).getExpiration()%></td>
                </tr>
                <% } %>

                </tbody>
            </table>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
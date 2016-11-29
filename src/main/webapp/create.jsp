<%--
  Created by IntelliJ IDEA.
  Date: 07/10/2016
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Customer</title>
    <jsp:include page="bootstrap/base.jsp" />
</head>
<body>
<div class="container">
    <jsp:include page="bootstrap/nav.jsp" />

    <div class="error">
        <p class="error">
            <% if (request.getAttribute("error") != null){%><%=request.getAttribute("error")%><%}%>
        </p>
    </div>


    <form method="post" action="/mre/customer">
        <div class="form-group">
            <label for="first">FirstName:</label>
            <input type="text" class="form-control" id="first" name="first_name" value="<% if (request.getAttribute("first_name") != null){%><%=request.getAttribute("first_name")%><%}%>">
        </div>
        <div class="form-group">
            <label for="last">LastName:</label>
            <input type="text" class="form-control" id="last" name="last_name" value="<% if (request.getAttribute("last_name") != null){%><%=request.getAttribute("last_name")%><%}%>">
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" name="phone" value="<% if (request.getAttribute("phone") != null){%><%=request.getAttribute("phone")%><%}%>">
        </div>

        <div class="form-group">
            <label for="email">Email address:</label>
            <input type="text" class="form-control" id="email" name="email" value="<% if (request.getAttribute("email") != null){%><%=request.getAttribute("email")%><%}%>">
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>



</body>
</html>

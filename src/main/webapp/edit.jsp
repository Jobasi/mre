<%--
  Created by IntelliJ IDEA.
  User: 
  Date: 12/10/2016
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
    <jsp:include page="bootstrap/base.jsp" />
</head>
<body>
<div class="container">
    <jsp:include page="bootstrap/nav.jsp" />

    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <h1> Edit Customer Data </h1>
        </div>
        <div class="col-lg-3"></div>

    </div>


    <div class="row">
            <div class="col-lg-12 error">
                <p class="error">
                    <% if (request.getAttribute("error") != null){%><%=request.getAttribute("error")%><%}%>
                </p>
            </div>
            
	
    </div>

    <div class="row">
        <div class="col-lg-12">
            <form method="post" action="/mre/edit">
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
                
                <div class="form-group">
                    <label for="cid"></label>
                    <input type="hidden" class="form-control" id="cid" name="cid" value="<% if (request.getAttribute("cid") != null){%><%=request.getAttribute("cid")%><%}%>">
                </div>
                

                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>


</div>
</body>
</html>

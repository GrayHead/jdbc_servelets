<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

        <form action="/jdbc_servelets_war_exploded/save" method="post">
            <input type="text" name="userName">
            <input type="text" name="userAge">
            <button>save</button>

        </form>

        <c:forEach items="${users}" var="user">
            <div>${user.getId()}</div>
            <div>${user.getName()}</div>
            <div>${user.getAge()}</div>

        </c:forEach>


    </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/10/2020
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div align="center">
    <h2>List of Product</h2>
    <table border="1" cellpadding="5">
        <tr>
            <td>NAME</td>
            <td>GIA</td>
            <td>SO LUONG</td>
            <td>MAU SAC</td>
            <td>MO TA</td>
            <td>DANH MUC</td>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>
                    <c:out value="${product.getName()}"/>
                </td>
                <td>
                    <c:out value="${product.getPrice()}"/>
                </td>
                <td>
                    <c:out value="${product.getQuantity()}"/>
                </td>
                <td>
                    <c:out value="${product.getColor()}"/>
                </td>
                <td>
                    <c:out value="${product.getDanh_muc()}"/>
                </td>
                <td>
                    <c:out value="${product.getDescription()}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
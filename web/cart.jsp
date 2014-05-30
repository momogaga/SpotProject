<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Java Servlets and JSP</title>
    </head>
    <body>

        <h1>Your cart</h1>

        <table border="1" cellpadding="5">
            <tr>
                <th>Artiste</th>
                <th>Titre</th>
                <th>Price</th>
            </tr>

            <c:forEach var="item" items="${cart.items}">
                <tr valign="top">
                    <td>${item.morceau.titre}</td>
                    <td>${item.morceau.prix}$</td>
                    <td>
                        <form action="cart">
                            <input type="hidden" name="productCode" value="${item.morceau.id}">
                            <input type="hidden" name="quantity" value="0"> 
                            <input type="submit" value="Remove Item">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <br>

        <form action="<c:url value='/index.jsp' />" method="post"><input
                type="submit" value="Continue Shopping"></form>

    </body>
</html>

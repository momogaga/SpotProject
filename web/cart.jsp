<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>ShoppingCart</title>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">


    </head>
    <body>

        <h1>Votre panier</h1>

        <table class="table table-striped">
            <tr>
                <th>Titre</th>
                <th>Artiste</th>
                <th>Prix</th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${cart.items}">
                <tr valign="top">
                    <td>${item.morceau.titre}</td>
                    <td>${item.morceau.artiste.nom}</td>
                    <td>${item.morceau.prix}$</td>
                    <td>
                        <form action="SessionPanier">
                            <input type="hidden" name="productCode" value="${item.morceau.id}">
                            <input type="hidden" name="quantity" value="0"> 
                            <input type="submit" value="Remove Item">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <br>

        <form action="<c:url value='/index.jsp' />" method="post">
            <button type="submit">Continue Shopping</button>
        </form>

    </body>
</html>

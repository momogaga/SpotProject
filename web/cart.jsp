<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>ShoppingCart</title>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="resources/style.css" />  

    </head>
    <body>
        <div class="container">

            <jsp:include page="header.jsp"/>  
            <div class="row"> 
                <h2>Votre panier</h2>

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
                                    <button class="btn btn-danger" type="submit">Remove Item</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </table>

                <br>

                <form action="<c:url value='/index.jsp' />" method="post">
                    <button class="btn btn-info"type="submit">Continue Shopping</button>
                </form>
            </div>

            <jsp:include page="footer.jsp"/>  
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="resources/bootstrap/js/bootstrap.js" ></script>

    </body>
</html>
